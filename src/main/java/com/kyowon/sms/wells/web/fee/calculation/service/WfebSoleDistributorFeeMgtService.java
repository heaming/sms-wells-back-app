package com.kyowon.sms.wells.web.fee.calculation.service;

import com.kyowon.sms.wells.web.fee.calculation.dto.WfebSoleDistributorFeeMgtDto.CreateReq;
import com.kyowon.sms.wells.web.fee.calculation.dto.WfebSoleDistributorFeeMgtDto.Fee;
import com.kyowon.sms.wells.web.fee.calculation.dto.WfebSoleDistributorFeeMgtDto.Performance;
import com.kyowon.sms.wells.web.fee.calculation.dto.WfebSoleDistributorFeeMgtDto.SearchBaseReq;
import com.kyowon.sms.wells.web.fee.calculation.mapper.WfebSoleDistributorFeeMgtMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <pre>
 * 총판 수수료 생성관리
 * </pre>
 *
 * @author mj
 * @since 2023.04.20
 */
@Service
@RequiredArgsConstructor
public class WfebSoleDistributorFeeMgtService {

    private final WfebSoleDistributorFeeMgtMapper mapper;

    /**
     * 총판수수료 생성관리 조회 - 수수료 실적
     * @param req
     * @return
     */
    public List<Performance> getDistributorPerformance(SearchBaseReq req) {
        // todo 실적테이블로 변경후 쿼리 수정
        return mapper.selectDistributorPerformance(req);
    }

    /**
     * 총판수수료 생성관리 조회 - 수수료
     * @param req
     * @return
     */
    public List<Fee> getDistributorFee(SearchBaseReq req) {
         return mapper.selectDistributorFee(req);
    }

    /**
     * 총판수수료 생성관리 저장(수수료)
     * @param listFees
     * @return
     */
    @Transactional
    public int editDistributorFee(List<Fee> listFees) {
        int processCount = 0;
        // todo 미정의
        return processCount;
    }
   /**
     * 총판수수료 생성관리 - 재시작
     * @param req
     * @return
     */
    @Transactional
    public int retryDistributorPerformance(CreateReq req) {
        int processCount = 0;
        // todo 미정의
        return processCount;
    }

    /**
     * 총판수수료 생성관리 - 집계
     * @param req
     * @return
     */
    @Transactional
    public int aggregateDistributorPerformance(CreateReq req) {
        int processCount = 0;
        // 01. 순주문월마감 삭제
        processCount += mapper.deleteAggregateNtorMmCl(req);
        // 02. 웰스순주문계약월마감 삭제
        processCount += mapper.deleteAggregateNtorMmCl(req);
        // 03. 순주문파트너실적월마감 삭제
        processCount += mapper.deleteAggregatePerfMmCl(req);
        // 04. 순주문파트너월마감 등록
        processCount += mapper.insertAggregateNtorMmCl(req);
        // 05. 순주문파트너계약월마감 등록
        processCount += mapper.insertAggregateCntrMmCl(req);
        // 06. 순주문파트너계약월마감 개인판매 등록
        processCount += mapper.insertAggregatePerfMmCl1(req);
        // 07. 순주문파트너계약월마감 지점(지국)판매 등록
        processCount += mapper.insertAggregatePerfMmCl2(req);
        // 08. 순주문파트너월마감 상태 변경
        processCount += mapper.updateAggregateNtorMmCl(req);
        return processCount;
    }

}
