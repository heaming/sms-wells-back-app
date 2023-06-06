package com.kyowon.sms.wells.web.service.interfaces.service;

import com.kyowon.sms.wells.web.service.interfaces.dto.WsniCenterHouseholdInterfaceDto.SearchReq;
import com.kyowon.sms.wells.web.service.interfaces.dto.WsniCenterHouseholdInterfaceDto.SearchRes;
import com.kyowon.sms.wells.web.service.interfaces.mapper.WsniCenterHouseholdInterfaceMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class WsniCenterHouseholdInterfaceService {
    private final WsniCenterHouseholdInterfaceMapper mapper;

    public List<SearchRes> getCustomerHouseholds(SearchReq dto) {
        return mapper.selectCustomerHouseholds(dto);
    }
}
