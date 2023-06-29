package com.kyowon.sms.wells.web.service.stock.converter;

import java.util.List;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaPcsvReturningGoodsMgtDto.SaveReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaPcsvReturningGoodsMgtDto.SearchRes;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaPcsvReturningGoodsDvo;

@Mapper(componentModel = "spring")
public interface WsnaPcsvReturningGoodsMgtConverter {
    List<SearchRes> mapDvoToSearchResPages(List<WsnaPcsvReturningGoodsDvo> dvos);

    WsnaPcsvReturningGoodsDvo mapSaveReqToPcsvReturningGoodsDvo(SaveReq dto);

}
