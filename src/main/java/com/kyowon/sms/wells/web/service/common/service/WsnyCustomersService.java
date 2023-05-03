package com.kyowon.sms.wells.web.service.common.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.common.dto.WsnyCustomersDto.SearchReq;
import com.kyowon.sms.wells.web.service.common.dto.WsnyCustomersDto.SearchRes;
import com.kyowon.sms.wells.web.service.common.mapper.WsnyCustomersMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WsnyCustomersService {
    private final WsnyCustomersMapper mapper;

    public List<SearchRes> getCustomers(SearchReq dto) {
        return mapper.selectCustomerBases(dto);
    }

    public PagingResult<SearchRes> getCustomerBases(SearchReq dto, PageInfo pageInfo) {
        return mapper.selectCustomers(dto, pageInfo);
    }
}
