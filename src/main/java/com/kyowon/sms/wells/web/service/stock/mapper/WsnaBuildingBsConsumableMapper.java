package com.kyowon.sms.wells.web.service.stock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaBuildingBsConsumableDto.*;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaBuildingBsConsumableDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WsnaBuildingBsConsumableMapper {

    PagingResult<WsnaBuildingBsConsumableDvo> selectBuildings(SearchReq dto, PageInfo pageInfo);

    List<WsnaBuildingBsConsumableDvo> selectItemQtys(String mngtYm, String bldCd);

    List<WsnaBuildingBsConsumableDvo> selectItemFirstQtys(String mngtYm, String strWareNo);

    List<SearchItmRes> selectItems(String mngtYm);

    FindTmlmRes selectBuildingBsConsumableAplcClose(String mngtYm);

    FindTmlmRes selectBuildingBsConsumableAplcFirstClose(String mngtYm);

    int mergeBuildingBsConsumableAplcClose(WsnaBuildingBsConsumableDvo dvo);

    List<SearchBldRes> selectBuildingList(String mngtYm);

    int mergeBuildingBsConsumables(List<CreateReq> dto);
}
