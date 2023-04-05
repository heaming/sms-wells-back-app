package com.kyowon.sms.wells.web.bond.credit.converter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.kyowon.sms.wells.web.bond.credit.dto.WbndRentalCbMgtDto.SaveTalkReq;
import com.kyowon.sms.wells.web.bond.credit.dvo.WbndBondContactExcludeIzDvo;

@Mapper(componentModel = "spring")
public interface WbndRentalCbMgtConverter {
    @Mapping(source = "fnlMdfcDtm", target = "orglFnlMdfcDtm")
    WbndBondContactExcludeIzDvo mapSaveTalkReqToContactDvo(SaveTalkReq dto);
}
