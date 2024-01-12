package com.kyowon.sms.wells.web.service.common.converter;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.common.dto.WsnyAsVisitCostMgtDto.SaveReq;
import com.kyowon.sms.wells.web.service.common.dvo.WsnyAsVisitCostMgtDvo;

@Mapper(componentModel = "spring")
public interface WsnyAsVisitCostMgtConverter {

    WsnyAsVisitCostMgtDvo mapSaveReqToWsnyAsVisitCostMgtDvo(SaveReq saveReq);

}
