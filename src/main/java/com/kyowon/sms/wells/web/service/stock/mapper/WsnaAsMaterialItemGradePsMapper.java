package com.kyowon.sms.wells.web.service.stock.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.common.dvo.WsnzWellsCodeWareHouseDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaAsMaterialItemGradePsDvo;

@Mapper
public interface WsnaAsMaterialItemGradePsMapper {

    List<WsnzWellsCodeWareHouseDvo> selectMcByWares(String baseYm, String allText);

    List<HashMap<String, String>> selectAsMaterialItemGradePs(WsnaAsMaterialItemGradePsDvo dvo);

}
