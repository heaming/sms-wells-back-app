package com.kyowon.sms.wells.web.fee.aggregate.service;

import java.util.List;

import com.kyowon.sms.wells.web.fee.aggregate.converter.WfeaNetOrderConverter;
import com.kyowon.sms.wells.web.fee.aggregate.dvo.WfeaNetOrderDvo;
import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.fee.aggregate.dto.WfeaNetOrderDto.*;
import com.kyowon.sms.wells.web.fee.aggregate.mapper.WfeaNetOrderMapper;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

/**
 * <pre>
 * WELLS 월순주문 집계 서비스
 * </pre>
 *
 * @author gs.piit150
 * @since 2023-02-03
 */

@Service
@RequiredArgsConstructor
public class WfeaNetOrderService {
    private final WfeaNetOrderMapper mapper;
    private final WfeaNetOrderConverter converter;;

    /**
     * WELLS 월순주문 집계 기본 조회
     * @param 'SearchReq' 검색조건 정보
     * @return 조회된 데이터
     */

    public List<SearchRes> getNetOrders(SearchReq dto) {
        return this.mapper.selectNetOrders(dto);
    }

    /**
     * WELLS 월순주문 수수료실적 집계대상 목록 조회
     * @param 'SearchReq' 검색조건 정보
     * @return 조회된 데이터
     */

    public List<SearchFeeRes> getNetOrderFees(SearchReq dto) {
        return this.mapper.selectNetOrderFees(dto);
    }

    /**
     * WELLS 월순주문 집계 생성
     * @param 'SaveReq' 삭제 및 저장 조건 정보
     * @return 저장 결과
     */

    @Transactional
    public int saveByNetOrders(SaveReq dto) {

        int processCount = 0;

        WfeaNetOrderDvo dvo = converter.mapSaveReqToWfeaNetOrderDvo(dto);

        mapper.deleteNetOrders(dvo);
        processCount = mapper.insertNetOrders(dvo);

        return processCount;
    }
}
