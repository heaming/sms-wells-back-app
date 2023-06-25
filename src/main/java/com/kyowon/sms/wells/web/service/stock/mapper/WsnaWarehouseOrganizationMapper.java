package com.kyowon.sms.wells.web.service.stock.mapper;

import java.util.List;
import java.util.Optional;

import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaWarehouseOrganizationDto.*;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaWarehouseOrganizationDvo;

@Mapper
public interface WsnaWarehouseOrganizationMapper {
    PagingResult<SearchRes> selectWarehouseOgs(SearchReq dto, PageInfo pageInfo);

    List<SearchRes> selectWarehouseOgs(SearchReq dto);

    int selectWareCarriedCounter(CountReq dto);

    int insertWareCarried(WsnaWarehouseOrganizationDvo warehouse);

    Optional<WsnaWarehouseOrganizationDvo> selectWarehouseByPk(String apyYmWareNo);

    int selectWarehouseOgPartner(WsnaWarehouseOrganizationDvo dvo);

    int updateWarehouseOg(WsnaWarehouseOrganizationDvo dvo);

    int insertWarehouseOg(WsnaWarehouseOrganizationDvo dvo);

    List<SearchWarehouseRes> selectIndvIndpWarehouses(SearchWarehouseReq dto);

    List<SearchWarehouseRes> selectOrganizationWarehouses(SearchWarehouseReq dto);

    List<SearchWarehouseRes> selectLogisticsCenters();

    PagingResult<SearchBuildingRes> selectBuildingInformations(SearchBuildingReq dto, PageInfo pageInfo);
}
