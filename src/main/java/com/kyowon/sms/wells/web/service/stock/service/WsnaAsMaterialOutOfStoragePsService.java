package com.kyowon.sms.wells.web.service.stock.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.stock.converter.WsnaAsMaterialOutOfStoragePsConverter;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaAsMaterialOutOfStoragePsDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaAsMaterialOutOfStoragePsDto.SearchRes;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaAsMaterialOutOfStoragePsMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WsnaAsMaterialOutOfStoragePsService {

    private final WsnaAsMaterialOutOfStoragePsMapper mapper;

    private final WsnaAsMaterialOutOfStoragePsConverter converter;

    public List<SearchRes> getAsMaterialOutOfStoragePss(SearchReq dto) {
        return converter.mapDvoToSearchRes(mapper.selectAsMaterialOutOfStorages(dto));
    }
}
