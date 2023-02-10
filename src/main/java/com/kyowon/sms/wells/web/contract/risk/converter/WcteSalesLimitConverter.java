package com.kyowon.sms.wells.web.contract.risk.converter;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.contract.risk.dto.WcteSalesLimitDto.SaveEntrpJLmOjReq;
import com.kyowon.sms.wells.web.contract.risk.dvo.WcteSellLmOjIzDvo;

@Mapper(componentModel = "spring")
public interface WcteSalesLimitConverter {

    WcteSellLmOjIzDvo mapSaveEntrpJLmOjReqToDvo(SaveEntrpJLmOjReq dto);

}
