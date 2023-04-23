package com.kyowon.sms.wells.web.service.stock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaBldBfsvcCsmbDdlvDto.*;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaBldBfsvcCsmbDdlvDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WsnaBldBfsvcCsmbDdlvMapper {

    PagingResult<WsnaBldBfsvcCsmbDdlvDvo> selectBuildingInfos(SearchReq dto, PageInfo pageInfo);

    List<WsnaBldBfsvcCsmbDdlvDvo> selectItemQtys(String mngtYm, String bldCd);

    List<WsnaBldBfsvcCsmbDdlvDvo> selectItemFirstQtys(String mngtYm, String strWareNo);

    List<SearchItmRes> selectItems(String mngtYm);

    FindTmlmRes selectBldCsmbAplcClose(String mngtYm);

    FindTmlmRes selectBldCsmbAplcFirstClose(String mngtYm);

    int mergeBldCsmbAplcClose(WsnaBldBfsvcCsmbDdlvDvo dvo);

    List<SearchBldRes> selectBuildings(String mngtYm);

    int mergeBldCsmbDeliveries(List<CreateReq> dto);
}
