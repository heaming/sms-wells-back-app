package com.kyowon.sms.wells.web.service.common.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.common.dto.WsnyProductListDto.SearchReq;
import com.kyowon.sms.wells.web.service.common.dto.WsnyProductListDto.SearchRes;
import com.kyowon.sms.wells.web.service.common.mapper.WsnyProductListMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WsnyProductListService {
    private final WsnyProductListMapper mapper;

    public List<SearchRes> getProductListByItmKndCd(SearchReq dto) {
        return mapper.selectProductListByItmKndCd(dto);
    }

}
