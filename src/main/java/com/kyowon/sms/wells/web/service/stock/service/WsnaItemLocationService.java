package com.kyowon.sms.wells.web.service.stock.service;

import java.util.List;

import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.stock.converter.WsnaItemLocationConverter;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaItemLocationDto.CreateReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaItemLocationDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaItemLocationDto.SearchRes;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaItemLocationDvo;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaItemLocationMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WsnaItemLocationService {
    private final WsnaItemLocationMapper mapper;
    private final WsnaItemLocationConverter converter;

    public PagingResult<SearchRes> getItemLocations(SearchReq dto, PageInfo pageInfo) {
        return mapper.selectItemLocations(dto, pageInfo);
    }

    public List<SearchRes> getItemLocations(SearchReq dto) {
        return mapper.selectItemLocations(dto);
    }

    public int saveItemLocations(List<CreateReq> list) {
        List<WsnaItemLocationDvo> voList = converter.mapAllWsnaItemLocationDvos(list);
        return mapper.saveItemLocations(voList);
    }
}
