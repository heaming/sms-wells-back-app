package com.kyowon.sms.wells.web.closing.sales.service;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.closing.sales.dto.WdcbPrepaidDepositRentalDto.SearchReq;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbPrepaidDepositRentalDto.SearchRes;
import com.kyowon.sms.wells.web.closing.sales.mapper.WdcbPrepaidDepositRentalMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class WdcbPrepaidDepositRentalService {
    private final WdcbPrepaidDepositRentalMapper mapper;

    public SearchRes getPrepaidDepositRental(SearchReq dto) {
        return mapper.selectPrepaidDepositRental(dto);
    }

}
