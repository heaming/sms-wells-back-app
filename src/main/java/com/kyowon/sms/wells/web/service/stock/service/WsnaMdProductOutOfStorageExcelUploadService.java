package com.kyowon.sms.wells.web.service.stock.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.stock.converter.WsnaMdProductOutOfStorageExcelUploadConverter;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaMdProductOutOfStorageExcelUploadDto.ValidateReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaMdProductOutOfStorageExcelUploadDto.ValidateRes;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaMdProductOutOfStorageExcelUploadDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaMdProductOutOfStorageExcelUploadErrorDvo;
import com.sds.sflex.system.config.core.service.MessageResourceService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class WsnaMdProductOutOfStorageExcelUploadService {

    private final MessageResourceService messageResourceService;

    private final WsnaMdProductOutOfStorageExcelUploadConverter converter;

    public ValidateRes validateMdProductOutOfStoragExcelUpload(
        List<ValidateReq> dtos
    ) {
        List<WsnaMdProductOutOfStorageExcelUploadDvo> dvos = converter
            .mapValidateReqToMdProductOutOfStorageExcelUploadDvo(dtos);

        List<WsnaMdProductOutOfStorageExcelUploadErrorDvo> excelUploadErrorDvos = validateExcelDatas(dvos);

        return ValidateRes.builder()
            .result(CollectionUtils.isEmpty(excelUploadErrorDvos) ? true : false)
            .errors(excelUploadErrorDvos).build();
    }

    /**
     * 엑셀업로드 유효성 체크 설정
     */
    private List<WsnaMdProductOutOfStorageExcelUploadErrorDvo> validateExcelDatas(
        List<WsnaMdProductOutOfStorageExcelUploadDvo> dvos
    ) {
        // 엑셀업로드 헤더 설정
        Map<String, String> headerTitle = new LinkedHashMap<>();
        headerTitle.put("cstSvAsnNo", messageResourceService.getMessage("MSG_TXT_ASGN_NO")); // 배정번호
        headerTitle.put("cntrNo", messageResourceService.getMessage("MSG_TXT_CNTR_NO")); // 계약번호
        headerTitle.put("cntrSn", messageResourceService.getMessage("MSG_TXT_CNTR_SN")); //계약일련번호
        headerTitle.put("pcsvCompDv", messageResourceService.getMessage("MSG_TXT_PCSV_CO")); // 택배사
        headerTitle.put("sppIvcNo", messageResourceService.getMessage("'MSG_TXT_IVC_NO")); // 송장번호
        headerTitle.put("sppBzsPdId", messageResourceService.getMessage("MSG_TXT_SR_NO")); // SR번호

        List<WsnaMdProductOutOfStorageExcelUploadErrorDvo> excelUploadDvos = new ArrayList<>();

        if (CollectionUtils.isNotEmpty(dvos)) {
            int row = 0;

            for (WsnaMdProductOutOfStorageExcelUploadDvo dvo : dvos) {
                //에러 Dvo 정의 (dataRo, errors);
                WsnaMdProductOutOfStorageExcelUploadErrorDvo excelUploadDvo = new WsnaMdProductOutOfStorageExcelUploadErrorDvo();

                // Error 목록
                List<String> errors = new ArrayList<>();

                // 데이터 존재 하지않는 목록
                List<String> emptyColumnNames = new ArrayList();

                String cstSvAsnNo = dvo.getCstSvAsnNo();
                String cntrNo = dvo.getCntrNo();
                String cntrSn = dvo.getCntrSn();
                String sppIvcNo = dvo.getSppIvcNo();
                String sppBzsPdId = dvo.getSppBzsPdId();
                String pcsvCompDv = dvo.getPcsvCompDv();

                // 배정번호
                if (StringUtils.isEmpty(cstSvAsnNo)) {
                    emptyColumnNames.add("cstSvAsnNo");
                }
                // 계약번호
                if (StringUtils.isEmpty(cntrNo)) {
                    emptyColumnNames.add("cntrNo");
                }
                // 계약일련번호
                if (StringUtils.isEmpty(cntrSn)) {
                    emptyColumnNames.add("cntrSn");
                }
                // 송장번호
                if (StringUtils.isEmpty(sppIvcNo)) {
                    emptyColumnNames.add("sppIvcNo");
                }
                // SR번호
                if (StringUtils.isEmpty(sppBzsPdId)) {
                    emptyColumnNames.add("sppBzsPdId");
                }
                // 택배사 구분
                if (StringUtils.isEmpty(pcsvCompDv)) {
                    emptyColumnNames.add("pcsvCompDv");
                }

                if (CollectionUtils.isNotEmpty(emptyColumnNames)) {
                    //  데이터 존재 체크 [유효하지 않은 항목이 있습니다.]
                    for (String column : emptyColumnNames) {
                        errors.add(
                            setErrorMsg(
                                headerTitle.get(column),
                                messageResourceService.getMessage("MSG_ALT_EXIST_INVALID_ITEM")
                            )
                        );
                    }
                }

                // 데이터 존재하는경우에만 유효성 체크

                // 송장번호 숫자 체크 [숫자만 입력 가능합니다.]
                if (StringUtils.isNotEmpty(sppIvcNo) && !isNumeric(sppIvcNo)) {

                    String header = headerTitle.get("sppIvcNo");
                    errors.add(
                        setErrorMsg(
                            header,
                            messageResourceService.getMessage("MSG_ALT_ONLY_NUMBER")
                        )
                    );
                }
                // 에러가 존재하는 데이터인경우 Dvos 저장
                if (CollectionUtils.isNotEmpty(errors)) {
                    excelUploadDvo.setDataRow(row);
                    excelUploadDvo.setErrors(errors);
                    excelUploadDvos.add(excelUploadDvo);
                }
                row++;
            }
        }
        return excelUploadDvos;
    }

    /**
     * 숫자 체크 (정규식)
     */
    private boolean isNumeric(String s) {
        return s.matches("[0-9]+");
    }

    /**
     * 에러 정보 Dvo
     */
    private String setErrorMsg(String header, String errorData) {
        return "[" + header + "]" + errorData;
    }
}
