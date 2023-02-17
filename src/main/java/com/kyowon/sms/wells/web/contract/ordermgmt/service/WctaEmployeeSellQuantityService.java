package com.kyowon.sms.wells.web.contract.ordermgmt.service;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaEmployeeSellQuantityDto;
import com.kyowon.sms.wells.web.contract.ordermgmt.mapper.WctaEmployeeSellQuantityMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctaEmployeeSellQuantityService {
    private final WctaEmployeeSellQuantityMapper mapper;

    public WctaEmployeeSellQuantityDto.SearchRes getEmployeeSellQuantity(WctaEmployeeSellQuantityDto.SearchReq dto) {
        return mapper.selectEmployeeSellQuantity(dto);
    }
}
