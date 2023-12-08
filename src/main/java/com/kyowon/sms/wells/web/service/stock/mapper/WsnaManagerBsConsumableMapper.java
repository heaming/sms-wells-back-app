package com.kyowon.sms.wells.web.service.stock.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaManagerBsConsumableDto.FindTmlmRes;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaManagerBsConsumableDto.SearchLmQtyRes;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaManagerBsConsumableDvo;

@Mapper
public interface WsnaManagerBsConsumableMapper {
    List<WsnaManagerBsConsumableDvo> selectItems(String mngtYm);

    FindTmlmRes selectManagerBsConsumableAplcClose(String mngtYm);

    FindTmlmRes selectManagerBsConsumableAplcFirstClose(String mngtYm);

    int mergeManagerBsConsumableAplcClose(WsnaManagerBsConsumableDvo dvo);

    List<SearchLmQtyRes> selectApplicationLimitQty(String mngtYm);

    List<HashMap<String, Object>> selectManagerBsConsumables(WsnaManagerBsConsumableDvo dvo);

    int mergeManagerBsConsumables(WsnaManagerBsConsumableDvo dvo);

    List<WsnaManagerBsConsumableDvo> selectBfsvcCsmbDdlvIzByMngtYm(String mngtYm, String strWareNo);

    String selectNewOstrAkNo(String ostrAkTpCd, String ostrAkRgstDt);

    int updateBfsvcCsmbDdlvIzOstrAkNoSn(WsnaManagerBsConsumableDvo dvo);

    int updateBfsvcCsmbDdlvIzDdlvStatCd(List<String> strWareNos, String mngtYm);

}
