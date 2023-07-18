package com.kyowon.sms.wells.web.service.stock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.common.dvo.WsnzWellsCodeWareHouseDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaDisposalMaterialsPsDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaDisposalMaterialsPsTxtDvo;

@Mapper
public interface WsnaDisposalMaterialsPsMapper {

    List<WsnzWellsCodeWareHouseDvo> selectMcbyWareHouses(String baseYm);

    List<WsnaDisposalMaterialsPsDvo> selectDisposalMaterials(String baseYm, String wareNo);

    List<WsnaDisposalMaterialsPsTxtDvo> selectDisposalMaterialTexts();

}
