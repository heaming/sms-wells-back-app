package com.kyowon.sms.wells.web.withdrawal.idvrve.dto;

import io.swagger.annotations.ApiModel;

/**
 * <pre>
 * 입금집계자료 조회 DTO
 * </pre>
 *
 * @author kimoon.lim
 * @since 2023-06-14
 */
public class WwdbDepositAggregateMaterialDto {

    // *********************************************************
    // Request Dto
    // *********************************************************
    // 입금집계 목록 조회 Request Dto
    @ApiModel("WwdbDepositAggregateMaterialDto-SearchDepositAggregateMaterialReq")
    public record SearchDepositAggregateMaterialReq(
        String dvCd, // 업무구분
        String searchDt, // 기준년월
        String dpTpCd // 입금유형 (TODO: 추가된 조회 컬럼)
    ) { }

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 입금집계 목록 조회 Result Dto
    @ApiModel("WwdbDepositAggregateMaterialDto-SearchDepositAggregateMaterialRes")
    public record SearchDepositAggregateMaterialRes(
        String dvNm, // 구분(공통,렌탈)
        String dpTpCd, // 입금유형
//        String sellTpCd,
//        String totRowNum,
        String spayAmt, // 일시불
        String rtlsAmt, // 렌탈/리스
        String mbmsAmt, // 멤버십
        String coIstAmt, // 회사설치
        String mngtAmt, // 유지관리
        String rglrAmt, // 정기배송
        String filtAmt, // 필터
        String totAmt // 금액(합계)
        //        String totRat
    ) { }

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 입금집계 합계 및 비율 Result Dto
    @ApiModel("WwdbDepositAggregateMaterialDto-SearchDepositAggregateMaterialTotalRes")
    public record SearchDepositAggregateMaterialTotalRes(
        String totSpay, // 일시불 합계
        String totRtls, // 렌탈/리스 합계
        String totMbms, // 멤버십 합계
        String totCoIst, // 회사설치 합계
        String totMngt, // 유지관리 합계
        String totRglr, // 정기배송 합계
        String totFilt, // 필터 합계
        String tot, // 금액(합계)
        String spayPer, // 일시불 비율
        String rtlsPer, // 렌탈/리스 비율
        String mbmsPer, // 멤버십 비율
        String coIstPer, // 회사설치 비율
        String mngtPer, // 유지관리 비율
        String rglrPer, // 정기배송 비율
        String filtPer, // 필터 비율
        String totalPer // 계(%) - 비율 합계
    ) { }

}
