package com.kyowon.sms.wells.web.closing.performance.service;

import com.kyowon.sflex.common.common.dvo.BatchCallReqDvo;
import com.kyowon.sflex.common.common.service.BatchCallService;
import com.kyowon.sms.wells.web.closing.performance.dto.WdccProductAccountDto.SearchProductRes;
import com.kyowon.sms.wells.web.closing.performance.dto.WdccProductAccountDto.SearchReq;
import com.kyowon.sms.wells.web.closing.performance.dto.WdccProductAccountDto.SearchStatusRes;
import com.kyowon.sms.wells.web.closing.performance.dto.WdccProductAccountDto.SearchTotalRes;
import com.kyowon.sms.wells.web.closing.performance.mapper.WdccProductAccountMapper;
import com.kyowon.sms.wells.web.closing.zcommon.constants.MakeFileStatus;
import com.sds.sflex.common.common.dto.ExcelBulkDownloadDto;
import com.sds.sflex.common.common.service.ExcelDownloadService;
import com.sds.sflex.system.config.interceptor.ExcelResultHandler;
import com.sds.sflex.system.config.validation.BizAssert;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class WdccProductAccountService {

    private final WdccProductAccountMapper mapper;
    private final ExcelDownloadService excelDownloadService;
    private final SqlSession priSqlSession;
    private final BatchCallService batchCallService;

    @Value("${sharedFile.path.app:none}")
    private String filePathShare;

    /**
     * 상품별 계정 현황 상세내역 다운로드
     *
     * @param dto
     * @return
     */
    public List<SearchTotalRes> getProductAccountTotals(SearchReq dto) {
        return mapper.selectProductAccountTotals(dto);
    }

    /**
     * 상품별 계정 현황(상품)
     *
     * @param dto
     * @return
     */
    public List<SearchProductRes> getProductAccounts(SearchReq dto) {
        return mapper.selectProductAccounts(dto);
    }

    /**
     * 상품별 계정 현황 상세내역 다운로드
     *
     * @param req
     * @param response
     * @return
     */
    public void getProductAccountsExcelDownload(ExcelBulkDownloadDto.DownloadReq req, HttpServletResponse response)
        throws IOException {
        SXSSFWorkbook workbook = new SXSSFWorkbook(-1);
        priSqlSession.select(
            "com.kyowon.sms.wells.web.closing.performance.mapper.WdccProductAccountMapper.selectProductAccountsExcelDownload",
            req.parameter(),
            new ExcelResultHandler(workbook, req.columns(), req.searchCondition())
        );

        excelDownloadService.downloadBulkExcel(workbook, response);
    }

    @Transactional
    public String createdetailItemizationFile(SearchReq dto) throws Exception {
        var baseY = Integer.parseInt(dto.baseYm().substring(0, 4));
        var baseM = Integer.parseInt(dto.baseYm().substring(4, 6));

        var fileStatus = checkFileStatus(baseY, baseM);

        if (fileStatus.isPresent()) { // 1. TB_IFIN_DP_SLIP_TRS_IDK_GN 에 ('WA', YYYY, MM) 존재하면
            if (MakeFileStatus.DONE.getCode().equals(fileStatus.get().zfcseq())) { // 2. seq = 1 (완료) 이면
                // 3. TB_IFIN_DP_SLIP_TRS_IDK_GN seq = 0 (진행 중) 으로 update
                mapper.updateProductAccountDetailFileStatus(baseY, baseM, 0);
                // 3-2. 배치 시작
            } else { // 2-2. seq = 0 (진행 중) 이면
                // throw Exception: 파일 생성 중입니다.
                return MakeFileStatus.PROCESSING.getName();
            }
        } else { // 1-2. 존재하지 않으면
            // 2. TB_IFIN_DP_SLIP_TRS_IDK_GN 에 seq = 0 (진행 중) 으로 insert
            mapper.createProductAccountDetailFileStatus(baseY, baseM, 0);
            // 2-2. 배치 시작
        }

        // 배치 dvo 생성
        BatchCallReqDvo batchCallReqDvo = new BatchCallReqDvo();

        // 배치 parameter
        Map<String, String> params = new HashMap<String, String>();
        params.put("baseYm", dto.baseYm());

        log.info("params:" + params);
        batchCallReqDvo.setJobKey("WSM_DC_OA0005");
        batchCallReqDvo.setParams(params);

        String runId = batchCallService.runJob(batchCallReqDvo);
        BizAssert.isTrue(StringUtils.isNotEmpty(runId), "MSG_ALT_SVE_ERR");

        /*
        String jobStatus;
        while (true) {
            Thread.sleep(2000);
            jobStatus = batchCallService.getLastestJobStatus(runId);
            log.info("jobStatus:" + jobStatus);
            if (StringUtils.equals(jobStatus, "Ended OK") || StringUtils.equals(jobStatus, "Ended Not OK")) {
                break;
            }
        }
        */

        return runId;
    }

    private Optional<SearchStatusRes> checkFileStatus(Integer baseY, Integer baseM) {
        return mapper.selectProductAccountDetailFileStatus(baseY, baseM);
    }

    /**
     * 상품별 계정 현황 다운로드 파일 명
     *
     * @param baseYm
     * @return filename
     */
    public String getDownloadFileName(String baseYm) {
        var baseY = Integer.parseInt(baseYm.substring(0, 4));
        var baseM = Integer.parseInt(baseYm.substring(4, 6));

        var fileStatus = checkFileStatus(baseY, baseM);

        if (fileStatus.isPresent() && MakeFileStatus.PROCESSING.getCode().equals(fileStatus.get().zfcseq())) {
            return MakeFileStatus.PROCESSING.getName();
        }
        return Paths.get(this.filePathShare, "WdccSalesInfobyProductExcelJob", "W_AccountByProd_" + baseYm + ".csv").toString();
    }
}
