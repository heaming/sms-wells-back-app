package com.kyowon.sms.wells.web.service.stock.converter;

import java.util.List;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaPcsvReturningGoodsMgtDto.SaveReq;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaPcsvReturningGoodsDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaPcsvReturningGoodsSaveDvo;

@Mapper(componentModel = "spring")
public interface WsnaPcsvReturningGoodsMgtConverter {

    //    List<SearchRes> mapDvoToSearchRes(List<WsnaPcsvReturningGoodsDvo> dvos);

    List<WsnaPcsvReturningGoodsSaveDvo> mapSaveReqToPcsvReturningGoodsDvo(List<SaveReq> dto);

    List<WsnaPcsvReturningGoodsSaveDvo> mapGoodsDvoToSaveGoodsDvo(List<WsnaPcsvReturningGoodsDvo> dvos);

}
