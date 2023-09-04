package com.kyowon.sms.wells.web.service.stock.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaFilterUseAgrgDto.FindFilterProductRes;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaFilterUseAgrgDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaFilterUseAgrgDto.SearchRes;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaFilterUseAgrgMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class WsnaFilterUseAgrgService {

    private final WsnaFilterUseAgrgMapper mapper;

    public List<FindFilterProductRes> getFilterProducts(SearchReq dto) {
        return mapper.selectFilterProducts(dto);
    }

    public List<SearchRes> getFilterUseAgrgs(SearchReq dto) {
        return mapper.selectFilterUseAgrgs(dto);
    }
}
