package com.kyowon.sms.wells.web.service.allocate.converter;

import java.util.List;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncRoutineBsPsicAssignStateDto.SearchRes;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncRoutineBsPsicAssignStateDvo;

@Mapper(componentModel = "spring")
public interface WsncRoutineBsPsicAssignStateConverter {
    List<SearchRes> mapDvoToSearchResPages(List<WsncRoutineBsPsicAssignStateDvo> dvos);

}
