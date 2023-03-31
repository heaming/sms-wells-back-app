package com.kyowon.sms.wells.web.withdrawal.interfaces.service;

import com.kyowon.sms.wells.web.withdrawal.interfaces.dto.WwdbDepositRefundInterfaceDto;
import com.kyowon.sms.wells.web.withdrawal.interfaces.dto.WwdbSinglePaymentPerformanceInfoInterfaceDto;
import com.kyowon.sms.wells.web.withdrawal.interfaces.mapper.WwdbSinglePaymentPerformanceInfoInterfaceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WwdbSinglePaymentPerformanceInfoInterfaceService {

    private final WwdbSinglePaymentPerformanceInfoInterfaceMapper mapper;

    public List<WwdbSinglePaymentPerformanceInfoInterfaceDto.SearchRes> getSinglePaymentPerformanceInfos(WwdbSinglePaymentPerformanceInfoInterfaceDto.SearchReq dto) {
        return mapper.selectSinglePaymentPerformanceInfos(dto);
    }
}
