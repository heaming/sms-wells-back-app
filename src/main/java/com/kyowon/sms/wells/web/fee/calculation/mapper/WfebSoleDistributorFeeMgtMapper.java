package com.kyowon.sms.wells.web.fee.calculation.mapper;

import com.kyowon.sms.wells.web.fee.calculation.dto.WfebSoleDistributorFeeMgtDto.*;
import com.kyowon.sms.wells.web.fee.calculation.dvo.WfebSoleDistributorFeeDvo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WfebSoleDistributorFeeMgtMapper {
    List<Performance> selectDistributorPerformance(SearchPerformanceReq req);
    List<Fee> selectDistributorFee(SearchFeeReq req);
    int updateCalcFee(WfebSoleDistributorFeeDvo dvo);


    int selectCheckSoleConfrim(CreateReq req);
    int deleteAggregateNtorMmCl(CreateReq req);
    int deleteAggregateNtorCntrMmCl(CreateReq req);
    int deleteAggregateNtorPerfMmCl(CreateReq req);

    int insertAggregateNtorMmCl(CreateReq req);
    int insertAggregateNtorCntrMmCl(CreateReq req);
    int insertAggregateNtorPerfMmCl(CreateReq req);
    int insertOgAggregateNtorPerfMmCl(CreateReq req);

    int insertAggregateNtorPerfPointMmCl(CreateReq req);
    int updateAggregateNtorMmCl(CreateReq req);

}
