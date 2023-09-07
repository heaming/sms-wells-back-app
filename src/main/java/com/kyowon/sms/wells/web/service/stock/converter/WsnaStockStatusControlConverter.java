package com.kyowon.sms.wells.web.service.stock.converter;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaStockStatusControlDto.RemoveReq;
import static com.kyowon.sms.wells.web.service.stock.dto.WsnaStockStatusControlDto.SaveReq;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.stock.dvo.WsnaStockStatusControlDvo;

@Mapper(componentModel = "spring")
public interface WsnaStockStatusControlConverter {

    WsnaStockStatusControlDvo mapSaveReqToWsnaStockStatusControlDvo(SaveReq dto);

    WsnaStockStatusControlDvo mapRemoveReqToWsnaStockStatusControlDvo(RemoveReq dto);

}
