package com.kyowon.sms.wells.web.service.stock.converter;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaReturningGoodsStoreDto.SaveConfirmationReq;
import static com.kyowon.sms.wells.web.service.stock.dto.WsnaReturningGoodsStoreDto.SaveReq;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.stock.dvo.WsnaReturningGoodsStoreDvo;

@Mapper(componentModel = "spring")
public interface WsnaReturningGoodsStoreConverter {

    WsnaReturningGoodsStoreDvo mapSaveReqToReturningGoodsDvo(SaveReq dto);

    WsnaReturningGoodsStoreDvo mapSaveConfirmationReqToReturningGoodsDvo(SaveConfirmationReq dto);

}
