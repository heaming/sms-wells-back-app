package com.kyowon.sms.wells.web.service.interfaces.service;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.interfaces.dto.WsniRegistrationBarCodeDto;
import com.kyowon.sms.wells.web.service.interfaces.mapper.WsniRegistrationBarCodeMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class WsniRegistrationBarCodeService {
    private final WsniRegistrationBarCodeMapper mapper;

    public WsniRegistrationBarCodeDto.SearchRes getRegistrationBarCodes(
        WsniRegistrationBarCodeDto.SearchReq dto
    ) {
        return mapper.selectRegistrationBarCode(dto);
    }

    public WsniRegistrationBarCodeDto.SearchRes getRegistrationBarCodes(
        String qrcd
    ) {
        WsniRegistrationBarCodeDto.SearchReq dto = new WsniRegistrationBarCodeDto.SearchReq(qrcd);
        return getRegistrationBarCodes(dto);
    }

}
