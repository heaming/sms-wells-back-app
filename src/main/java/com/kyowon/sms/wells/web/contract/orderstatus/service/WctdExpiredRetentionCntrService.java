package com.kyowon.sms.wells.web.contract.orderstatus.service;

import static com.kyowon.sms.wells.web.contract.orderstatus.dto.WctdExpiredRetentionCntrDto.SearchReq;
import static com.kyowon.sms.wells.web.contract.orderstatus.dto.WctdExpiredRetentionCntrDto.SearchRes;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.contract.orderstatus.mapper.WctdExpiredRetentionCntrMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctdExpiredRetentionCntrService {
    private final WctdExpiredRetentionCntrMapper mapper;

    public PagingResult<SearchRes> getExpiredRetentionCntrPages(SearchReq dto, PageInfo pageInfo) {
        return mapper.selectExpiredRetentionCntrPages(dto, pageInfo);
    }

    public List<SearchRes> getExpiredRetentionCntrsForExcelDownload(SearchReq dto) {
        return mapper.selectExpiredRetentionCntrPages(dto);
    }
}
