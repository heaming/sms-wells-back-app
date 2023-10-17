package com.kyowon.sms.wells.web.service.allocate.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncExpProductDto.SearchReq;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncExpProductDto.SearchRes;
import com.kyowon.sms.wells.web.service.allocate.mapper.WsncExpProductMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class WsncExpProductService {
    private final WsncExpProductMapper mapper;

    public List<SearchRes> getExpProducts(SearchReq req) {
        return mapper.selectExpProduct(req);
    }

}
