package com.kyowon.sms.wells.web.service.stock.converter;

import java.util.List;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaAssignExcludeItemDto.SaveReq;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaAssignExcludeItemDvo;

@Mapper(componentModel = "spring")
public interface WsnaAssignExcludeItemConverter {
    List<WsnaAssignExcludeItemDvo> mapAllSaveReqToWsnaAssignExcludeItemDvos(List<SaveReq> list);
}
