package com.kyowon.sms.wells.web.service.stock.converter;

import java.util.List;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaPcsvReturningGoodsMgtDto;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaPcsvReturningGoodsMgtDto.SaveReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaPcsvReturningGoodsMgtDto.SearchRes;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaPcsvReturningGoodsDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaPcsvReturningGoodsSaveDvo;

@Mapper(componentModel = "spring")
public interface WsnaPcsvReturningGoodsMgtConverter {
    List<SearchRes> mapDvoToSearchResPages(List<WsnaPcsvReturningGoodsDvo> dvos);

    List<SearchRes> mapDvoToSearchRes(List<WsnaPcsvReturningGoodsDvo> dvos);

    WsnaPcsvReturningGoodsDvo mapSearchResToWsnaPcsvReturningGoodsDvo(WsnaPcsvReturningGoodsMgtDto.SearchRes res);

    WsnaPcsvReturningGoodsSaveDvo mapSaveReqToPcsvReturningGoodsDvo(SaveReq dto);

}
