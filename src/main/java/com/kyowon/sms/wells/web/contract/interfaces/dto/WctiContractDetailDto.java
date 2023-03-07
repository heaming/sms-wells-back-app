package com.kyowon.sms.wells.web.contract.interfaces.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;

/**
 * EAI INTERFACE 통신용 DTO
 * request/response의 body에 정의된 key 형식(snake case) 사용
 */
public class WctiContractDetailDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // 계약상세 목록 Search Request Dto
    @ApiModel("WctiContractDetailDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String CST_NO,
        String PD_NM,
        String SELL_TP_CD
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 계약상세 목록 Search Response Dto
    @ApiModel("WctiContractDetailDto-SearchRes")
    public record SearchRes(
        String CNTR_NO, //계약번호
        String CNTR_SN, //계약일련번호
        String CNTR_DTL_STAT_CD, //계약상세상태코드
        String CNTR_DTL_STAT_NM, //계약상세상태코드명
        String PD_HCLSF_ID, //상품대분류ID
        String PD_HCLSF_NM, //상품대분류명
        String PD_MCLSF_ID, //상품중분류ID
        String PD_MCLSF_NM, //상품중분류명
        String PD_LCLSF_ID, //상품소분류ID
        String PD_LCLSF_NM, //상품소분류명
        String BASE_PD_CD, //기준상품코드
        String BASE_PD_NM, //기준상품명
        String CNTRT_NM, //계약자명
        String CNTR_DT, //계약일자
        String CRAL_LOCARA_TNO, //휴대지역전화번호
        String MEXNO, //휴대전화국번호
        String CRAL_IDV_TNO, //휴대개별전화번호
        String LOCARA_TNO, //지역전화번호
        String EXNO, //전화국번호
        String IDV_TNO, //개별전화번호
        String CNTR_ADRPC_ID, //계약주소지ID
        String CNTR_ADR, //계약주소
        String CNTR_DTL_ADR, //계약상세주소
        String BRYY_MMDD, //생년월일
        String SEX_DV_CD, //성별구분코드
        String ISTLL_NM //설지차명
    ) {}
}
