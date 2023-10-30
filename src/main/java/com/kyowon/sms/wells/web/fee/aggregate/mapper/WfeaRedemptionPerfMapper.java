package com.kyowon.sms.wells.web.fee.aggregate.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WfeaRedemptionPerfMapper {

    /**
     * 되물림확정 상태 조회
     * @param baseYm
     * @return 확정상태 개수
     */
    int selectRedemptionConfirmStatus(String baseYm, String perfAgrgCrtDvCd);

    /**
     * 되물림 상태데이터 생성
     * @param baseYm
     * @return
     */
    int insertRedemptionStatus(String baseYm, String perfAgrgCrtDvCd);

    /**
     * 실적 데이터 삭제
     * @param tableName
     * @param baseYm
     * @return
     */
    int deleteCommonRedempPerfData(String tableName, String baseYm, String ogTpCd, String perfAgrgCrtDvCd);

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
    int insertIndivContractDataFromLife(String baseYm, String ogTpCd, String perfAtcCd, String cntrPerfCrtDvCd);

    /**
     * P조직 개인 라이프 상조 되물림대상 계약 생성
     * @param baseYm
     * @return
     */
    int insertOrgContractDataFromLife(String baseYm, String ogTpCd, String perfAtcCd, String cntrPerfCrtDvCd);

    /**
     * 개인 되물림 계약 실적을 파트너별 합계 생성
     * @param baseYm
     * @return
     */
    int insertSumOfCntrDataToPrtnrForIndiv(String baseYm, String ogTpCd, String perfAgrgCrtDvCd, List<String> perfAtcCds);

    /**
     * 파트너단위 개인 되물림 실적을 조직 파트너 실적 합계 생성
     * @param baseYm
     * @return
     */
    int insertSumOfCntrDataToPrtnrForOg(String baseYm, String ogTpCd, String perfAgrgCrtDvCd, List<String> perfAtcCds);

    /**
     * P조직 실활동인원수 되물림실적 생성
     * @param baseYm
     * @return
     */
    int insertActiPplNForPorganization(String baseYm);
}
