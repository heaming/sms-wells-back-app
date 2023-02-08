package com.kyowon.sms.wells.web.contract.risk.converter;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.contract.risk.dto.WctcUserSellLimitMngtDto.SaveReq;
import com.kyowon.sms.wells.web.contract.risk.dvo.WctcUserSellLimitMngtDvo;

@Mapper(componentModel = "spring")
public interface WctcUserSellLimitMngtConverter {
    WctcUserSellLimitMngtDvo mapSaveReqToWctcUserSellLimitMngtDvo(SaveReq dto);

}
