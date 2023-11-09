package com.kyowon.sms.wells.web.fee.calculation.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WfebAgainDisbursementFeeMapper {
    Integer insertContractDlqAdsbs(String baseYm, String ogTpCd, String cntrPerfCrtDvCd, String feeRedfAdsbDtlId);
}
