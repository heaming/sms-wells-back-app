package com.kyowon.sms.wells.web.service.orgcode.service;

import static com.kyowon.sms.wells.web.service.orgcode.dto.WsndHumanResourcesDto.*;

import java.util.List;

import com.kyowon.sms.wells.web.service.orgcode.mapper.WsndHumanResourcesMapper;
import org.springframework.stereotype.Service;

import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WsndHumanResourcesService {
    private final WsndHumanResourcesMapper mapper;

    public PagingResult<SearchRes> getHumanResourcesPages(
        SearchReq dto, PageInfo pageInfo
    ) {
        return mapper.selectHumanResources(dto, pageInfo);
    }

    public List<SearchDepartmentRes> getManagerDepartmentCodes() {
        return mapper.selectManagerDepartmentCodes();
    }

    public List<SearchCenterRes> getManagerCenterCodes(String detpCd) {
        return mapper.selectManagerCenterCodes(detpCd);
    }

    public List<SearchCenterRes> getEngineerCenterCodes() {
        return mapper.selectEngineerCenterCodes();
    }
}
