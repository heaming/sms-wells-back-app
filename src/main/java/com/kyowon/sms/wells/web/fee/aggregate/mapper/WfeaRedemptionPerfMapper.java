package com.kyowon.sms.wells.web.fee.aggregate.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WfeaRedemptionPerfMapper {

    /**
     * 되물림대상 계약에서 되물림 관련 실적항목코드별로 생성
     * @param baseYm
     * @return
     */
    int insertContractDataFromNtorp(String baseYm, String ogTpCd, String cntrPerfCrtDvCd, String perfAgrgCrtDvCd, List<String> perfAtcCds);

    /**
     * P조직 개인 라이프 상조 되물림대상 계약 생성
     * @param baseYm
     * @return
     */
    int insertIndivContractDataFromLife(String baseYm, String ogTpCd, String perfAtcCd, String cntrPerfCrtDvCd, String perfAgrgCrtDvCd);

    /**
     * P조직 조직 라이프 상조 되물림대상 계약 생성
     * @param baseYm
     * @return
     */
    int insertOrgContractDataFromLife(String baseYm, String ogTpCd, String perfAtcCd, String cntrPerfCrtDvCd, String perfAgrgCrtDvCd);

    /**
     * P조직 실활동인원수 되물림실적 생성
     * @param baseYm
     * @return
     */
    int insertActiPplNForPorganization(String baseYm, String ogTpCd, String perfAgrgCrtDvCd);
}
