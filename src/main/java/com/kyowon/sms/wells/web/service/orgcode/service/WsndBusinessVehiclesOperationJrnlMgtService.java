package com.kyowon.sms.wells.web.service.orgcode.service;

import static com.kyowon.sms.wells.web.service.orgcode.dto.WsndBusinessVehiclesOperationJrnlMgtDto.SearchReq;
import static com.kyowon.sms.wells.web.service.orgcode.dto.WsndBusinessVehiclesOperationJrnlMgtDto.SearchRes;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.orgcode.mapper.WsndBusinessVehiclesOperationJrnlMgtMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WsndBusinessVehiclesOperationJrnlMgtService {
    private final WsndBusinessVehiclesOperationJrnlMgtMapper mapper;

    public List<SearchRes> getBusinessVehiclesOperationJrnl(
        SearchReq dto
    ) {
        return mapper.selectBusinessVehiclesOperationJrnl(dto);
    }

    public PagingResult<SearchRes> getBusinessVehiclesOperationJrnl(
        SearchReq dto, PageInfo pageInfo
    ) {
        return mapper.selectBusinessVehiclesOperationJrnl(dto, pageInfo);
    }
}
