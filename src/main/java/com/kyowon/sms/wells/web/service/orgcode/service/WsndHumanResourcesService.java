package com.kyowon.sms.wells.web.service.orgcode.service;

import static com.kyowon.sms.wells.web.service.orgcode.dto.WsndHumanResourcesDto.SearchReq;
import static com.kyowon.sms.wells.web.service.orgcode.dto.WsndHumanResourcesDto.SearchRes;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.orgcode.mapper.WsndHumanResourcesMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

/**
 * @author Anonymous
 * @since 2023-12-31
 */
@Service
@RequiredArgsConstructor
public class WsndHumanResourcesService {
    private final WsndHumanResourcesMapper mapper;

    /**
     * 인사기본정보 조회
     * @param dto
     * @param pageInfo
     * @return
     */
    public PagingResult<SearchRes> getHumanResourcesPages(
        SearchReq dto, PageInfo pageInfo
    ) {
        return mapper.selectHumanResources(dto, pageInfo);
    }
}
