package com.kyowon.sms.wells.web.service.stock.converter;

import java.util.List;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaPcsvOutOfStorageMgtDto.SearchRes;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaPcsvOutOfStorageDvo;

@Mapper(componentModel = "spring")
public interface WsnaPcsvOutOfStorageMgtConverter {
    List<SearchRes> mapAllDvoToSearchRes(List<WsnaPcsvOutOfStorageDvo> dvos);
}
