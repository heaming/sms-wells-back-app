package com.kyowon.sms.wells.web.service.stock.service;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaOutOfStorageIzDtlDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaOutOfStorageIzDtlDto.SearchRes;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaOutOfStorageIzDtlMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WsnaOutOfStorageIzDtlService {

    private final WsnaOutOfStorageIzDtlMapper mapper;

    public PagingResult<SearchRes> getOutOfStorageIzDtls(SearchReq dto, PageInfo pageInfo) {
        return mapper.getOutOfStorageIzDtls(dto, pageInfo);
    }
}
