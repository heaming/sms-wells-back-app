package com.kyowon.sms.wells.web.service.stock.converter;

import java.util.List;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaItemLocationDto;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaItemLocationDvo;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaItemLocationDto.*;

@Mapper(componentModel = "spring")
public interface WsnaItemLocationConverter {
    List<WsnaItemLocationDvo> mapAllWsnaItemLocationDvos(List<CreateReq> list);

    List<WsnaItemLocationDvo> mapAllStockWsnaItemLocationDvos(List<CreateLocationReq> list);

    WsnaItemLocationDvo mapToStckStdGbWsnaItemLocataionDvo(CreateWareLocationReq dto);
}
