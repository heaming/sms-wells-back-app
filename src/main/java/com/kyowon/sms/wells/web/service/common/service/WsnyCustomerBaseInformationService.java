package com.kyowon.sms.wells.web.service.common.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.common.mapper.WsnyCustomerBaseInformationMapper;
import com.kyowon.sms.wells.web.service.stock.dto.WsnyCustomerBaseInformationDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnyCustomerBaseInformationDto.SearchRes;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WsnyCustomerBaseInformationService {
    private final WsnyCustomerBaseInformationMapper mapper;

    public List<SearchRes> getCustomerBases(SearchReq dto) {
        return mapper.selectCustomerBases(dto);
    }

    public PagingResult<SearchRes> getCustomerBases(SearchReq dto, PageInfo pageInfo) {
        return mapper.selectCustomerBases(dto, pageInfo);
    }
}
