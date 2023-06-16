package com.kyowon.sms.wells.web.service.stock.converter;

import com.kyowon.sms.wells.web.service.stock.dvo.WsnaReturningGoodsStoreDvo;
import org.mapstruct.Mapper;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaReturningGoodsStoreDto.*;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaItemStockItemizationReqDvo;

@Mapper(componentModel = "spring")
public interface WsnaReturningGoodsStoreConverter {

    WsnaReturningGoodsStoreDvo mapSaveReqToReturningGoodsDvo(SaveReq dto);

    WsnaReturningGoodsStoreDvo mapSaveConfirmationReqToReturningGoodsDvo(SaveConfirmationReq dto);

}
