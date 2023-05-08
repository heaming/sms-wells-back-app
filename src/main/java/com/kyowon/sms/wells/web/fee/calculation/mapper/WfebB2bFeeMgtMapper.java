package com.kyowon.sms.wells.web.fee.calculation.mapper;

import com.kyowon.sms.wells.web.fee.calculation.dto.WfebB2bFeeMgtDto.*;
import com.kyowon.sms.wells.web.fee.calculation.dvo.WfebB2bDtlFeeDvo;
import com.kyowon.sms.wells.web.fee.calculation.dvo.WfebB2bFeeDvo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WfebB2bFeeMgtMapper {
    List<Performance> selectB2bPerformance(SearchPerformanceReq req);
    List<Fee> selectB2bFee(SearchFeeReq req);
    int updateCalcFee(WfebB2bFeeDvo dvo);
    int updateCalcDtlFee(WfebB2bDtlFeeDvo dvo);

    int deleteAggregateNtorMmCl(CreateReq req);
    int deleteAggregateNtorCntrMmCl(CreateReq req);
    int deleteAggregateNtorPerfMmCl(CreateReq req);

    int insertAggregateNtorMmCl(CreateReq req);
    int insertAggregateNtorCntrMmCl(CreateReq req);
    int insertAggregateNtorPerfMmCl(CreateReq req);
    int insertAggregateNtorPerfPointMmCl(CreateReq req);
    int updateAggregateNtorMmCl(CreateReq req);

}
