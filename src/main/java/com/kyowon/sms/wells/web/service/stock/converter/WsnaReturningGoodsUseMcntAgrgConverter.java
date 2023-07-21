package com.kyowon.sms.wells.web.service.stock.converter;

import java.util.List;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaReturningGoodsUseMcntAgrgDto;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaReturningGoodsUseMcntAgrgDvo;

@Mapper(componentModel = "spring")
public interface WsnaReturningGoodsUseMcntAgrgConverter {

    List<WsnaReturningGoodsUseMcntAgrgDto.SearchRes> mapDvoToSearchRes(List<WsnaReturningGoodsUseMcntAgrgDvo> dvos);

}
