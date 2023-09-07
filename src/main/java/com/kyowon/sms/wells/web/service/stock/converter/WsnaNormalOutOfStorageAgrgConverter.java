package com.kyowon.sms.wells.web.service.stock.converter;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaNormalOutOfStorageAgrgDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaNormalOutOfStorageAgrgDvo;

@Mapper(componentModel = "spring")
public interface WsnaNormalOutOfStorageAgrgConverter {
    WsnaNormalOutOfStorageAgrgDvo mapSearchReqToWsnaNormalOutOfStorageAgrgDvo(SearchReq dto);

}
