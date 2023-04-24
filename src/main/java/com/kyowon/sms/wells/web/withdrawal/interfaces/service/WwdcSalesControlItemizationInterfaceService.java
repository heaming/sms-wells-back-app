package com.kyowon.sms.wells.web.withdrawal.interfaces.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.withdrawal.interfaces.dto.WwdcSalesControlItemizationInterfaceDto;
import com.kyowon.sms.wells.web.withdrawal.interfaces.mapper.WwdcSalesControlItemizationInterfaceMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WwdcSalesControlItemizationInterfaceService {

    private final WwdcSalesControlItemizationInterfaceMapper mapper;

    public List<WwdcSalesControlItemizationInterfaceDto.SearchRes> getSalesControlItemizations(
        WwdcSalesControlItemizationInterfaceDto.SearchReq dto
    ) {
        return mapper.selectSalesControlItemizations(dto);
    }
}
