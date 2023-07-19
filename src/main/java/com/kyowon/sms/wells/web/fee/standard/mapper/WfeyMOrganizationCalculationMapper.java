package com.kyowon.sms.wells.web.fee.standard.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WfeyMOrganizationCalculationMapper {

    /**
     * BS관리(판매자)수수료(W020084) 후처리 Case1
     *
     * 웰스서비스실적내역 테이블에 수수료계산금액 업데이트
     *
     * @param baseYm 기준년월
     * @param feeCd 수수료코드
     * @param feeTcntDvCd 수수료차수구분코드
     * @param ogTpCd 조직유형코드
     * @return 업데이트건수
     */
    Integer updateBsManagementFeeForSellerCase1(String baseYm, String feeCd, String feeTcntDvCd, String ogTpCd);
    /**
     * BS관리(판매자)수수료(W020084) 후처리 Case2
     *
     * 웰스서비스실적내역 테이블에 수수료계산금액 업데이트
     *
     * @param baseYm 기준년월
     * @param feeCd 수수료코드
     * @param feeTcntDvCd 수수료차수구분코드
     * @param ogTpCd 조직유형코드
     * @return 업데이트건수
     */
    Integer updateBsManagementFeeForSellerCase2(String baseYm, String feeCd, String feeTcntDvCd, String ogTpCd);
    /**
     * BS관리(판매자)수수료(W020084) 후처리 Case3
     *
     * 웰스서비스실적내역 테이블에 수수료계산금액 업데이트
     *
     * @param baseYm 기준년월
     * @param feeCd 수수료코드
     * @param feeTcntDvCd 수수료차수구분코드
     * @param ogTpCd 조직유형코드
     * @return 업데이트건수
     */
    Integer updateBsManagementFeeForSellerCase3(String baseYm, String feeCd, String feeTcntDvCd, String ogTpCd);
    /**
     * BS관리(판매자)수수료(W020084) 후처리 Case4
     *
     * 웰스서비스실적내역 테이블에 수수료계산금액 업데이트
     *
     * @param baseYm 기준년월
     * @param feeCd 수수료코드
     * @param feeTcntDvCd 수수료차수구분코드
     * @param ogTpCd 조직유형코드
     * @return 업데이트건수
     */
    Integer updateBsManagementFeeForSellerCase4(String baseYm, String feeCd, String feeTcntDvCd, String ogTpCd);

    /**
     * WM급지(판매자)수수료(W020085) 후처리
     *
     * 웰스서비스실적내역 테이블에 급지수수료금액 업데이트
     *
     * @param baseYm 기준년월
     * @param feeCd 수수료코드
     * @param feeTcntDvCd 수수료차수구분코드
     * @param ogTpCd 조직유형코드
     * @return 업데이트건수
     */
    Integer updateRegionLevelFeeForSeller(String baseYm, String feeCd, String feeTcntDvCd, String ogTpCd);

    /**
     * BS관리(지점장)수수료(W020087) 후처리 Case1
     *
     * 웰스서비스실적내역 테이블에 급지수수료금액 업데이트
     *
     * @param baseYm 기준년월
     * @param feeCd 수수료코드
     * @param feeTcntDvCd 수수료차수구분코드
     * @param ogTpCd 조직유형코드
     * @return 업데이트건수
     */
    Integer updateBsManagementFeeForBrmgrCase1(String baseYm, String feeCd, String feeTcntDvCd, String ogTpCd);
    /**
     * BS관리(지점장)수수료(W020087) 후처리 Case2
     *
     * 웰스서비스실적내역 테이블에 급지수수료금액 업데이트
     *
     * @param baseYm 기준년월
     * @param feeCd 수수료코드
     * @param feeTcntDvCd 수수료차수구분코드
     * @param ogTpCd 조직유형코드
     * @return 업데이트건수
     */
    Integer updateBsManagementFeeForBrmgrCase2(String baseYm, String feeCd, String feeTcntDvCd, String ogTpCd);
    /**
     * BS관리(지점장)수수료(W020087) 후처리 Case3
     *
     * 웰스서비스실적내역 테이블에 급지수수료금액 업데이트
     *
     * @param baseYm 기준년월
     * @param feeCd 수수료코드
     * @param feeTcntDvCd 수수료차수구분코드
     * @param ogTpCd 조직유형코드
     * @return 업데이트건수
     */
    Integer updateBsManagementFeeForBrmgrCase3(String baseYm, String feeCd, String feeTcntDvCd, String ogTpCd);
    /**
     * BS관리(지점장)수수료(W020087) 후처리 Case4
     *
     * 웰스서비스실적내역 테이블에 급지수수료금액 업데이트
     *
     * @param baseYm 기준년월
     * @param feeCd 수수료코드
     * @param feeTcntDvCd 수수료차수구분코드
     * @param ogTpCd 조직유형코드
     * @return 업데이트건수
     */
    Integer updateBsManagementFeeForBrmgrCase4(String baseYm, String feeCd, String feeTcntDvCd, String ogTpCd);
    /**
     * BS관리(지점장)수수료(W020087) 후처리 Case5
     *
     * 웰스서비스실적내역 테이블에 급지수수료금액 업데이트
     *
     * @param baseYm 기준년월
     * @param feeCd 수수료코드
     * @param feeTcntDvCd 수수료차수구분코드
     * @param ogTpCd 조직유형코드
     * @return 업데이트건수
     */
    Integer updateBsManagementFeeForBrmgrCase5(String baseYm, String feeCd, String feeTcntDvCd, String ogTpCd);
    /**
     * BS관리(지점장)수수료(W020087) 후처리 Case6
     *
     * 웰스서비스실적내역 테이블에 급지수수료금액 업데이트
     *
     * @param baseYm 기준년월
     * @param feeCd 수수료코드
     * @param feeTcntDvCd 수수료차수구분코드
     * @param ogTpCd 조직유형코드
     * @return 업데이트건수
     */
    Integer updateBsManagementFeeForBrmgrCase6(String baseYm, String feeCd, String feeTcntDvCd, String ogTpCd);
    /**
     * BS관리(지점장)수수료(W020087) 후처리 Case7
     *
     * 웰스서비스실적내역 테이블에 급지수수료금액 업데이트
     *
     * @param baseYm 기준년월
     * @param feeCd 수수료코드
     * @param feeTcntDvCd 수수료차수구분코드
     * @param ogTpCd 조직유형코드
     * @return 업데이트건수
     */
    Integer updateBsManagementFeeForBrmgrCase7(String baseYm, String feeCd, String feeTcntDvCd, String ogTpCd);
    /**
     * BS관리(지점장)수수료(W020087) 후처리 Case8
     *
     * 웰스서비스실적내역 테이블에 급지수수료금액 업데이트
     *
     * @param baseYm 기준년월
     * @param feeCd 수수료코드
     * @param feeTcntDvCd 수수료차수구분코드
     * @param ogTpCd 조직유형코드
     * @return 업데이트건수
     */
    Integer updateBsManagementFeeForBrmgrCase8(String baseYm, String feeCd, String feeTcntDvCd, String ogTpCd);

    /**
     * BS관리(지점장)수수료(W020087) 후처리
     *
     * 웰스서비스실적내역 테이블에 수수료계산금액 업데이트
     *
     * @param baseYm 기준년월
     * @param feeCd 수수료코드
     * @param feeTcntDvCd 수수료차수구분코드
     * @param ogTpCd 조직유형코드
     * @return 업데이트건수
     */
    Integer updateUnderPerf120RegionLevelFeeForBrmgr(String baseYm, String feeCd, String feeTcntDvCd, String ogTpCd);

    /**
     * WM급지(지점장)수수료(W020088) 후처리
     *
     * 웰스서비스실적내역 테이블에 매니저BS실적건수 120이상 대상자 급지수수료금액 업데이트
     *
     * @param baseYm 기준년월
     * @param feeCd 수수료코드
     * @param feeTcntDvCd 수수료차수구분코드
     * @param ogTpCd 조직유형코드
     * @return 업데이트건수
     */
    Integer updateOverPerf120RegionLevelFeeForBrmgr(String baseYm, String feeCd, String feeTcntDvCd, String ogTpCd);


}
