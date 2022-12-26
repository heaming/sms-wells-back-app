package com.kyowon.sms.wells.web.service.allocate.converter;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncFixationVisitDto;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncFixationVisitDvo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WsncFixationVisitConverter {
    WsncFixationVisitDvo mapFixationVisitReqToFixationVisitDvo(WsncFixationVisitDto.SaveFixationVisitRegReq dto);
}
