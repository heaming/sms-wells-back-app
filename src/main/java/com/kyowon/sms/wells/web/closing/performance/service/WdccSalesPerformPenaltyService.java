package com.kyowon.sms.wells.web.closing.performance.service;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.closing.performance.dto.WdccSalesPerformPenaltyDto.SearchReq;
import com.kyowon.sms.wells.web.closing.performance.dto.WdccSalesPerformPenaltyDto.SearchRes;
import com.kyowon.sms.wells.web.closing.performance.mapper.WdccSalesPerformPenaltyMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WdccSalesPerformPenaltyService {
    private final WdccSalesPerformPenaltyMapper mapper;

    /**
     * 위약금예상 - 조회
     * @param dto 검색조건
     * @return result 조회결과
     */
    public SearchRes getPerformPenalty(
        SearchReq dto
    ) {
        return mapper.selectPerformPenalty(dto);
    }
}
