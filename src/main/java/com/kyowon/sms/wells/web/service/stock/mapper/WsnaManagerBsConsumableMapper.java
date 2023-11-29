package com.kyowon.sms.wells.web.service.stock.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaManagerBsConsumableDto.FindTmlmRes;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaManagerBsConsumableDto.SearchLmQtyRes;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaManagerBsConsumableDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaManagerBsConsumableDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WsnaManagerBsConsumableMapper {
    List<WsnaManagerBsConsumableDvo> selectItems(String mngtYm);

    List<HashMap<String, Object>> selectManagerBsConsumables(WsnaManagerBsConsumableDvo dvo);

    List<WsnaManagerBsConsumableDvo> selectBuildings(SearchReq dto);

    PagingResult<WsnaManagerBsConsumableDvo> selectBuildings(SearchReq dto, PageInfo pageInfo);

    List<WsnaManagerBsConsumableDvo> selectItemQtys(String mngtYm, String prtnrNo);

    List<WsnaManagerBsConsumableDvo> selectItemFirstQtys(String mngtYm, String prtnrNo);

    FindTmlmRes selectManagerBsConsumableAplcClose(String mngtYm);

    FindTmlmRes selectManagerBsConsumableAplcFirstClose(String mngtYm);

    int mergeManagerBsConsumableAplcClose(WsnaManagerBsConsumableDvo dvo);

    int mergeManagerBsConsumables(WsnaManagerBsConsumableDvo dvo);

    List<WsnaManagerBsConsumableDvo> selectBfsvcCsmbDdlvIzByMngtYm(String mngtYm, String strWareNo);

    String selectNewOstrAkNo(String ostrAkTpCd, String ostrAkRgstDt);

    int updateBfsvcCsmbDdlvIzOstrAkNoSn(WsnaManagerBsConsumableDvo dvo);

    int updateBfsvcCsmbDdlvIzDdlvStatCd(List<String> strWareNos, String mngtYm);

    List<SearchLmQtyRes> selectApplicationLimitQty(String mngtYm);
}
