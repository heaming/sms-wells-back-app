package com.kyowon.sms.wells.web.bond.credit.converter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.kyowon.sms.wells.web.bond.credit.dto.WbndRentalCbMgtMessageExcludeDto.SaveReq;
import com.kyowon.sms.wells.web.bond.credit.dvo.WbndBondContactExcludeIzDvo;

@Mapper(componentModel = "spring")
public interface WbndRentalCbMgtMessageExcludeConverter {
    @Mapping(source = "fnlMdfcDtm", target = "orglFnlMdfcDtm")
    WbndBondContactExcludeIzDvo mapSaveReqToContactDvo(SaveReq dto);
}
