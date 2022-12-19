package com.kyowon.sms.wells.web.service.orgcode.mapper;

import static com.kyowon.sms.wells.web.service.orgcode.dto.WsndHumanResourcesDto.*;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WsndHumanResourcesMapper {
    PagingResult<SearchRes> selectHumanResources(SearchReq dto, PageInfo pageInfo);

    List<SearchDepartmentRes> selectManagerDepartmentCodes();

    List<SearchCenterRes> selectManagerCenterCodes(String detpCd);

    List<SearchCenterRes> selectEngineerCenterCodes();
}
