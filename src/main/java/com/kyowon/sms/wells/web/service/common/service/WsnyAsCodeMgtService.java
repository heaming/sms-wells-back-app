package com.kyowon.sms.wells.web.service.common.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kyowon.sms.wells.web.service.common.dto.WsnyAsCodeMgtDto.SearchReq;
import com.kyowon.sms.wells.web.service.common.dvo.WsnyAsCodeMgtDvo;
import com.kyowon.sms.wells.web.service.common.mapper.WsnyAsCodeMgtMapper;
import com.sds.sflex.common.common.dto.ExcelBulkDownloadDto.DownloadReq;
import com.sds.sflex.common.common.dto.ExcelUploadDto;
import com.sds.sflex.common.common.dvo.ExcelMetaDvo;
import com.sds.sflex.common.common.dvo.ExcelUploadErrorDvo;
import com.sds.sflex.common.common.service.ExcelDownloadService;
import com.sds.sflex.common.common.service.ExcelReadService;
import com.sds.sflex.common.utils.StringUtil;
import com.sds.sflex.system.config.core.service.MessageResourceService;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.interceptor.ExcelResultHandler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * <pre>
 * W-SV-U-0016M01 AS 코드관리
 * </pre>
 *
 * @author gs.piit122 김동엽
 * @since 2022-11-08
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class WsnyAsCodeMgtService {

    private final WsnyAsCodeMgtMapper mapper;
    private final MessageResourceService messageResourceService;
    private final ExcelReadService excelReadService;

    private final SqlSession priSqlSession;
    private final ExcelDownloadService excelDownloadService;

    private String MSG_ALT_INPUT_OVER_LEN = "MSG_ALT_INPUT_OVER_LEN";

    public PagingResult<WsnyAsCodeMgtDvo> getAsCodePages(
        SearchReq dto, PageInfo pageInfo
    ) {
        return mapper.selectAsCodes(dto, pageInfo);
    }

    public void getAsCodes(
        DownloadReq req, HttpServletResponse response
    ) throws IOException {

        SXSSFWorkbook workbook = new SXSSFWorkbook(-1);
        priSqlSession.select(
            "com.kyowon.sms.wells.web.service.common.mapper.WsnyAsCodeMgtMapper.selectAsCodes",
            req.parameter(),
            new ExcelResultHandler(workbook, req.columns(), req.searchCondition())
        );
        excelDownloadService.downloadBulkExcel(workbook, response);
    }

    public ExcelUploadDto.UploadRes uploadExcel(
        final MultipartFile file
    ) throws Exception {

        final Map<String, String> header = new LinkedHashMap<String, String>();
        header.put("pdGrpCd", messageResourceService.getMessage("MSG_TXT_PD_GRP"));
        header.put("pdCd", messageResourceService.getMessage("MSG_TXT_PRDT_CODE"));
        header.put("svDvCd", messageResourceService.getMessage("MSG_TXT_SV_TP"));
        header.put("asLctCd", messageResourceService.getMessage("MSG_TXT_AS_LCT"));
        header.put("asPhnCd", messageResourceService.getMessage("MSG_TXT_AS_PHN"));
        header.put("asCausCd", messageResourceService.getMessage("MSG_TXT_AS_CAUS"));
        header.put("siteAwAtcDsnDt", messageResourceService.getMessage("MSG_TXT_AS_CAUS"));
        header.put("svAnaHclsfCd", messageResourceService.getMessage("MSG_TXT_SV_ANA_HCLSF_CD"));
        header.put("siteAwAtcCd", messageResourceService.getMessage("MSG_TXT_SITE_AW"));
        header.put("svAnaMclsfCd", messageResourceService.getMessage("MSG_TXT_SV_ANA_MCLSF_CD"));
        header.put("svAnaLclsfCd", messageResourceService.getMessage("MSG_TXT_SV_ANA_LCLSF_CD"));
        header.put("svAnaDsnDt", messageResourceService.getMessage("MSG_TXT_SV_ANA_DSN_DT"));
        header.put("apyStrtdt", messageResourceService.getMessage("TXT_MSG_HIST_STRT_DTM"));
        header.put("apyEnddt", messageResourceService.getMessage("TXT_MSG_HIST_END_DTM"));

        final List<WsnyAsCodeMgtDvo> list = excelReadService
            .readExcel(file, new ExcelMetaDvo(2, header), WsnyAsCodeMgtDvo.class);

        List<ExcelUploadErrorDvo> errorDvos = new ArrayList<>();

        int row = 2;
        for (WsnyAsCodeMgtDvo excelRowDvo : list) {

            ExcelUploadErrorDvo errorDvo = new ExcelUploadErrorDvo();

            //필수값이 누락되어 있습니다.
            if (StringUtils.isBlank(excelRowDvo.getPdGrpCd()))
                errorDvo.setHeaderName(header.get("pdGrpCd"));
            if (StringUtils.isBlank(excelRowDvo.getPdCd()))
                errorDvo.setHeaderName(header.get("pdCd"));
            if (StringUtils.isBlank(excelRowDvo.getSvTpCd()))
                errorDvo.setHeaderName(header.get("svTpCd"));
            if (StringUtils.isBlank(excelRowDvo.getAsLctCd()))
                errorDvo.setHeaderName(header.get("asLctCd"));
            if (StringUtils.isBlank(excelRowDvo.getAsPhnCd()))
                errorDvo.setHeaderName(header.get("asPhnCd"));
            if (StringUtils.isBlank(excelRowDvo.getAsCausCd()))
                errorDvo.setHeaderName(header.get("asCausCd"));
            if (StringUtils.isBlank(excelRowDvo.getSiteAwAtcDsnDt()))
                errorDvo.setHeaderName(header.get("siteAwAtcDsnDt"));
            if (StringUtils.isBlank(excelRowDvo.getSvAnaHclsfCd()))
                errorDvo.setHeaderName(header.get("svAnaHclsfCd"));
            if (StringUtil.isNotEmpty(errorDvo.getHeaderName())) {
                errorDvo.setErrorRow(row);
                errorDvo.setErrorData(messageResourceService.getMessage("MSG_ALT_EMPTY_REQUIRED_VAL"));
            }
            //--------------------------------------------------------------------

            // 품목코드 유효성체크
            String pdCd = excelRowDvo.getPdCd();
            String validPdCd = this.mapper.selectPdCdValid(pdCd);
            if (StringUtils.isEmpty(validPdCd)) {
                errorDvo.setHeaderName(header.get("pdCd"));
                errorDvo.setErrorRow(row);
                errorDvo.setErrorData(this.messageResourceService.getMessage("MSG_ALT_INVLD_ITM_PD_CD"));
            }

            // 입력 가능 길이를 초과하였습니다 (최대: ?, 입력: ?)
            if (StringUtil.nvl(excelRowDvo.getPdGrpCd(), "").trim().length() > 4) {
                errorDvo.setHeaderName(header.get("pdGrpCd"));
                errorDvo.setErrorRow(row);
                errorDvo.setErrorData(
                    messageResourceService.getMessage(
                        MSG_ALT_INPUT_OVER_LEN, "4",
                        String.valueOf(StringUtil.nvl(excelRowDvo.getPdGrpCd(), "").length())
                    )
                );
            }
            if (pdCd.trim().length() > 10) {
                errorDvo.setHeaderName(header.get("pdCd"));
                errorDvo.setErrorRow(row);
                errorDvo.setErrorData(
                    messageResourceService.getMessage(
                        MSG_ALT_INPUT_OVER_LEN, "0",
                        String.valueOf(StringUtil.nvl(excelRowDvo.getPdCd(), "").length())
                    )
                );
            }
            if (StringUtil.nvl(excelRowDvo.getSvTpCd(), "").trim().length() > 2) {
                errorDvo.setHeaderName(header.get("svTpCd"));
                errorDvo.setErrorRow(row);
                errorDvo.setErrorData(
                    messageResourceService.getMessage(
                        MSG_ALT_INPUT_OVER_LEN, "2",
                        String.valueOf(StringUtil.nvl(excelRowDvo.getSvTpCd(), "").length())
                    )
                );
            }
            if (StringUtil.nvl(excelRowDvo.getAsLctCd(), "").trim().length() > 4) {
                errorDvo.setHeaderName(header.get("asLctCd"));
                errorDvo.setErrorRow(row);
                errorDvo.setErrorData(
                    messageResourceService.getMessage(
                        MSG_ALT_INPUT_OVER_LEN, "4",
                        String.valueOf(StringUtil.nvl(excelRowDvo.getAsLctCd(), "").length())
                    )
                );
            }
            if (StringUtil.nvl(excelRowDvo.getAsPhnCd(), "").trim().length() > 4) {
                errorDvo.setHeaderName(header.get("asPhnCd"));
                errorDvo.setErrorRow(row);
                errorDvo.setErrorData(
                    messageResourceService.getMessage(
                        MSG_ALT_INPUT_OVER_LEN, "4",
                        String.valueOf(StringUtil.nvl(excelRowDvo.getAsPhnCd(), "").length())
                    )
                );
            }
            if (StringUtil.nvl(excelRowDvo.getAsCausCd(), "").trim().length() > 4) {
                errorDvo.setHeaderName(header.get("asCausCd"));
                errorDvo.setErrorRow(row);
                errorDvo.setErrorData(
                    messageResourceService.getMessage(
                        MSG_ALT_INPUT_OVER_LEN, "4",
                        String.valueOf(StringUtil.nvl(excelRowDvo.getAsCausCd(), "").length())
                    )
                );
            }
            if (StringUtil.nvl(excelRowDvo.getSiteAwAtcDsnDt(), "").trim().length() > 8) {
                errorDvo.setHeaderName(header.get("siteAwAtcDsnDt"));
                errorDvo.setErrorRow(row);
                errorDvo.setErrorData(
                    messageResourceService.getMessage(
                        MSG_ALT_INPUT_OVER_LEN, "8",
                        String.valueOf(StringUtil.nvl(excelRowDvo.getSiteAwAtcDsnDt(), "").length())
                    )
                );
            }
            if (StringUtil.nvl(excelRowDvo.getSiteAwAtcCd(), "").trim().length() > 4) {
                errorDvo.setHeaderName(header.get("siteAwAtcCd"));
                errorDvo.setErrorRow(row);
                errorDvo.setErrorData(
                    messageResourceService.getMessage(
                        MSG_ALT_INPUT_OVER_LEN, "4",
                        String.valueOf(StringUtil.nvl(excelRowDvo.getSiteAwAtcCd(), "").length())
                    )
                );
            }
            if (StringUtil.nvl(excelRowDvo.getSvAnaHclsfCd(), "").trim().length() > 4) {
                errorDvo.setHeaderName(header.get("svAnaHclsfCd"));
                errorDvo.setErrorRow(row);
                errorDvo.setErrorData(
                    messageResourceService.getMessage(
                        MSG_ALT_INPUT_OVER_LEN, "4",
                        String.valueOf(StringUtil.nvl(excelRowDvo.getSvAnaHclsfCd(), "").length())
                    )
                );
            }
            if (StringUtil.nvl(excelRowDvo.getSvAnaMclsfCd(), "").trim().length() > 4) {
                errorDvo.setHeaderName(header.get("svAnaMclsfCd"));
                errorDvo.setErrorRow(row);
                errorDvo.setErrorData(
                    messageResourceService.getMessage(
                        MSG_ALT_INPUT_OVER_LEN, "4",
                        String.valueOf(StringUtil.nvl(excelRowDvo.getSvAnaMclsfCd(), "").length())
                    )
                );
            }
            if (StringUtil.nvl(excelRowDvo.getSvAnaLclsfCd(), "").trim().length() > 4) {
                errorDvo.setHeaderName(header.get("svAnaLclsfCd"));
                errorDvo.setErrorRow(row);
                errorDvo.setErrorData(
                    messageResourceService.getMessage(
                        MSG_ALT_INPUT_OVER_LEN, "4",
                        String.valueOf(StringUtil.nvl(excelRowDvo.getSvAnaLclsfCd(), "").length())
                    )
                );
            }
            if (StringUtil.nvl(excelRowDvo.getSvAnaDsnDt(), "").trim().length() > 8) {
                errorDvo.setHeaderName(header.get("svAnaDsnDt"));
                errorDvo.setErrorRow(row);
                errorDvo.setErrorData(
                    messageResourceService.getMessage(
                        MSG_ALT_INPUT_OVER_LEN, "8",
                        String.valueOf(StringUtil.nvl(excelRowDvo.getSvAnaDsnDt(), "").length())
                    )
                );
            }
            if (StringUtil.nvl(excelRowDvo.getApyStrtdt(), "").trim().length() > 8) {
                errorDvo.setHeaderName(header.get("apyStrtdt"));
                errorDvo.setErrorRow(row);
                errorDvo.setErrorData(
                    messageResourceService.getMessage(
                        MSG_ALT_INPUT_OVER_LEN, "8",
                        String.valueOf(StringUtil.nvl(excelRowDvo.getApyStrtdt(), "").length())
                    )
                );
            }
            if (StringUtil.nvl(excelRowDvo.getApyEnddt(), "").trim().length() > 8) {
                errorDvo.setHeaderName(header.get("apyEnddt"));
                errorDvo.setErrorRow(row);
                errorDvo.setErrorData(
                    messageResourceService.getMessage(
                        MSG_ALT_INPUT_OVER_LEN, "8",
                        String.valueOf(StringUtil.nvl(excelRowDvo.getApyEnddt(), "").length())
                    )
                );
            }
            //--------------------------------------------------------------------

            if (null != errorDvo && (errorDvo.getErrorRow() != 0)) {
                errorDvos.add(errorDvo);
            } else {

                excelRowDvo.setSvTpCd(excelRowDvo.getSvDvCd());
                mapper.saveAsCode(excelRowDvo);
            }
            row++;
        }
        return ExcelUploadDto.UploadRes.builder()
            .status(errorDvos.isEmpty() ? "S" : "E")
            .errorInfo(errorDvos)
            .excelData(list)
            .build();
    }

}
