package com.kyowon.sms.wells.web.service.stock.converter;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaQomAsnDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaRenewalWareHouseDvo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WsnaQomAsnConverter {
    WsnaRenewalWareHouseDvo searchReqToWsnaRenewalWareHouseDvo(SearchReq dto);
}
