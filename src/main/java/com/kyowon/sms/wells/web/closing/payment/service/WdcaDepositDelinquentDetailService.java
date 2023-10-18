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

    /**
     * 입금 연체 상세 조회
     * @param dto
     * @return
     */
    public List<SearchRes> getDepositDelinquentDetails(SearchReq dto) {
        return mapper.selectDepositDelinquentDetails(dto);
    }

    /**
     * 입금 연체 상세-계약별 상세조회
     * @param dto
     * @return
     */
    public PagingResult<SearchContractRes> getDepositDelinquentContractPages(SearchContractReq dto, PageInfo pageInfo) {
        return mapper.selectDepositDelinquentContractPages(dto, pageInfo);
    }

    /**
     * 입금 연체 상세-계약별 상세조회 엑셀 다운로드
     * @param dto
     * @return
     */
    public List<SearchContractRes> getDepositDelinquentContractForExcelDownload(SearchContractReq dto) {
        return mapper.selectDepositDelinquentContractPages(dto);
    }
}
