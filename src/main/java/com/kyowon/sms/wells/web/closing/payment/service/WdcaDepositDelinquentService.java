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

    /**
     * 입금 연체 현황 조회
     * @param dto
     * @return
     */
    public List<SearchRes> getDepositDelinquents(SearchReq dto) {
        return mapper.selectDepositDelinquents(dto);
    }

    /**
     * 입금 연체 현황 엑셀 다운로드
     * @param dto
     * @return
     */
    public List<SearchRes> getDepositDelinquentForExcelDownload(SearchReq dto) {
        return mapper.selectDepositDelinquents(dto);
    }
}
