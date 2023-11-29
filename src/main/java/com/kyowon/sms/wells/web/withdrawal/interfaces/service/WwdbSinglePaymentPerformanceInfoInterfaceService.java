package com.kyowon.sms.wells.web.withdrawal.interfaces.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.withdrawal.interfaces.dto.WwdbSinglePaymentPerformanceInfoInterfaceDto;
import com.kyowon.sms.wells.web.withdrawal.interfaces.mapper.WwdbSinglePaymentPerformanceInfoInterfaceMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WwdbSinglePaymentPerformanceInfoInterfaceService {

    private final WwdbSinglePaymentPerformanceInfoInterfaceMapper mapper;

    public List<WwdbSinglePaymentPerformanceInfoInterfaceDto.SearchRes> getSinglePaymentPerformanceInfos(
        WwdbSinglePaymentPerformanceInfoInterfaceDto.SearchReq dto
    ) {
        return mapper.selectSinglePaymentPerformanceInfos(dto);
    }
}
