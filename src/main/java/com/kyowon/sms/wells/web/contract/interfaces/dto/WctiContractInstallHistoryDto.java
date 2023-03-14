package com.kyowon.sms.wells.web.contract.interfaces.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;

/**
 * EAI INTERFACE 통신용 DTO
 * request/response의 body에 정의된 key 형식(snake case) 사용
 */
public class WctiContractInstallHistoryDto {

    // *********************************************************
    // Request Dto
    // *********************************************************
    // 계약처, 설치처 정보 변경 이력 Search Request Dto
    @ApiModel("WctiContractInstallHistoryDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String CNTR_NO,
        @NotBlank
        String CNTR_SN,
        @NotBlank
        String INQR_DV_CD
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 계약처, 설치처 정보 변경 이력 Search Response Dto
    @ApiModel("WctiContractInstallHistoryDto-SearchRes")
    public record SearchRes(
        String CNTR_NO, //계약번호
        String CNTR_SN, //계약일련번호
        String CRAL_LOCARA_TNO, //휴대지역전화번호
        String MEXNO, //휴대전화국번호
        String CRAL_IDV_TNO, //휴대개별전화번호
        String LOCARA_TNO, //지역전화번호
        String EXNO, //전화국번호
        String IDV_TNO, //개별전화번호
        String CH_DTM, //변경일시
        String FNL_MDFC_USR_ID, //최종수정사용자ID
        String FNL_MDFC_USR_NM //최종수정사용자명
    ) {}
}
