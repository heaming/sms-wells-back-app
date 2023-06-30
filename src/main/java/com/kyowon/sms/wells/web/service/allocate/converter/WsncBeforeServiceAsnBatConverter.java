package com.kyowon.sms.wells.web.service.allocate.converter;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncBeforeServiceAsnBatDto;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncBeforeServiceAsnBatDvo;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncSpecCustAsnDvo;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncSpecCustMngrAsnDvo;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncSpecCustPlanMatDvo;

@Mapper(componentModel = "spring")
public interface WsncBeforeServiceAsnBatConverter {

//    WsncSpecCustAsnDvo mapSaveProcessReqToSpecCustAsnDvo(WsncBeforeServiceAsnBatDto.SaveProcessReq dto) throws Exception;

//    WsncSpecCustPlanMatDvo mapSaveProcessReqToSpecCustPlanMatDvo(WsncBeforeServiceAsnBatDto.SaveProcessReq dto) throws Exception;

//    WsncSpecCustMngrAsnDvo mapSaveProcessReqToSpecCustMngrAsnDvo(WsncBeforeServiceAsnBatDto.SaveProcessReq dto) throws Exception;

    WsncBeforeServiceAsnBatDvo mapSaveProcessReqToDvo(WsncBeforeServiceAsnBatDto.SaveProcessReq dto) throws Exception;

    WsncSpecCustAsnDvo mapDvoToSpecCustAsnDvo(WsncBeforeServiceAsnBatDvo dvo) throws Exception;

    WsncSpecCustPlanMatDvo mapDvoToSpecCustPlanMatDvo(WsncBeforeServiceAsnBatDvo dvo) throws Exception;

    WsncSpecCustMngrAsnDvo mapDvoToSpecCustMngrAsnDvo(WsncBeforeServiceAsnBatDvo dvo) throws Exception;
}
