package com.kyowon.sms.wells.web.service.stock.converter;

import java.util.List;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaMdProductOutOfStorageExcelUploadDto.ValidateReq;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaMdProductOutOfStorageExcelUploadDvo;

@Mapper(componentModel = "spring")
public interface WsnaMdProductOutOfStorageExcelUploadConverter {

    List<WsnaMdProductOutOfStorageExcelUploadDvo> mapValidateReqToMdProductOutOfStorageExcelUploadDvo(
        List<ValidateReq> dto
    );
}
