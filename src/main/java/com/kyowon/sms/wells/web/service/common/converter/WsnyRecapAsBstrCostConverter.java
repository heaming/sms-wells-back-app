package com.kyowon.sms.wells.web.service.common.converter;

import com.kyowon.sms.wells.web.service.common.dto.WsnyRecapAsBstrCostDto;
import com.kyowon.sms.wells.web.service.common.dvo.WsnyRecapAsBstrCostDvo;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WsnyRecapAsBstrCostConverter {


    List<WsnyRecapAsBstrCostDto.SearchRes> mapAllRecapAsBstrCostDvoToSearchRes(List<WsnyRecapAsBstrCostDvo> dvos);

    WsnyRecapAsBstrCostDvo mapAllRecapAsBstrCostSaveReqToDvo(WsnyRecapAsBstrCostDto.SaveReq saveReq);

    WsnyRecapAsBstrCostDto.SaveReq mapAllDvoToSaveReq(WsnyRecapAsBstrCostDvo dvo);

}
