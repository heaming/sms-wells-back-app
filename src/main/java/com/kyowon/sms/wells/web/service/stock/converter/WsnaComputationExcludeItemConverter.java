package com.kyowon.sms.wells.web.service.stock.converter;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaComputationExcludeItemDto.RemoveReq;
import static com.kyowon.sms.wells.web.service.stock.dto.WsnaComputationExcludeItemDto.SaveReq;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.stock.dvo.WsnaComputationExcludeItemDvo;

@Mapper(componentModel = "spring")
public interface WsnaComputationExcludeItemConverter {

    WsnaComputationExcludeItemDvo mapRemoveReqToWsnaComputationExcludeItemDvo(RemoveReq dto);

    WsnaComputationExcludeItemDvo mapSaveReqToWsnaComputationExcludeItemDvo(SaveReq dto);

}
