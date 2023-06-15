package com.kyowon.sms.wells.web.service.allocate.converter;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncBeforeServiceAsnBatDto;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncSpecCustAsnDvo;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncSpecCustMngrAsnDvo;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncSpecCustPlanMatDvo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WsncBeforeServiceAsnBatConverter {

    WsncSpecCustAsnDvo mapSaveProcessReqToSpecCustAsnDvo(WsncBeforeServiceAsnBatDto.SaveProcessReq dto) throws Exception;

    WsncSpecCustPlanMatDvo mapSaveProcessReqToSpecCustPlanMatDvo(WsncBeforeServiceAsnBatDto.SaveProcessReq dto) throws Exception;

    WsncSpecCustMngrAsnDvo mapSaveProcessReqToSpecCustMngrAsnDvo(WsncBeforeServiceAsnBatDto.SaveProcessReq dto) throws Exception;
}
