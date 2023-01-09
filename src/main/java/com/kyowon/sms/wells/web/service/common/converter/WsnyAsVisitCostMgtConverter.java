package com.kyowon.sms.wells.web.service.common.converter;

import com.kyowon.sms.wells.web.service.common.dto.WsnyAsVisitCostMgtDto;
import com.kyowon.sms.wells.web.service.common.dvo.WsnyAsVisitCostMgtDvo;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WsnyAsVisitCostMgtConverter {


    List<WsnyAsVisitCostMgtDto.SearchRes> mapAllRecapAsBstrCostDvoToSearchRes(List<WsnyAsVisitCostMgtDvo> dvos);

    WsnyAsVisitCostMgtDvo mapAllRecapAsBstrCostSaveReqToDvo(WsnyAsVisitCostMgtDto.SaveReq saveReq);

    WsnyAsVisitCostMgtDto.SaveReq mapAllDvoToSaveReq(WsnyAsVisitCostMgtDvo dvo);

}
