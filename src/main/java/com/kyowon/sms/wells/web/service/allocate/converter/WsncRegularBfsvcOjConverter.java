package com.kyowon.sms.wells.web.service.allocate.converter;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncRegularBfsvcOjDto;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncRegularBfsvcOjDvo;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncSpecCustAsnDvo;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncSpecCustMngrAsnDvo;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncSpecCustPlanMatDvo;

@Mapper(componentModel = "spring")
public interface WsncRegularBfsvcOjConverter {
//    WsncSpecCustAsnDvo mapSaveProcessReqToSpecCustAsnDvo(WsncRegularBfsvcOjDto.CreateReq dto) throws Exception;

//    WsncSpecCustPlanMatDvo mapSaveProcessReqToSpecCustPlanMatDvo(WsncRegularBfsvcOjDto.CreateReq dto) throws Exception;

//    WsncSpecCustMngrAsnDvo mapSaveProcessReqToSpecCustMngrAsnDvo(WsncRegularBfsvcOjDto.CreateReq dto) throws Exception;

    WsncRegularBfsvcOjDvo mapSaveProcessReqToDvo(WsncRegularBfsvcOjDto.CreateReq dto) throws Exception;

    WsncSpecCustAsnDvo mapDvoToSpecCustAsnDvo(WsncRegularBfsvcOjDvo dto) throws Exception;

    WsncSpecCustPlanMatDvo mapDvoToSpecCustPlanMatDvo(WsncRegularBfsvcOjDvo dto) throws Exception;

    WsncSpecCustMngrAsnDvo mapDvoToSpecCustMngrAsnDvo(WsncRegularBfsvcOjDvo dto) throws Exception;
}
