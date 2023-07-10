package com.kyowon.sms.wells.web.fee.standard.mapper;

import com.kyowon.sms.wells.web.fee.standard.dto.WfeyEngineerAllowanceDto.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WfeyHomeMasterCalculationMapper {

    /**
     * 홈마스터 급지수수료(W030012) 후처리
     *
     * @param baseYm 기준년월
     * @param ogTpCd 조직유형코드
     * @param feeCd 수수료코드
     * @param feeTcntDvCd 수수료차수구분코드
     * @return 처리개수
     */
    Integer updateRglvlFeePostProcess(String baseYm, String ogTpCd, String feeCd, String feeTcntDvCd);

    /**
     * 홈마스터 서비스현장수수료(W030007) 후처리
     *
     * @param baseYm 기준년월
     * @param ogTpCd 조직유형코드
     * @param feeCd 수수료코드
     * @param feeTcntDvCd 수수료차수구분코드
     * @return 처리개수
     */
    Integer updateSvSiteFeePostProcess(String baseYm, String ogTpCd, String feeCd, String feeTcntDvCd);

    /**
     * 교육수수료(W030011)계산 1단계 - 2회차 지급월에 퇴직여부에 따라 미지급(삭제)으로 상태 변경
     *
     * @param baseYm
     * @param ogTpCd
     * @param feeTcntDvCd
     * @return
     */
    Integer updateResignPartnerEducationFees(String baseYm, String ogTpCd, String feeTcntDvCd);

    /**
     * 교육수수료(W030011)계산 1단계 - 2회차 지급월에 퇴직여부에 따라 미지급(삭제)으로 상태 변경 이력생성
     * @param baseYm
     * @param ogTpCd
     * @param feeTcntDvCd
     * @return
     */
    Integer insertResignPartnerEducationFeeHistories(String baseYm, String ogTpCd, String feeTcntDvCd);

    /**
     * 교육수수료(W030011)계산 1단계 - 2회차 지급월에 퇴직여부에 따라 미지급(삭제)으로 계획 상태 변경
     * @param baseYm
     * @param ogTpCd
     * @param feeCd
     * @param feeTcntDvCd
     * @return
     */
    Integer updateResignPartnerEducationFeePlan(String baseYm, String ogTpCd, String feeCd, String feeTcntDvCd);

    /**
     * 교육수수료(W030011)계산 1단계 - 2회차 지급월에 퇴직여부에 따라 미지급(삭제)으로 계획 상태변경 이력 생성
     *
     * @param baseYm
     * @param ogTpCd
     * @param feeCd
     * @param feeTcntDvCd
     * @return
     */
    Integer insertResignPartnerEducationFeePlanHistories(String baseYm, String ogTpCd, String feeCd, String feeTcntDvCd);

    Integer insertEducationFees(String baseYm, String ogTpCd, String feeTcntDvCd, String feeCd, String dtaCrtFeeCd, String coCd, String fnlFeeYn);

    Integer insertEducationFeePlan(String baseYm, String ogTpCd, String feeTcntDvCd, String feeCd, String dtaCrtFeeCd, String coCd, String fnlFeeYn);
}
