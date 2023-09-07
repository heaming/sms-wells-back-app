package com.kyowon.sms.wells.web.service.visit.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbSnProcessingAgrgDto.SearchReq;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbSnProcessingAgrgDto.SearchRgrpRes;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbSnProcessingAgrgDto.SearchSnRes;
import com.kyowon.sms.wells.web.service.visit.mapper.WsnbSnProcessingAgrgMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * <pre>
 * K-W-SV-U-0079M01 S/N 처리 집계
 * </pre>
 *
 * @author heymi.cho
 * @since 2023.08.18
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class WsnbSnProcessingAgrgService {
    private final WsnbSnProcessingAgrgMapper mapper;

    public PagingResult<SearchSnRes> getSnProcessingAgrgBySn(SearchReq dto, PageInfo pageInfo) {
        return mapper.selectSnProcessingAgrgBySn(dto, pageInfo);
    }

    public List<SearchSnRes> getSnProcessingAgrgBySn(SearchReq dto) {
        return mapper.selectSnProcessingAgrgBySn(dto);
    }

    public PagingResult<SearchRgrpRes> getSnProcessingAgrgByRgrp(SearchReq dto, PageInfo pageInfo) {
        return mapper.selectSnProcessingAgrgByRgrp(dto, pageInfo);
    }

    public List<SearchRgrpRes> getSnProcessingAgrgByRgrp(SearchReq dto) {
        return mapper.selectSnProcessingAgrgByRgrp(dto);
    }
}
