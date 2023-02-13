package com.kyowon.sms.wells.web.contract.risk.converter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.kyowon.sms.wells.web.contract.risk.dto.WctcSalesLimitsDto.SaveBlacklistReq;
import com.kyowon.sms.wells.web.contract.risk.dvo.WctcSellLimitOjIzDvo;

@Mapper(componentModel = "spring")
public interface WctcSalesLimitsConverter {
    @Mapping(source = "fnlMdfcDtm", target = "orglFnlMdfcDtm")
    WctcSellLimitOjIzDvo mapSaveBlacklistReqToWctcSellLimitOjIzDvo(SaveBlacklistReq dto);
}
