package com.kyowon.sms.wells.web.service.common.service;

import com.kyowon.sms.common.web.closing.mileage.dvo.ZdceSmartMileageExcelDvo;
import com.kyowon.sms.common.web.closing.mileage.util.ZdceMileageUtil;
import com.kyowon.sms.wells.web.service.common.converter.WsnyAsCodeMgtConverter;
import com.kyowon.sms.wells.web.service.common.dto.WsnyAsCodeMgtDto.*;
import com.kyowon.sms.wells.web.service.common.dvo.WsnyAsCodeMgtDvo;
import com.kyowon.sms.wells.web.service.common.mapper.WsnyAsCodeMgtMapper;
import com.sds.sflex.common.common.dto.ExcelUploadDto;
import com.sds.sflex.common.common.dvo.ExcelMetaDvo;
import com.sds.sflex.common.common.dvo.ExcelUploadErrorDvo;
import com.sds.sflex.common.common.service.ExcelReadService;
import com.sds.sflex.common.utils.StringUtil;
import com.sds.sflex.system.config.core.service.MessageResourceService;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    private final WsnyAsCodeMgtConverter converter;
    private final Map<String, String> header;
    private final MessageResourceService messageResourceService;
    private final ExcelReadService excelReadService;

    public PagingResult<SearchRes> getAsCodePages(
        SearchReq dto, PageInfo pageInfo
    ) {
        return new PagingResult<>(
            converter.mapAllSearchResToDvo(mapper.selectAsCodes(dto, pageInfo)), pageInfo
        );
    }

    public List<SearchRes> getAsCodes(
        SearchReq dto
    ) {
        return converter.mapAllSearchResToDvo(mapper.selectAsCodes(dto));
    }

    public ExcelUploadDto.UploadRes uploadExcel(
        final MultipartFile file
    ) throws Exception {

        header.clear();
        header.put("pdGrpCd", messageResourceService.getMessage("MSG_TXT_PD_GRP"));
        header.put("pdCd", messageResourceService.getMessage("MSG_TXT_PRDT"));
        header.put("svTpCd", messageResourceService.getMessage("MSG_TXT_SV_TP"));
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
        for (WsnyAsCodeMgtDvo dvo : list) {
            ExcelUploadErrorDvo errorDvo = new ExcelUploadErrorDvo();

            log.debug("--------------------------------------------------------------------------------------------");
            log.debug(
                "상품그룹코드(pdGrpCd)={}" +
                    ", 상품코드(pdCd)={}" +
                    ", 서비스유형코드(svTpCd)={}" +
                    ", AS위치코드(asLctCd)={}" +
                    ", AS현상코드(asPhnCd)={}" +
                    ", AS원인코드(asCausCd)={}" +
                    ", 서비스분석대분류코드(svAnaHclsfCd)={}" +
                    ", 서비스분석중분류코드(svAnaMclsfCd)={}" +
                    ", 서비스분석소분류코드(svAnaLclsfCd)={}" +
                    ", 현장수당항목코드(siteAwAtcCd)={}",
                dvo.getPdGrpCd(),
                dvo.getPdCd(), dvo.getSvTpCd(), dvo.getAsLctCd(), dvo.getAsPhnCd(), dvo.getAsCausCd(),
                dvo.getSvAnaHclsfCd(), dvo.getSvAnaMclsfCd(), dvo.getSvAnaLclsfCd(),
                dvo.getSiteAwAtcCd()
            );
            log.debug("--------------------------------------------------------------------------------------------");

            //필수값이 누락되어 있습니다.
            if (StringUtils.isEmpty(dvo.getPdGrpCd()))
                errorDvo.setHeaderName(header.get("pdGrpCd"));
            if (StringUtils.isEmpty(dvo.getPdCd()))
                errorDvo.setHeaderName(header.get("pdCd"));
            if (StringUtils.isEmpty(dvo.getSvTpCd()))
                errorDvo.setHeaderName(header.get("svTpCd"));
            if (StringUtils.isEmpty(dvo.getAsLctCd()))
                errorDvo.setHeaderName(header.get("asLctCd"));
            if (StringUtils.isEmpty(dvo.getAsPhnCd()))
                errorDvo.setHeaderName(header.get("asPhnCd"));
            if (StringUtils.isEmpty(dvo.getAsCausCd()))
                errorDvo.setHeaderName(header.get("asCausCd"));
            if (StringUtils.isEmpty(dvo.getSiteAwAtcDsnDt()))
                errorDvo.setHeaderName(header.get("siteAwAtcDsnDt"));
            if (StringUtils.isEmpty(dvo.getSvAnaHclsfCd()))
                errorDvo.setHeaderName(header.get("svAnaHclsfCd"));
            if (StringUtil.isNotEmpty(errorDvo.getHeaderName())) {
                errorDvo.setErrorRow(row);
                errorDvo.setErrorData(messageResourceService.getMessage("MSG_ALT_EMPTY_REQUIRED_VAL"));
            }
            //--------------------------------------------------------------------
            // 입력 가능 길이를 초과하였습니다 (최대: ?, 입력: ?)
            if (StringUtil.nvl(dvo.getPdGrpCd(), "").trim().length() > 4) {
                errorDvo.setHeaderName(header.get("pdGrpCd"));
                errorDvo.setErrorRow(row);
                errorDvo.setErrorData(
                    messageResourceService.getMessage(
                        "MSG_ALT_INPUT_OVER_LEN", "4", String.valueOf(StringUtil.nvl(dvo.getPdGrpCd(), "").length())
                    )
                );
            }
            if (StringUtil.nvl(dvo.getPdCd(), "").trim().length() > 10) {
                errorDvo.setHeaderName(header.get("pdCd"));
                errorDvo.setErrorRow(row);
                errorDvo.setErrorData(
                    messageResourceService.getMessage(
                        "MSG_ALT_INPUT_OVER_LEN", "0", String.valueOf(StringUtil.nvl(dvo.getPdCd(), "").length())
                    )
                );
            }
            if (StringUtil.nvl(dvo.getSvTpCd(), "").trim().length() > 2) {
                errorDvo.setHeaderName(header.get("svTpCd"));
                errorDvo.setErrorRow(row);
                errorDvo.setErrorData(
                    messageResourceService.getMessage(
                        "MSG_ALT_INPUT_OVER_LEN", "2", String.valueOf(StringUtil.nvl(dvo.getSvTpCd(), "").length())
                    )
                );
            }
            if (StringUtil.nvl(dvo.getAsLctCd(), "").trim().length() > 4) {
                errorDvo.setHeaderName(header.get("asLctCd"));
                errorDvo.setErrorRow(row);
                errorDvo.setErrorData(
                    messageResourceService.getMessage(
                        "MSG_ALT_INPUT_OVER_LEN", "4", String.valueOf(StringUtil.nvl(dvo.getAsLctCd(), "").length())
                    )
                );
            }
            if (StringUtil.nvl(dvo.getAsPhnCd(), "").trim().length() > 4) {
                errorDvo.setHeaderName(header.get("asPhnCd"));
                errorDvo.setErrorRow(row);
                errorDvo.setErrorData(
                    messageResourceService.getMessage(
                        "MSG_ALT_INPUT_OVER_LEN", "4", String.valueOf(StringUtil.nvl(dvo.getAsPhnCd(), "").length())
                    )
                );
            }
            if (StringUtil.nvl(dvo.getAsCausCd(), "").trim().length() > 4) {
                errorDvo.setHeaderName(header.get("asCausCd"));
                errorDvo.setErrorRow(row);
                errorDvo.setErrorData(
                    messageResourceService.getMessage(
                        "MSG_ALT_INPUT_OVER_LEN", "4", String.valueOf(StringUtil.nvl(dvo.getAsCausCd(), "").length())
                    )
                );
            }
            if (StringUtil.nvl(dvo.getSiteAwAtcDsnDt(), "").trim().length() > 8) {
                errorDvo.setHeaderName(header.get("siteAwAtcDsnDt"));
                errorDvo.setErrorRow(row);
                errorDvo.setErrorData(
                    messageResourceService.getMessage(
                        "MSG_ALT_INPUT_OVER_LEN", "8",
                        String.valueOf(StringUtil.nvl(dvo.getSiteAwAtcDsnDt(), "").length())
                    )
                );
            }

            if (StringUtil.nvl(dvo.getSiteAwAtcCd(), "").trim().length() > 4) {
                errorDvo.setHeaderName(header.get("siteAwAtcCd"));
                errorDvo.setErrorRow(row);
                errorDvo.setErrorData(
                    messageResourceService.getMessage(
                        "MSG_ALT_INPUT_OVER_LEN", "4", String.valueOf(StringUtil.nvl(dvo.getSiteAwAtcCd(), "").length())
                    )
                );
            }

            if (StringUtil.nvl(dvo.getSvAnaHclsfCd(), "").trim().length() > 4) {
                errorDvo.setHeaderName(header.get("svAnaHclsfCd"));
                errorDvo.setErrorRow(row);
                errorDvo.setErrorData(
                    messageResourceService.getMessage(
                        "MSG_ALT_INPUT_OVER_LEN", "4",
                        String.valueOf(StringUtil.nvl(dvo.getSvAnaHclsfCd(), "").length())
                    )
                );
            }

            if (StringUtil.nvl(dvo.getSvAnaMclsfCd(), "").trim().length() > 4) {
                errorDvo.setHeaderName(header.get("svAnaMclsfCd"));
                errorDvo.setErrorRow(row);
                errorDvo.setErrorData(
                    messageResourceService.getMessage(
                        "MSG_ALT_INPUT_OVER_LEN", "4",
                        String.valueOf(StringUtil.nvl(dvo.getSvAnaMclsfCd(), "").length())
                    )
                );
            }
            if (StringUtil.nvl(dvo.getSvAnaLclsfCd(), "").trim().length() > 4) {
                errorDvo.setHeaderName(header.get("svAnaLclsfCd"));
                errorDvo.setErrorRow(row);
                errorDvo.setErrorData(
                    messageResourceService.getMessage(
                        "MSG_ALT_INPUT_OVER_LEN", "4",
                        String.valueOf(StringUtil.nvl(dvo.getSvAnaLclsfCd(), "").length())
                    )
                );
            }

            if (StringUtil.nvl(dvo.getSvAnaDsnDt(), "").trim().length() > 8) {
                errorDvo.setHeaderName(header.get("svAnaDsnDt"));
                errorDvo.setErrorRow(row);
                errorDvo.setErrorData(
                    messageResourceService.getMessage(
                        "MSG_ALT_INPUT_OVER_LEN", "8", String.valueOf(StringUtil.nvl(dvo.getSvAnaDsnDt(), "").length())
                    )
                );
            }
            if (StringUtil.nvl(dvo.getApyStrtdt(), "").trim().length() > 8) {
                errorDvo.setHeaderName(header.get("apyStrtdt"));
                errorDvo.setErrorRow(row);
                errorDvo.setErrorData(
                    messageResourceService.getMessage(
                        "MSG_ALT_INPUT_OVER_LEN", "8", String.valueOf(StringUtil.nvl(dvo.getApyStrtdt(), "").length())
                    )
                );
            }
            if (StringUtil.nvl(dvo.getApyEnddt(), "").trim().length() > 8) {
                errorDvo.setHeaderName(header.get("apyEnddt"));
                errorDvo.setErrorRow(row);
                errorDvo.setErrorData(
                    messageResourceService.getMessage(
                        "MSG_ALT_INPUT_OVER_LEN", "8", String.valueOf(StringUtil.nvl(dvo.getApyEnddt(), "").length())
                    )
                );
            }
            //--------------------------------------------------------------------

            if (null != errorDvo && (errorDvo.getErrorRow() != 0)) {
                errorDvos.add(errorDvo);
            } else
                mapper.saveAsCode(dvo);
            row++;
        }

        return ExcelUploadDto.UploadRes.builder()
            .status(errorDvos.isEmpty() ? "S" : "E")
            .errorInfo(errorDvos)
            .build();
    }

}
