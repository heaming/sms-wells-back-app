package com.kyowon.sms.wells.web.closing.sales.converter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.kyowon.sms.wells.web.closing.sales.dto.WdcbAdvancedSellFeeReplaceDto.SaveReq;
import com.kyowon.sms.wells.web.closing.sales.dvo.WdcbAdvancedSellFeeReplaceDvo;

@Mapper(componentModel = "spring")
public interface WdcbAdvancedSellFeeReplaceConverter {
    @Mapping(source = "fnlMdfcDtm", target = "orglFnlMdfcDtm")
    WdcbAdvancedSellFeeReplaceDvo mapSaveReqToWdcbAdvancedSellFeeReplaceDvo(SaveReq dto);
}
