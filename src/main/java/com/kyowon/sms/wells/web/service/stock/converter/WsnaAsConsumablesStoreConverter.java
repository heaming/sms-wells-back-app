package com.kyowon.sms.wells.web.service.stock.converter;

import com.kyowon.sms.wells.web.service.stock.dvo.WsnaAsConsumablesStoreDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaItemStockItemizationReqDvo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaAsConsumablesStoreDto.*;

@Mapper(componentModel = "spring")
public interface WsnaAsConsumablesStoreConverter {

    WsnaAsConsumablesStoreDvo mapSaveReqToAsConsumablesStoreDvo(SaveReq dto);

    WsnaAsConsumablesStoreDvo mapRemoveReqToAsConsumablesStoreDvo(RemoveReq dto);

    WsnaItemStockItemizationReqDvo mapItemAsConsumablesStoreDvo(WsnaAsConsumablesStoreDvo dvo);

}
