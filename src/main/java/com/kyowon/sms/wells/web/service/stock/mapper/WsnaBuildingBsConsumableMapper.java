package com.kyowon.sms.wells.web.service.stock.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaBuildingBsConsumableDto.CreateReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaBuildingBsConsumableDto.FindTmlmRes;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaBuildingBsConsumableDto.SearchBldRes;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaBuildingBsConsumableDto.SearchLmQtyRes;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaBuildingBsConsumableDvo;

@Mapper
public interface WsnaBuildingBsConsumableMapper {

    List<HashMap<String, Object>> selectBuildingBsConsumables(WsnaBuildingBsConsumableDvo dvo);

    List<WsnaBuildingBsConsumableDvo> selectItemQtys(String mngtYm, String bldCd);

    List<WsnaBuildingBsConsumableDvo> selectItemFirstQtys(String mngtYm, String strWareNo);

    List<WsnaBuildingBsConsumableDvo> selectItems(String mngtYm);

    FindTmlmRes selectBuildingBsConsumableAplcClose(String mngtYm);

    FindTmlmRes selectBuildingBsConsumableAplcFirstClose(String mngtYm);

    int mergeBuildingBsConsumableAplcClose(WsnaBuildingBsConsumableDvo dvo);

    List<SearchBldRes> selectBuildingList();

    int mergeBuildingBsConsumables(CreateReq dto);

    List<WsnaBuildingBsConsumableDvo> selectBfsvcCsmbDdlvIzByMngtYm(String mngtYm, String strWareNo);

    String selectNewOstrAkNo(String ostrAkTpCd, String ostrAkRgstDt);

    int updateBfsvcCsmbDdlvIzOstrAkNoSn(WsnaBuildingBsConsumableDvo dvo);

    int updateBfsvcCsmbDdlvIzDdlvStatCd(String strWareNo, String mngtYm);

    List<SearchLmQtyRes> selectApplicationLimitQty(String mngtYm);
}
