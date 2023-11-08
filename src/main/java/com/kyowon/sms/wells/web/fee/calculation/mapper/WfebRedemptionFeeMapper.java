package com.kyowon.sms.wells.web.fee.calculation.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WfebRedemptionFeeMapper {
    Integer insertContractDlqRedemptionOfFees(String baseYm, String cntrPerfCrtDvCd, String feeRedfAdsbDtlId);
}
