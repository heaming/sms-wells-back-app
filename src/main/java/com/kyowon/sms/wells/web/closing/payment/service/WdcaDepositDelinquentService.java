package com.kyowon.sms.wells.web.closing.payment.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.closing.payment.dto.WdcaDepositDelinquentDto.SearchReq;
import com.kyowon.sms.wells.web.closing.payment.dto.WdcaDepositDelinquentDto.SearchRes;
import com.kyowon.sms.wells.web.closing.payment.mapper.WdcaDepositDelinquentMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class WdcaDepositDelinquentService {
    private final WdcaDepositDelinquentMapper mapper;

    public List<SearchRes> getDepositDelinquents(SearchReq dto) {
        return mapper.selectDepositDelinquents(dto);
    }

    public List<SearchRes> getDepositDelinquentForExcelDownload(SearchReq dto) {
        return mapper.selectDepositDelinquents(dto);
    }
}
