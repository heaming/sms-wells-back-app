package com.kyowon.sms.wells.web.service.interfaces.service;

import com.kyowon.sms.wells.web.service.interfaces.dto.WsniFilterChangeExpectedInterfaceDto.SearchReq;
import com.kyowon.sms.wells.web.service.interfaces.dto.WsniFilterChangeExpectedInterfaceDto.SearchRes;

import com.kyowon.sms.wells.web.service.interfaces.mapper.WsniFilterChangeExpectedInterfaceMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class WsniFilterChangeExpectedInterfaceService {
    private final WsniFilterChangeExpectedInterfaceMapper mapper;

    public List<SearchRes> getFilterChangeExpectedInfos(SearchReq dto) {
        return mapper.selectFilterChangeExpectedInfos(dto);
    }
}
