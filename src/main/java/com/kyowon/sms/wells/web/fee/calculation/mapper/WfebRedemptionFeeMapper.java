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
    Integer insertLifeContractRedf(String baseYm, String ogTpCd, String feeRedfAdsbDtlId, String plarIndvFeeCd, String brmgrIndvFeeCd, String brmgrOgFeeCd);

    /**
     * M조직 연체되물림 계약데이터 생성
     * 되물림 기준년월에 2, 4직급이면 되물림 제외
     * @param baseYm
     * @param cntrPerfCrtDvCd
     * @param feeRedfAdsbDtlId
     * @return
     */
    Integer insertContractDlqRedf(String baseYm, String cntrPerfCrtDvCd, String feeRedfAdsbDtlId);

    /**
     * 총판 기본수수료 연체되물림 계약데이터 생성
     * @param baseYm
     * @param cntrPerfCrtDvCd
     * @param feeRedfAdsbDtlId
     * @return
     */
    Integer insertBaseContractDlqRedf(String baseYm, String cntrPerfCrtDvCd, String feeRedfAdsbDtlId, String ogTpCd);
}
