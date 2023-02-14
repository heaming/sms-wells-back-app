package com.kyowon.sms.wells.web.service.orgcode.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.orgcode.dto.WsndBusinessVehiclesMgtDto.SearchReq;
import com.kyowon.sms.wells.web.service.orgcode.dto.WsndBusinessVehiclesMgtDto.SearchRes;
import com.kyowon.sms.wells.web.service.orgcode.mapper.WsndBusinessVehiclesMgtMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WsndBusinessVehiclesMgtService {
    private final WsndBusinessVehiclesMgtMapper mapper;

    public List<SearchRes> getBusinessVehicles(SearchReq dto) {
        return mapper.selectBusinessVehicles(dto);
    }

    public PagingResult<SearchRes> getBusinessVehiclesPages(SearchReq dto, PageInfo pageInfo) {
        return mapper.selectBusinessVehicles(dto, pageInfo);
    }
}
