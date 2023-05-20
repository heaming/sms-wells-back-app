package com.kyowon.sms.wells.web.organization.insurance.service;

import com.kyowon.sms.wells.web.organization.insurance.converter.WogdIndustrialDisasterInsuranceConverter;
import com.kyowon.sms.wells.web.organization.insurance.dto.WogdIndustrialDisasterInsuranceDto.SearchReq;
import com.kyowon.sms.wells.web.organization.insurance.dto.WogdIndustrialDisasterInsuranceDto.SearchRes;
import com.kyowon.sms.wells.web.organization.insurance.dto.WogdIndustrialDisasterInsuranceDto.SearchPrtnrReq;
import com.kyowon.sms.wells.web.organization.insurance.dto.WogdIndustrialDisasterInsuranceDto.EditReq;
import com.kyowon.sms.wells.web.organization.insurance.dto.WogdIndustrialDisasterInsuranceDto.RemoveReq;
import com.kyowon.sms.wells.web.organization.insurance.dvo.WogdIndustrialDisasterInsuranceDvo;
import com.kyowon.sms.wells.web.organization.insurance.mapper.WogdIndustrialDisasterInsuranceMapper;
import com.sds.sflex.common.common.dto.ExcelUploadDto;
import com.sds.sflex.common.common.dvo.ExcelMetaDvo;
import com.sds.sflex.common.common.dvo.ExcelUploadErrorDvo;
import com.sds.sflex.common.common.service.ExcelReadService;
import com.sds.sflex.system.config.core.service.MessageResourceService;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.regex.Pattern;

/**
 * <pre>
 * 산재보험
 * </pre>
 * test
 *
 * @author 이한울
 * @since 2023-04-27
 */
@Service
@RequiredArgsConstructor
public class WogdIndustrialDisasterInsuranceService {

    private final WogdIndustrialDisasterInsuranceMapper mapper;
    private final WogdIndustrialDisasterInsuranceConverter converter;
    private final MessageResourceService messageService;
    private final ExcelReadService excelReadService;

    public PagingResult<SearchRes> getIndustrialDisasterInsurances(SearchReq dto, PageInfo pageInfo) {
        return mapper.selectIndustrialDisasterInsurances(dto, pageInfo);
    }

    public List<SearchRes> getIndustrialDisasterInsurancesForExcelDownload(SearchReq dto) {
        return mapper.selectIndustrialDisasterInsurances(dto);
    }

    @Transactional
    public int editIndustrialDisasterInsurances(List<EditReq> dtos) {
        List<WogdIndustrialDisasterInsuranceDvo> dvos = converter.mapAllEditReqToWogdInddInsrDvo(dtos);
        int processCnt = 0;
        for (WogdIndustrialDisasterInsuranceDvo dvo : dvos) {
            mapper.updateIndustrialDisasterInsurances(dvo);
        }
        return processCnt;
    }

    @Transactional
    public int removeIndustrialDisasterInsurances(List<RemoveReq> dtos) {
        List<WogdIndustrialDisasterInsuranceDvo> dvos = converter.mapAllRemoveReqToWogdInddInsrDvo(dtos);
        int processCnt = 0;
        for (WogdIndustrialDisasterInsuranceDvo dvo : dvos) {
            dvo.setDtaDlYn("Y");
            processCnt = mapper.updateIndustrialDisasterInsurances(dvo);
        }
        return processCnt;
    }

    @Transactional
    public ExcelUploadDto.UploadRes saveIndustrialDisasterInsurancesForDirectExcelUpload(MultipartFile file, String baseYm) throws Exception {
        Map<String, String> headerTitle = new LinkedHashMap<>();
        headerTitle.put("ogTpCd", messageService.getMessage("MSG_TXT_OG_TP"));
        headerTitle.put("prtnrNo", messageService.getMessage("MSG_TXT_PRTNR_NUM"));
        headerTitle.put("inddInsrDdctam", messageService.getMessage("MSG_TXT_DDCTAM"));
        String specialPattern = "^[0-9a-zA-Zㄱ-ㅎ가-힣]*$"; //특수문자 정규식
        String pattern = "^[0-9\\-]*$"; //숫자 정규식
        String status = "S";
        int row = 2;
        List<Map<String, Object>> checks = new ArrayList<>();
        ExcelMetaDvo meta = new ExcelMetaDvo(1, headerTitle);
        List<WogdIndustrialDisasterInsuranceDvo> lists = excelReadService.readExcel(file, meta, WogdIndustrialDisasterInsuranceDvo.class);
        List<ExcelUploadErrorDvo> errorDvos = new ArrayList<>();

        for (WogdIndustrialDisasterInsuranceDvo list : lists) {
            ExcelUploadErrorDvo errorDvo = new ExcelUploadErrorDvo();
            int finalRow = row;
            int existCnt = 0;
            list.setDsbYm(baseYm);
            Map<String, Object> check = new HashMap<>();

            if (StringUtils.isEmpty(list.getOgTpCd())) { //조직유형코드 유효성
                errorDvo.setErrorRow(finalRow);
                errorDvo.setHeaderName(headerTitle.get("ogTpCd"));
                errorDvo.setErrorData(messageService.getMessage("MSG_ALT_EMPTY_REQUIRED_VAL")); //필수값이 누락되어 있습니다.
            } else {
                if (!Pattern.matches(specialPattern, list.getOgTpCd())) {
                    errorDvo.setErrorRow(finalRow);
                    errorDvo.setHeaderName(headerTitle.get("ogTpCd"));
                    errorDvo.setErrorData(messageService.getMessage("MSG_ALT_CHK_SPECL_CHAR")); // 특수 문자는 허용되지 않습니다.
                } else {
                    if (StringUtils.isEmpty(list.getPrtnrNo())) { //파트너번호 유효성
                        errorDvo.setErrorRow(finalRow);
                        errorDvo.setHeaderName(headerTitle.get("prtnrNo"));
                        errorDvo.setErrorData(messageService.getMessage("MSG_ALT_EMPTY_REQUIRED_VAL")); //필수값이 누락되어 있습니다.
                    } else {
                        if (!Pattern.matches(pattern, list.getPrtnrNo())) {
                            errorDvo.setErrorRow(finalRow);
                            errorDvo.setHeaderName(headerTitle.get("prtnrNo"));
                            errorDvo.setErrorData(messageService.getMessage("MSG_ALT_ONLY_NUMBER")); // 숫자만 입력 가능합니다
                        } else {
                            SearchPrtnrReq dto = SearchPrtnrReq.builder().dsbYm(baseYm).ogTpCd(list.getOgTpCd()).prtnrNo(list.getPrtnrNo()).build();
                            existCnt = mapper.selectCountIndustrialDisasterInsurances(dto);
                            if (existCnt == 0) {
                                errorDvo.setErrorRow(finalRow);
                                errorDvo.setHeaderName(headerTitle.get("prtnrNo"));
                                errorDvo.setErrorData(messageService.getMessage("MSG_ALT_CRSP_PRTNR_NO_INF_NEX"));// 해당 파트너번호에 대한 정보가 없습니다.
                            }
                        }
                    }
                }
                if (CollectionUtils.isNotEmpty(checks)) {
                    for (int i = 0; i < checks.size(); i++) {
                        if (checks.get(i).get("prtnrNo").equals(list.getPrtnrNo()) && checks.get(i).get("ogTpCd").equals(list.getOgTpCd())) {
                            errorDvo.setErrorRow(finalRow);
                            errorDvo.setHeaderName(headerTitle.get("prtnrNo"));
                            errorDvo.setErrorData(messageService.getMessage("MSG_ALT_DUPLICATE_EXISTS")); //중복된 값이 존재합니다.
                        }
                    }
                }
                check.put("ogTpCd", list.getOgTpCd());
                check.put("prtnrNo", list.getPrtnrNo());
                checks.add(check);
            }


            if (StringUtils.isEmpty(list.getInddInsrDdctam())) {
                errorDvo.setErrorRow(finalRow);
                errorDvo.setHeaderName(headerTitle.get("inddInsrDdctam"));
                errorDvo.setErrorData(messageService.getMessage("MSG_ALT_EMPTY_REQUIRED_VAL")); //필수값이 누락되어 있습니다.
            } else {
                if (!Pattern.matches(pattern, list.getInddInsrDdctam())) {
                    errorDvo.setErrorRow(finalRow);
                    errorDvo.setHeaderName(headerTitle.get("inddInsrDdctam"));
                    errorDvo.setErrorData(messageService.getMessage("MSG_ALT_ONLY_NUMBER")); // 숫자만 입력 가능합니다
                }
            }

            if (null != errorDvo && (errorDvo.getErrorRow() != 0)) {
                errorDvos.add(errorDvo);
            }
            row++;
        }

        if (CollectionUtils.isNotEmpty(errorDvos)) {
            status = "E";
        }

        if (status.equals("S")) {
            mapper.updateIndustrialDisasterInsurancesForExcelupload(lists);
        }

        ExcelUploadDto.UploadRes result = ExcelUploadDto.UploadRes.builder()
            .status(status)
            .excelData(lists)
            .errorInfo(errorDvos)
            .build();
        return result;
    }
}
