package com.kyowon.sms.wells.web.fee.calculation.mapper;

import com.kyowon.sms.wells.web.fee.calculation.dto.WfebSoleDistributorFeeMgtDto.CreateReq;
import com.kyowon.sms.wells.web.fee.calculation.dto.WfebSoleDistributorFeeMgtDto.Fee;
import com.kyowon.sms.wells.web.fee.calculation.dto.WfebSoleDistributorFeeMgtDto.Performance;
import com.kyowon.sms.wells.web.fee.calculation.dto.WfebSoleDistributorFeeMgtDto.SearchBaseReq;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WfebSoleDistributorFeeMgtMapper {
    List<Performance> selectDistributorPerformance(SearchBaseReq req);
    List<Fee> selectDistributorFee(SearchBaseReq req);

    int deleteAggregateNtorMmCl(CreateReq req);
    int deleteAggregateCntrMmCl(CreateReq req);
    int deleteAggregatePerfMmCl(CreateReq req);
    int insertAggregateNtorMmCl(CreateReq req);
    int insertAggregateCntrMmCl(CreateReq req);
    int insertAggregatePerfMmCl1(CreateReq req);
    int insertAggregatePerfMmCl2(CreateReq req);
    int updateAggregateNtorMmCl(CreateReq req);
}
