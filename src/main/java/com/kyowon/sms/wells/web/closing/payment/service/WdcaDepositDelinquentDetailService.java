package com.kyowon.sms.wells.web.closing.payment.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.closing.payment.dto.WdcaDepositDelinquentDetailDto.SearchContractReq;
import com.kyowon.sms.wells.web.closing.payment.dto.WdcaDepositDelinquentDetailDto.SearchContractRes;
import com.kyowon.sms.wells.web.closing.payment.dto.WdcaDepositDelinquentDetailDto.SearchReq;
import com.kyowon.sms.wells.web.closing.payment.dto.WdcaDepositDelinquentDetailDto.SearchRes;
import com.kyowon.sms.wells.web.closing.payment.mapper.WdcaDepositDelinquentDetailMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class WdcaDepositDelinquentDetailService {
    private final WdcaDepositDelinquentDetailMapper mapper;

    public List<SearchRes> getDepositDelinquentDetails(SearchReq dto) {
        return mapper.selectDepositDelinquentDetails(dto);
    }

    public PagingResult<SearchContractRes> getDepositDelinquentContractPages(SearchContractReq dto, PageInfo pageInfo) {
        return mapper.selectDepositDelinquentContractPages(dto, pageInfo);
    }

    public List<SearchContractRes> getDepositDelinquentContractForExcelDownload(SearchContractReq dto) {
        return mapper.selectDepositDelinquentContractPages(dto);
    }
}
