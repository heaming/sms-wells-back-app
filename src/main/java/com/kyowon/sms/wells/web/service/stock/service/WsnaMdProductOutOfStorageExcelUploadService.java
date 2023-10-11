package com.kyowon.sms.wells.web.service.stock.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.service.stock.converter.WsnaMdProductOutOfStorageExcelUploadConverter;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaMdProductOutOfStorageExcelUploadDto.ValidateReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaMdProductOutOfStorageExcelUploadDto.ValidateRes;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaMdProductOutOfStorageExcelUploadDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaMdProductOutOfStorageExcelUploadErrorDvo;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaMdProductOutOfStorageExcelUploadMapper;
import com.sds.sflex.system.config.core.service.MessageResourceService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class WsnaMdProductOutOfStorageExcelUploadService {

    private final WsnaMdProductOutOfStorageExcelUploadMapper mapper;

    private final MessageResourceService messageResourceService;
    private final WsnaMdProductOutOfStorageExcelUploadConverter converter;

    @Transactional
    public int saveMdProductOutOfStoragExcelUpload(List<ValidateReq> dtos) {
        int processCount = 0;
        List<WsnaMdProductOutOfStorageExcelUploadDvo> dvos = converter
            .mapValidateReqToMdProductOutOfStorageExcelUploadDvo(dtos);
        for (WsnaMdProductOutOfStorageExcelUploadDvo dvo : dvos) {
            mapper.insertSppBzsInvoiceProcessIz(dvo);
            processCount += 1;
        }
        return processCount;
    }

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
        headerTitle.put("cntrNo", messageResourceService.getMessage("MSG_TXT_CNTR_DTL_NO")); // 계약상세번호
        headerTitle.put("pcsvCompDv", messageResourceService.getMessage("MSG_TXT_PCSV_CO")); // 택배사
        headerTitle.put("sppIvcNo", messageResourceService.getMessage("MSG_TXT_IVC_NO")); // 송장번호
        headerTitle.put("sppBzsPdId", messageResourceService.getMessage("MSG_TXT_SR_NO")); // SR번호

        List<WsnaMdProductOutOfStorageExcelUploadErrorDvo> excelUploadDvos = new ArrayList<>();

        if (CollectionUtils.isNotEmpty(dvos)) {
            int row = 0;

            for (WsnaMdProductOutOfStorageExcelUploadDvo dvo : dvos) {
                //에러 Dvo 정의 (dataRo, errors);
                WsnaMdProductOutOfStorageExcelUploadErrorDvo excelUploadDvo = new WsnaMdProductOutOfStorageExcelUploadErrorDvo();

                // Error 목록
                List<String> errors = new ArrayList<>();

                String sppIvcNo = dvo.getSppIvcNo();
                // 송장번호 중복체크
                if (StringUtils.isNotEmpty(sppIvcNo)) {
                    int result = mapper.selectExistSppIvcNo(dvo);
                    if (result > 0) {
                        String header = headerTitle.get("sppIvcNo");
                        errors.add(
                            setErrorMsg(
                                header,
                                messageResourceService.getMessage("MSG_ALT_DUPLICATE_VALUE_EXISTS", sppIvcNo)
                            )
                        );
                    }
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
     * 에러 정보 Dvo
     */
    private String setErrorMsg(String header, String errorData) {
        return "[" + header + "]" + errorData;
    }
}
