package com.kyowon.sms.wells.web.service.common.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.common.dto.WsnzRegistrationBarCodeDto;

@Mapper
public interface WsnzRegistrationBarCodeMapper {
    WsnzRegistrationBarCodeDto.SearchRes selectRegistrationBarCode(
        WsnzRegistrationBarCodeDto.SearchReq dto
    );
}
