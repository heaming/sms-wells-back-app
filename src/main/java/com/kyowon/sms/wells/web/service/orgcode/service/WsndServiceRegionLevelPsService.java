package com.kyowon.sms.wells.web.service.orgcode.service;

import com.kyowon.sms.wells.web.service.orgcode.dto.WsndServiceRegionLevelPsDto.*;
import com.kyowon.sms.wells.web.service.orgcode.mapper.WsndServiceRegionLevelPsMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class WsndServiceRegionLevelPsService {
    private final WsndServiceRegionLevelPsMapper mapper;

    public PagingResult<SearchRes> getServiceRegionLevelPsPaging(SearchReq dto, PageInfo pageInfo) {
        PagingResult<SearchRes> dtos = this.mapper.selectServiceRegionLevelPs(dto, pageInfo);
        return dtos;
    }

    public List<SearchRes> getServiceRegionLevelPsExcelDownload(SearchReq dto) {
        return mapper.selectServiceRegionLevelPs(dto);
    }
}
