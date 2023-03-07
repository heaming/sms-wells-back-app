package com.kyowon.sms.wells.web.fee.aggregate.service;

import static com.kyowon.sms.wells.web.fee.aggregate.dto.WfeaFilterMgtFeeDto.SearchReq;
import static com.kyowon.sms.wells.web.fee.aggregate.dto.WfeaFilterMgtFeeDto.SearchRes;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.fee.aggregate.mapper.WfeaFilterMgtFeeMapper;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * 필터관리수수료 현황
 * </pre>
 *
 * @author haejin.lee
 * @since 2023-02-28
 */
@Service
@RequiredArgsConstructor
public class WfeaFilterMgtFeeService {

    private final WfeaFilterMgtFeeMapper mapper;

    /**
     * 필터관리수수료 현황 목록 조회
     * @param 'SearchReq' 검색조건 정보
     * @return 조회된 데이터
     */
    public List<SearchRes> getFilterMgtFees(SearchReq dto) {
        return this.mapper.selectFilterMgtFees(dto);
    }
}
