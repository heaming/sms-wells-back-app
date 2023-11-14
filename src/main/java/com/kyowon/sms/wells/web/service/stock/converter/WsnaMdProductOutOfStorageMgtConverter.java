package com.kyowon.sms.wells.web.service.stock.converter;

import java.util.List;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaMdProductOutOfStorageMgtDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaMdProductOutOfStorageMgtDto.SearchRes;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaMdProdcutOutOfStorageSearchDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaMdProductOfStorageDvo;

@Mapper(componentModel = "spring")
public interface WsnaMdProductOutOfStorageMgtConverter {

    WsnaMdProdcutOutOfStorageSearchDvo mapSearchReqToWsnaMdProdcutOutOfStorageSerachDvo(SearchReq dto);

    List<SearchRes> mapAllDvoToSearchRes(List<WsnaMdProductOfStorageDvo> dvos);
}
