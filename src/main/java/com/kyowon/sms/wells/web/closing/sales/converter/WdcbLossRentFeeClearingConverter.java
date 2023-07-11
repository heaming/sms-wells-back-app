package com.kyowon.sms.wells.web.closing.sales.converter;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.closing.sales.dto.WdcbLossRentFeeClearingDto.SaveReq;
import com.kyowon.sms.wells.web.closing.sales.dvo.WdcbLossRentFeeClearingDvo;

@Mapper(componentModel = "spring")
public interface WdcbLossRentFeeClearingConverter {
    WdcbLossRentFeeClearingDvo mapSaveReqToWdcbLossRentFeeClearingDvo(SaveReq dto);
}
