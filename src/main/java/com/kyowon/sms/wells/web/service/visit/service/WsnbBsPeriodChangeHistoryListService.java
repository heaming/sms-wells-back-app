package com.kyowon.sms.wells.web.service.visit.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbBsPeriodChangeHistoryListDto.SearchReq;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbBsPeriodChangeHistoryListDto.SearchRes;
import com.kyowon.sms.wells.web.service.visit.mapper.WsnbBsPeriodChangeHistoryListMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WsnbBsPeriodChangeHistoryListService {
    private final WsnbBsPeriodChangeHistoryListMapper mapper;

    public PagingResult<SearchRes> getBsPeriodChangeHistoryList(SearchReq dto, PageInfo pageInfo) {
        return mapper.selectBsPeriodChangeHistoryList(dto, pageInfo);
    }

    public List<SearchRes> getBsPeriodChangeHistoryListForExcelDownload(SearchReq dto) {
        return mapper.selectBsPeriodChangeHistoryList(dto);
    }

    // getBsPeriodChangeHistoryListForExcelDownload
}
