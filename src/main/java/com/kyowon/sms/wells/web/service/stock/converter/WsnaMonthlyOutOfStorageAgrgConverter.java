package com.kyowon.sms.wells.web.service.stock.converter;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaMonthlyOutOfStorageAgrgDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaMonthlyOutOfStorageAgrgDvo;

@Mapper(componentModel = "spring")
public interface WsnaMonthlyOutOfStorageAgrgConverter {

    WsnaMonthlyOutOfStorageAgrgDvo mapSearchReqToWsnaMonthlyOutOfStorageAgrgDvo(
        SearchReq dto
    );

}
