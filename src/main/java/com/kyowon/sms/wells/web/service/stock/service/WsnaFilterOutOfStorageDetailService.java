package com.kyowon.sms.wells.web.service.stock.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaFilterOutOfStorageDetailDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaFilterOutOfStorageDetailDto.SearchRes;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaFilterOutOfStorageDetailMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class WsnaFilterOutOfStorageDetailService {

    private final WsnaFilterOutOfStorageDetailMapper mapper;

    public PagingResult<SearchRes> getFilterOutOfStorageDetailPages(SearchReq dto, PageInfo pageInfo) {
        return mapper.selectFilterOutOfStorageDetails(dto, pageInfo);
    }

    public List<SearchRes> getFilterOutOfStorageDetailsExcelDownload(SearchReq dto) {
        return mapper.selectFilterOutOfStorageDetails(dto);
    }

}
