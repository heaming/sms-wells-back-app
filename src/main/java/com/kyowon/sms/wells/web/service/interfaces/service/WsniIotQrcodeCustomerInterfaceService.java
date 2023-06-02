package com.kyowon.sms.wells.web.service.interfaces.service;

import com.kyowon.sms.wells.web.service.interfaces.mapper.WsniIotQrcodeCustomerInterfaceMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.interfaces.dto.WsniIotQrcodeCustomerInterfaceDto.SearchReq;
import com.kyowon.sms.wells.web.service.interfaces.dto.WsniIotQrcodeCustomerInterfaceDto.SearchRes;

@Slf4j
@Service
@RequiredArgsConstructor
public class WsniIotQrcodeCustomerInterfaceService {
    private final WsniIotQrcodeCustomerInterfaceMapper mapper;

    public SearchRes getContractInfoByIotQrcode(SearchReq dto) {
        return mapper.selectContractInfoByIotQrcode(dto);
    }
}
