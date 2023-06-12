package com.kyowon.sms.wells.web.fee.calculation.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WfebRedemptionFeeMapper {
    Integer deleteCommonDlqRedemptionOfFees(String baseYm, String cntrPerfCrtDvCd, String tableName);
    Integer insertContractDlqRedemptionOfFees(String baseYm, String cntrPerfCrtDvCd, String feeRedfAdsbDtlId);
    Integer insertDlqRedemptionOfFees(String baseYm, String cntrPerfCrtDvCd, String feeRedfAdsbId);
    Integer updatePrtnrIdCntrDlqRedemptionOfFees(String baseYm, String cntrPerfCrtDvCd);
    Integer insertContractDlqRedemptionOfFeeHistories(String baseYm, String cntrPerfCrtDvCd);
    Integer insertPartnerDlqRedemptionOfFeeHistories(String baseYm, String cntrPerfCrtDvCd);
}
