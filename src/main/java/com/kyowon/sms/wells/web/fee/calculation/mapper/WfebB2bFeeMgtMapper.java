package com.kyowon.sms.wells.web.fee.calculation.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.fee.calculation.dto.WfebB2bFeeMgtDto.*;
import com.kyowon.sms.wells.web.fee.calculation.dvo.WfebB2bDtlFeeDvo;
import com.kyowon.sms.wells.web.fee.calculation.dvo.WfebB2bFeeDvo;

@Mapper
public interface WfebB2bFeeMgtMapper {
    /* B2b수수료 생성관리 조회 */
    // 1. 실적
    List<Performance> selectB2bPerformance(SearchPerformanceReq req);

    // 2. 수수료
    List<Fee> selectB2bFee(SearchFeeReq req);

    /* B2b수수료 생성관리 저장 */
    // W040005:알선수수료, W040004:프로모션, W040020:재지급, W040003:인센티브 저장
    int updateCalcFee(WfebB2bFeeDvo dvo);

    // 공제 저장
    int updateCalcDtlFee(WfebB2bDtlFeeDvo dvo);

    /* B2b수수료 생성관리 */
    // 01-1. 순주문파트너월마감 삭제
    int deleteAggregateNtorMmCl(CreateReq req);

    // 01-2. 순주문파트너실적월마감 삭제 (개인, 조직)
    int deleteAggregateNtorCntrMmCl(CreateReq req);

    // 01-3. 순주문파트너계약월마감 삭제
    int deleteAggregateNtorPerfMmCl(CreateReq req);

    // 01-4. 웰스순주문계약월마감 삭제
    int deleteWelsNetOrders(CreateReq req);

    // 02. B2B 순주문파트너월마감 생성
    int insertAggregateNtorMmCl(CreateReq req);

    // 03. 웰스순주문계약월마감 생성
    int insertEtcNetOrders(CreateReq req);

    // 04. B2B 순주문파트너계약월마감 생성
    int insertAggregateNtorCntrMmCl(CreateReq req);

    // 05. B2B 순주문파트너실적월마감 생성
    int insertOgAggregateNtorPerfMmCl(CreateReq req);

    // 05-1. 순주문파트너실적월마감 - 개인판매 생성
    int insertAggregateNtorPerfMmCl(CreateReq req);

    // 05-2. 순주문파트너실적월마감 - 조직 생성
    int insertAggregateNtorPerfPointMmCl(CreateReq req);

    //  06-2. 순주문파트너월마감 확정여부 체크
    int selectCheckB2bConfrim(CreateReq req);

    /* 미사용 */
    int updateAggregateNtorMmCl(CreateReq req);

}
