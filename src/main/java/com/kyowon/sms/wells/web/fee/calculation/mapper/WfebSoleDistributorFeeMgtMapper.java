package com.kyowon.sms.wells.web.fee.calculation.mapper;

import com.kyowon.sms.wells.web.fee.calculation.dto.WfebSoleDistributorFeeMgtDto.BaseReq;
import com.kyowon.sms.wells.web.fee.calculation.dto.WfebSoleDistributorFeeMgtDto.Fee;
import com.kyowon.sms.wells.web.fee.calculation.dto.WfebSoleDistributorFeeMgtDto.Performance;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WfebSoleDistributorFeeMgtMapper {
    List<Performance> selectDistributorPerformance(BaseReq req);
    List<Fee> selectDistributorFee(BaseReq req);
}
