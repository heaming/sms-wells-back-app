package com.kyowon.sms.wells.web.fee.calculation.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.fee.calculation.dto.WfebSoleDistributorFeeMgtDto.*;
import com.kyowon.sms.wells.web.fee.calculation.dvo.WfebSoleDistributorFeeDvo;

@Mapper
public interface WfebSoleDistributorFeeMgtMapper {
    /* 총판수수료 생성관리 조회 */
    // 1. 실적
    List<Performance> selectDistributorPerformance(SearchPerformanceReq req);

    // 2. 수수료
    List<Fee> selectDistributorFee(SearchFeeReq req);

    /* 총판수수료 생성관리 저장(수수료) */
    int updateCalcFee(WfebSoleDistributorFeeDvo dvo);

    /* 총판수수료 생성관리 - 집계 */
    // 01-1. 순주문파트너월마감 삭제
    int deleteAggregateNtorMmCl(CreateReq req);

    // 01-2. 순주문파트너실적월마감 삭제 (개인, 조직)
    int deleteAggregateNtorCntrMmCl(CreateReq req);

    // 01-3. 순주문파트너계약월마감 삭제
    int deleteAggregateNtorPerfMmCl(CreateReq req);

    // 01-4. 웰스순주문계약월마감 삭제
    int deleteWelsNetOrders(CreateReq req);

    //  02. 총판 순주문파트너월마감 생성
    int insertAggregateNtorMmCl(CreateReq req);

    // 03. 웰스순주문계약월마감 생성
    int insertSodbtNetOrders(CreateReq req);

    // 04. 총판 순주문파트너계약월마감 생성
    int insertAggregateNtorCntrMmCl(CreateReq req);

    // 05-1. 순주문파트너실적월마감 - 개인판매 생성
    int insertAggregateNtorPerfMmCl(CreateReq req);

    // 05-2. 순주문파트너실적월마감 - 조직 생성
    int insertOgAggregateNtorPerfMmCl(CreateReq req);

    // 06. 순주문파트너월마감 확정여부 체크
    int selectCheckSoleConfrim(CreateReq req);

    /* 미사용 */
    int insertAggregateNtorPerfPointMmCl(CreateReq req);

    int updateAggregateNtorMmCl(CreateReq req);

}
