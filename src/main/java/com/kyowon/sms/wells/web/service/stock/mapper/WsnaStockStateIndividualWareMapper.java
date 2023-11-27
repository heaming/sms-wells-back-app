package com.kyowon.sms.wells.web.service.stock.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaStockStateIndividualWareDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaStockStateIndividualWareDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaStockStateIndividualWareWareDvo;

@Mapper
public interface WsnaStockStateIndividualWareMapper {

    List<WsnaStockStateIndividualWareWareDvo> selectMcByWares(SearchReq dto);

    List<HashMap<String, String>> selectStockStateIndividualWare(WsnaStockStateIndividualWareDvo dvo);

    List<HashMap<String, String>> selectServiceCenter(String baseYm);

    String selectMyServiceCenter(String baseYm);

}
