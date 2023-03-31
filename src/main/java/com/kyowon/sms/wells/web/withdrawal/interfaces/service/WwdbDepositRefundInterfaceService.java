package com.kyowon.sms.wells.web.withdrawal.interfaces.service;

import com.kyowon.sms.wells.web.withdrawal.interfaces.dto.WwdbDepositRefundInterfaceDto;
import com.kyowon.sms.wells.web.withdrawal.interfaces.mapper.WwdbDepositRefundInterfaceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WwdbDepositRefundInterfaceService {

    private final WwdbDepositRefundInterfaceMapper mapper;

    public List<WwdbDepositRefundInterfaceDto.SearchRes> getDepositRefunds(WwdbDepositRefundInterfaceDto.SearchReq dto) {
        return mapper.selectDepositRefunds(dto);
    }
}
