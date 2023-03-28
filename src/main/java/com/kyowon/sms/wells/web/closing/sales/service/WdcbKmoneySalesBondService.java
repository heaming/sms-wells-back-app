package com.kyowon.sms.wells.web.closing.sales.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.closing.sales.dto.WdcbKmoneySalesBondDto.SearchBondRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbKmoneySalesBondDto.SearchCancelRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbKmoneySalesBondDto.SearchDepositRes;
import com.kyowon.sms.wells.web.closing.sales.mapper.WdcbKmoneySalesBondMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class WdcbKmoneySalesBondService {
    private final WdcbKmoneySalesBondMapper mapper;

    public PagingResult<SearchBondRes> getSalesBondPages(String baseYr, PageInfo pageInfo) {
        return mapper.selectSalesBondPages(baseYr, pageInfo);
    }

    public List<SearchBondRes> getSalesBondExcelDownload(String baseYr) {
        return mapper.selectSalesBondPages(baseYr);
    }

    public List<SearchDepositRes> getDepositDetails(String baseYm) {
        return mapper.selectDepositDetails(baseYm);
    }

    public List<SearchCancelRes> getCancelDetails(String baseYm) {
        return mapper.selectCancelDetails(baseYm);
    }
}
