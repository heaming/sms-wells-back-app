package com.kyowon.sms.wells.web.fee.aggregate.service;

import java.util.List;

import com.kyowon.sms.wells.web.fee.aggregate.converter.WfeaFeeBaseAmountConverter;
import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.fee.aggregate.dto.WfeaFeeBaseAmountDto.*;
import com.kyowon.sms.wells.web.fee.aggregate.mapper.WfeaFeeBaseAmountMapper;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * WELLS 수수료 기준금액 체크리스트 서비스
 * </pre>
 *
 * @author gs.piit150
 * @since 2023-02-03
 */
@Service
@RequiredArgsConstructor
public class WfeaFeeBaseAmountService {
    private final WfeaFeeBaseAmountMapper mapper;
    private final WfeaFeeBaseAmountConverter converter;;

    /**
     * WELLS 수수료 기준금액 체크리스트 목록 조회
     * @param 'SearchReq' 검색조건 정보
     * @return 조회된 데이터
     */
    public List<SearchRes> getFeeBaseAmounts(SearchReq dto) {
        return this.mapper.selectFeeBaseAmounts(dto);
    }

}
