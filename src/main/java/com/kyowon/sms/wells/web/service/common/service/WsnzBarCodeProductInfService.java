package com.kyowon.sms.wells.web.service.common.service;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.common.dto.WsnzBarCodeProductInfDto;
import com.kyowon.sms.wells.web.service.common.mapper.WsnzBarCodeProductInfMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WsnzBarCodeProductInfService {

    private final WsnzBarCodeProductInfMapper mapper;

    public WsnzBarCodeProductInfDto.SearchRes getBarCodeProducts(
        WsnzBarCodeProductInfDto.SearchReq dto
    ) {
        return mapper.selectBarCodeProduct(dto);
    }

    public WsnzBarCodeProductInfDto.SearchRes getBarCodeProducts(
        String qrcd
    ) {
        WsnzBarCodeProductInfDto.SearchReq dto = new WsnzBarCodeProductInfDto.SearchReq(qrcd);
        return getBarCodeProducts(dto);
    }
}
