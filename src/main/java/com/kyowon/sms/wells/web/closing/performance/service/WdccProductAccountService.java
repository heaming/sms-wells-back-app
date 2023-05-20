package com.kyowon.sms.wells.web.closing.performance.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.closing.performance.dto.WdccProductAccountDto.SearchExcelRes;
import com.kyowon.sms.wells.web.closing.performance.dto.WdccProductAccountDto.SearchProductRes;
import com.kyowon.sms.wells.web.closing.performance.dto.WdccProductAccountDto.SearchReq;
import com.kyowon.sms.wells.web.closing.performance.dto.WdccProductAccountDto.SearchTotalRes;
import com.kyowon.sms.wells.web.closing.performance.mapper.WdccProductAccountMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class WdccProductAccountService {
    private final WdccProductAccountMapper mapper;

    public List<SearchTotalRes> getProductAccountTotals(SearchReq dto) {
        return mapper.selectProductAccountTotals(dto);
    }

    public List<SearchProductRes> getProductAccounts(SearchReq dto) {
        return mapper.selectProductAccounts(dto);
    }

    public List<SearchExcelRes> getProductAccountsExcelDownload(SearchReq dto) {
        return mapper.selectProductAccountsExcelDownload(dto);
    }
}
