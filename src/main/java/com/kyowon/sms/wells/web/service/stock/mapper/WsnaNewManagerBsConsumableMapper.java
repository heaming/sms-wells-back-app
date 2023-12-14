package com.kyowon.sms.wells.web.service.stock.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaNewManagerBsConsumableDto.FindTmlmRes;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaNewManagerBsConsumableDto.SearchLmQtyRes;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaNewManagerBsConsumableDvo;

@Mapper
public interface WsnaNewManagerBsConsumableMapper {
    List<WsnaNewManagerBsConsumableDvo> selectItems(String mngtYm);

    FindTmlmRes selectNewManagerBsConsumableAplcClose(String mngtYm);

    FindTmlmRes selectNewManagerBsConsumableAplcFirstClose(String mngtYm);

    int mergeNewManagerBsConsumableAplcClose(WsnaNewManagerBsConsumableDvo dvo);

    List<SearchLmQtyRes> selectApplicationLimitQty(String mngtYm);

    List<HashMap<String, Object>> selectNewManagerBsConsumables(WsnaNewManagerBsConsumableDvo dvo);

    int mergeNewManagerBsConsumables(WsnaNewManagerBsConsumableDvo dvo);

    List<WsnaNewManagerBsConsumableDvo> selectBfsvcCsmbDdlvIzByMngtYm(String mngtYm, String strWareNo);

    String selectNewOstrAkNo(String ostrAkTpCd, String ostrAkRgstDt);

    int updateBfsvcCsmbDdlvIzOstrAkNoSn(WsnaNewManagerBsConsumableDvo dvo);

    int updateBfsvcCsmbDdlvIzDdlvStatCd(List<String> strWareNos, String mngtYm);

}
