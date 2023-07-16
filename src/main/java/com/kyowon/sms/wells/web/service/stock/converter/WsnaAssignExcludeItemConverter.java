package com.kyowon.sms.wells.web.service.stock.converter;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaAssignExcludeItemDto.CreateReq;
import static com.kyowon.sms.wells.web.service.stock.dto.WsnaAssignExcludeItemDto.RemoveReq;

import java.util.List;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.stock.dvo.WsnaAssignExcludeItemDelDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaAssignExcludeItemDvo;

@Mapper(componentModel = "spring")
public interface WsnaAssignExcludeItemConverter {

    WsnaAssignExcludeItemDelDvo mapRemoveReqToWsnaAssignExcludeItemDelDvo(RemoveReq dto);

    List<WsnaAssignExcludeItemDvo> mapAllCreateReqToWsnaAssignExcludeItemDvo(List<CreateReq> list);
}
