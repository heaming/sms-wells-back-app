package com.kyowon.sms.wells.web.service.stock.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaWareItemReceivingAndPayingStateDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaWareItemReceivingAndPayingStateDto.SearchRes;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaWareItemReceivingAndPayingStateMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class WsnaWareItemReceivingAndPayingStateService {
    private final WsnaWareItemReceivingAndPayingStateMapper mapper;

    public List<SearchRes> getWareItemReceivingAndPayingStates(SearchReq dto) {
        return mapper.selectWareItemReceivingAndPayingStates(dto);
    }

}
