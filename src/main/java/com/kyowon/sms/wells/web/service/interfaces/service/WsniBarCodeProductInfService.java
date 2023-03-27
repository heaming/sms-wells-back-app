package com.kyowon.sms.wells.web.service.interfaces.service;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.interfaces.dto.WsniBarCodeProductInfDto;
import com.kyowon.sms.wells.web.service.interfaces.mapper.WsniBarCodeProductInfMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WsniBarCodeProductInfService {

    private final WsniBarCodeProductInfMapper mapper;

    public WsniBarCodeProductInfDto.SearchRes getBarCodeProducts(
        String qrcd
    ) {
        return mapper.selectBarCodeProduct(qrcd);
    }
}
