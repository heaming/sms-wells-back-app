package com.kyowon.sms.wells.web.closing.payment.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.closing.payment.dto.WdcaBusinessDepositDelinquentDto.SearchReq;
import com.kyowon.sms.wells.web.closing.payment.dto.WdcaBusinessDepositDelinquentDto.SearchRes;
import com.kyowon.sms.wells.web.closing.payment.mapper.WdcaBusinessDepositDelinquentMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class WdcaBusinessDepositDelinquentService {
    private final WdcaBusinessDepositDelinquentMapper mapper;

    /**
     * 영업부 입금/연체 현황 조회
     * @param dto
     * @return
     */
    public PagingResult<SearchRes> getBusinessDepositDelinquentPages(SearchReq dto, PageInfo pageInfo) {
        return mapper.selectBusinessDepositDelinquentPages(dto, pageInfo);
    }

    /**
     * 영업부 입금/연체 현황 조회 엑셀다운로드
     * @param dto
     * @return
     */
    public List<SearchRes> getBusinessDepositDelinquentForExcelDownload(SearchReq dto) {
        return mapper.selectBusinessDepositDelinquentPages(dto);
    }
}
