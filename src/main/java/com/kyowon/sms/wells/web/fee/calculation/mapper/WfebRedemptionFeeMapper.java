package com.kyowon.sms.wells.web.fee.calculation.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WfebRedemptionFeeMapper {
    /**
     * P조직 상조 연체되물림 계약데이터 생성
     * @param baseYm
     * @param cntrPerfCrtDvCd
     * @param feeRedfAdsbDtlId
     * @return
     */
    Integer insertContractLifeRedemptionOfFees(String baseYm, String cntrPerfCrtDvCd, String feeRedfAdsbDtlId);

    /**
     * M조직 연체되물림 계약데이터 생성
     * @param baseYm
     * @param cntrPerfCrtDvCd
     * @param feeRedfAdsbDtlId
     * @return
     */
    Integer insertContractDlqRedemptionOfFees(String baseYm, String cntrPerfCrtDvCd, String feeRedfAdsbDtlId);
}
