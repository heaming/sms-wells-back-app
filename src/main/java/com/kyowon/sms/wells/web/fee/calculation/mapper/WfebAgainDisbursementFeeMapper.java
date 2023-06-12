package com.kyowon.sms.wells.web.fee.calculation.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WfebAgainDisbursementFeeMapper {
    Integer deleteCommonDlqAdsbs(String baseYm, String cntrPerfCrtDvCd, String tableName);
    Integer insertContractDlqAdsbs(String baseYm, String cntrPerfCrtDvCd, String feeRedfAdsbDtlId);
    Integer insertContractDlqAdsbHistories(String baseYm, String cntrPerfCrtDvCd);
    Integer insertPartnerDlqAdsbHistories(String baseYm, String cntrPerfCrtDvCd);
    Integer insertDlqAdsbs(String baseYm, String cntrPerfCrtDvCd, String feeRedfAdsbId);
    Integer updatePartnerIdCntrDlqAdsbs(String baseYm, String cntrPerfCrtDvCd);
}
