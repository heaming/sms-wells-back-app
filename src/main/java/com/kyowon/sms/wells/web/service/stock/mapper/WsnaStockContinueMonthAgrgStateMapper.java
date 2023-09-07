package com.kyowon.sms.wells.web.service.stock.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.stock.dvo.WsnaStockContinueMonthAgrgStateDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaStockContinueMonthAgrgStateWareDvo;

@Mapper
public interface WsnaStockContinueMonthAgrgStateMapper {

    List<WsnaStockContinueMonthAgrgStateWareDvo> selectMcByWares(String baseYm);

    List<HashMap<String, String>> selectStockContinueMonthAgrgState(WsnaStockContinueMonthAgrgStateDvo dvo);

}
