package com.kyowon.sms.wells.web.service.stock.converter;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaMdProductOutOfStorageMgtDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaMdProdcutOutOfStorageSearchDvo;

@Mapper(componentModel = "spring")
public interface WsnaMdProductOutOfStorageMgtConverter {

    WsnaMdProdcutOutOfStorageSearchDvo mapSearchReqToWsnaMdProdcutOutOfStorageSerachDvo(SearchReq dto);
}
