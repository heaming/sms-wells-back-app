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
     * WELLS 월순주문 집계 데이터 조회
     * @param 'SearchReq' 검색조건 정보
     * @return 조회된 데이터
     */

    public List<SearchRes> getNetOrders(SearchReq dto) {
        return this.mapper.selectNetOrders(dto);
    }

    /**
     * WELLS 월순주문 집계 데이터 조회
     * @param 'SearchReq' 검색조건 정보
     * @return 조회된 데이터
     */

    public List<SearchRes> getNetAggreateOrders(SearchReq dto) {
        return this.mapper.selectAggreateNetOrders(dto);
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
     * WELLS 월순주문 수수료실적 집계 확정여부 조회
     * @param 'SearchReq' 검색조건 정보
     * @return 조회된 데이터
     */

    public SearchConfirmRes getNetAggregateConfirm(SearchReq dto) {
        return this.mapper.selectNetAggregateConfirm(dto);
    }

    /**
     * WELLS 월순주문 집계 생성
     * @param 'SaveReq' 삭제 및 저장 조건 정보
     *        1.계약별집계정보 삭제
     *        2.매니저 계약별 집계
     *        3.플래너 계약별 집계
     *        4.홈마스터 계약별 집계
     * @return 저장 결과
     */

    @Transactional
    public int saveByNetOrders(SaveReq dto) {

        int processCount = 0;

        WfeaNetOrderDvo dvo = converter.mapSaveReqToWfeaNetOrderDvo(dto);

        mapper.deleteNetOrders(dvo);
        mapper.deleteWelsNetOrders(dvo);
        processCount += mapper.insertManagerNetOrders(dvo);
        processCount += mapper.insertPlannerNetOrders(dvo);
        processCount += mapper.insertHomeMasterNetOrders(dvo);
        if (processCount > 0) {
            mapper.insertNetOrder(dvo);
        }

        return processCount;
    }

    /**
     * WELLS 월순주문 집계 확정
     * @param 'SaveReq' 확정정보 수정 조건 정보
     * @return 저장 결과
     */

    @Transactional
    public int updateByNetOrders(SaveReq dto) {

        int processCount = 0;

        WfeaNetOrderDvo dvo = converter.mapSaveReqToWfeaNetOrderDvo(dto);

        processCount = mapper.updateNetOrders(dvo);

        return processCount;
    }
}
