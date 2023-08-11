package com.kyowon.sms.wells.web.service.stock.converter;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaStockMasterRenewalDto.EditReq;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.stock.dvo.WsnaStockMasterRenewalDvo;

@Mapper(componentModel = "spring")
public interface WsnaStockMasterRenewalConverter {

    WsnaStockMasterRenewalDvo mapEditReqToWsnaStockMasterRenewalDvo(EditReq dto);

}
