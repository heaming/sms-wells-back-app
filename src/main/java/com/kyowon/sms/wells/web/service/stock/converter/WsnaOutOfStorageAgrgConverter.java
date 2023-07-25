package com.kyowon.sms.wells.web.service.stock.converter;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaOutOfStorageAgrgDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaOutOfStorageAgrgDvo;

@Mapper(componentModel = "spring")
public interface WsnaOutOfStorageAgrgConverter {

    WsnaOutOfStorageAgrgDvo mapSearchReqToWsnaOutOfStorageAgrgDvo(SearchReq dto);

}
