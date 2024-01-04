package com.kyowon.sms.wells.web.fee.calculation.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WfebAgainDisbursementFeeMapper {
    /**
     * P조직/M조직 상조 연체 재지급 계약데이터 생성
     * @param baseYm
     * @param ogTpCd
     * @param cntrPerfCrtDvCd
     * @param feeRedfAdsbDtlId
     * @return
     */
    Integer insertLifeContractDlqAdsbsForPog(String baseYm, String ogTpCd, String cntrPerfCrtDvCd, String feeRedfAdsbDtlId, String plarIndvFeeCd, String brmgrIndvFeeCd, String brmgrOgFeeCd);

    /**
     * M조직 연체재지급 계약 데이터 생성
     * @param baseYm 기준년월
     * @param ogTpCd 조직유형코드
     * @param cntrPerfCrtDvCd 계약실적생성구분코드
     * @param feeRedfAdsbDtlId 수수료되물림재지급상세ID
     * @return
     */
    Integer insertContractDlqAdsbsForMog(String baseYm, String ogTpCd, String cntrPerfCrtDvCd, String feeRedfAdsbDtlId);
    /**
     * 총판 기본수수료 연체재지급 계약 데이터 생성
     * @param baseYm 기준년월
     * @param ogTpCd 조직유형코드
     * @param cntrPerfCrtDvCd 계약실적생성구분코드
     * @param feeRedfAdsbDtlId 수수료되물림재지급상세ID
     * @return
     */
    Integer insertBaseContractDlqAdsbs(String baseYm, String ogTpCd, String cntrPerfCrtDvCd, String feeRedfAdsbDtlId);
}
