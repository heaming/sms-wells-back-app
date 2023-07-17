package com.kyowon.sms.wells.web.fee.standard.converter;

import com.kyowon.sms.wells.web.fee.standard.dto.WfeySellProductTypeDto.SaveSellProductReq;
import com.kyowon.sms.wells.web.fee.standard.dvo.WfeySellProductTypeDvo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WfeySellProductTypeConverter {
    WfeySellProductTypeDvo mapSaveReqWfeySellProductTypeDvo(SaveSellProductReq dto);
}
