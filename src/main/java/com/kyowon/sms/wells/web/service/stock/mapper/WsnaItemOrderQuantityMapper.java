package com.kyowon.sms.wells.web.service.stock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.stock.dvo.WsnaItemOrderQuantityDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaItemOrderQuantitySearchDvo;

@Mapper
public interface WsnaItemOrderQuantityMapper {

    long selectItemOrderQuantityCount(WsnaItemOrderQuantitySearchDvo dvo);

    List<WsnaItemOrderQuantityDvo> selectItemOrderQuantitiy(WsnaItemOrderQuantitySearchDvo dvo);

}
