package com.kyowon.sms.wells.web.service.allocate.service;

import static com.kyowon.sms.wells.web.service.allocate.dto.WsncBsEngineerAllocateSearchDto.*;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.allocate.mapper.WsncBsEngineerAllocateSearchMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WsncBsEngineerAllocateSearchService {

    private final WsncBsEngineerAllocateSearchMapper mapper;

    public PagingResult<SearchRes> getBsEngineerAllocateList(
        SearchReq dto, PageInfo pageInfo
    ) {
        return mapper.selectBsEngineerAllocateList(dto, pageInfo);
    }

    public List<SearchRes> getBsEngineerAllocateListExcelDownload(
        SearchReq dto
    ) {
        return mapper.selectBsEngineerAllocateList(dto);
    }

    public List<AggregateRes> getBsEngineerAllocateAggregate(
        SearchReq dto
    ) {
        return mapper.selectBsEngineerAllocateAggregate(dto);
    }
}
