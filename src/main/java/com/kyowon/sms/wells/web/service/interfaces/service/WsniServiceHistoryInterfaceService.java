package com.kyowon.sms.wells.web.service.interfaces.service;

import static com.kyowon.sms.wells.web.service.interfaces.dto.WsniServiceHistoryInterfaceDto.SearchReq;
import static com.kyowon.sms.wells.web.service.interfaces.dto.WsniServiceHistoryInterfaceDto.SearchRes;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.interfaces.converter.WsniServiceHistoryInterfaceConverter;
import com.kyowon.sms.wells.web.service.interfaces.dvo.WsniServiceHistoryInterfaceDvo;
import com.kyowon.sms.wells.web.service.interfaces.mapper.WsniServiceHistoryInterfaceMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class WsniServiceHistoryInterfaceService {

    private final WsniServiceHistoryInterfaceMapper mapper;
    private final WsniServiceHistoryInterfaceConverter converter;

    public List<SearchRes> getServiceHistory(SearchReq dto) {
        List<WsniServiceHistoryInterfaceDvo> dvos = mapper.selectServiceHistory(dto);
        return converter.mapAllServiceHistoryInterfaceDvoToSearchRes(dvos);
    }
}
