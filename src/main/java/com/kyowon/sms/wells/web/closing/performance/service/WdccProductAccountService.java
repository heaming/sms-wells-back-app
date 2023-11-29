package com.kyowon.sms.wells.web.closing.performance.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sflex.common.common.dvo.BatchCallReqDvo;
import com.kyowon.sflex.common.common.service.BatchCallService;
import com.kyowon.sms.wells.web.closing.performance.dto.WdccProductAccountDto.SearchProductRes;
import com.kyowon.sms.wells.web.closing.performance.dto.WdccProductAccountDto.SearchReq;
import com.kyowon.sms.wells.web.closing.performance.dto.WdccProductAccountDto.SearchTotalRes;
import com.kyowon.sms.wells.web.closing.performance.mapper.WdccProductAccountMapper;
import com.sds.sflex.common.common.dto.ExcelBulkDownloadDto;
import com.sds.sflex.common.common.service.ExcelDownloadService;
import com.sds.sflex.system.config.interceptor.ExcelResultHandler;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class WdccProductAccountService {
    private final WdccProductAccountMapper mapper;
    private final ExcelDownloadService excelDownloadService;
    private final SqlSession priSqlSession;
    private final BatchCallService batchCallService;

    /**
     * 상품별 계정 현황 상세내역 다운로드
     * @param dto
     * @return
     */
    public List<SearchTotalRes> getProductAccountTotals(SearchReq dto) {
        return mapper.selectProductAccountTotals(dto);
    }

    /**
     * 상품별 계정 현황(상품)
     * @param dto
     * @return
     */
    public List<SearchProductRes> getProductAccounts(SearchReq dto) {
        return mapper.selectProductAccounts(dto);
    }

    /**
     * 상품별 계정 현황 상세내역 다운로드
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

        /*String jobStatus;
        while (true) {
            Thread.sleep(2000);
            jobStatus = batchCallService.getLastestJobStatus(runId);
            log.info("jobStatus:" + jobStatus);
            if (StringUtils.equals(jobStatus, "Ended OK") || StringUtils.equals(jobStatus, "Ended Not OK")) {
                break;
            }
        }*/

        return runId;
    }
}
