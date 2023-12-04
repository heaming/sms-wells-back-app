package com.kyowon.sms.wells.web.service.stock.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaBuildingBsConsumableDto.FindTmlmRes;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaBuildingBsConsumableDto.SearchBldRes;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaBuildingBsConsumableDto.SearchLmQtyRes;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaBsConsumablesAskReqDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaBuildingBsConsumableDvo;

@Mapper
public interface WsnaBuildingBsConsumableMapper {

    List<SearchBldRes> selectBuildingList(String mngtYm);

    List<WsnaBuildingBsConsumableDvo> selectItems(String mngtYm);

    FindTmlmRes selectBuildingBsConsumableAplcClose(String mngtYm);

    FindTmlmRes selectBuildingBsConsumableAplcFirstClose(String mngtYm);

    int mergeBuildingBsConsumableAplcClose(WsnaBuildingBsConsumableDvo dvo);

    List<SearchLmQtyRes> selectApplicationLimitQty(String mngtYm);

    List<HashMap<String, Object>> selectBuildingBsConsumables(WsnaBuildingBsConsumableDvo dvo);

    int mergeBuildingBsConsumables(WsnaBuildingBsConsumableDvo dvo);

    List<WsnaBuildingBsConsumableDvo> selectBfsvcCsmbDdlvIzByMngtYm(String mngtYm, String strWareNo);

    String selectNewOstrAkNo(String ostrAkTpCd, String ostrAkRgstDt);

    int updateBfsvcCsmbDdlvIzOstrAkNoSn(WsnaBsConsumablesAskReqDvo dvo, String mngtYm, String bfsvcCsmbDdlvOjCd);

    int updateBfsvcCsmbDdlvIzDdlvStatCd(String strWareNo, String mngtYm);

}
