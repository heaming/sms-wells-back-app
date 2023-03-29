package com.kyowon.sms.wells.web.withdrawal.bilfnt.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WwdaDesignationWithdrawalCustomerMgtDto {

    @ApiModel("WwdaDesignationWithdrawalCustomerMgtDto-SearchAutoFntDsnWdrwCstReq")
    public record SearchAutoFntDsnWdrwCstReq(
        String cntr,
        String sellTpCd // 판매유형코드
    ) {}

    @ApiModel("WwdaDesignationWithdrawalCustomerMgtDto-SearchAutoFntDsnWdrwCstRes")
    public record SearchAutoFntDsnWdrwCstRes(
        String cntr, // 계약번호
        String cntrNo, // 계약상세번호
        String cntrSn, // 계약상세 일련번호
        String cstKnm, // 고객성명
        String sellTpCd, // 업무유형
        String dsnWdrwAmt, // 지정금액
        String dsnWdrwFntD, // 이체일
        String fntYn, // 이체구분
        String dpAmt, // 입금금액
        String ucAmt, // 잔액
        String dsnWdrwFntPrdCd, // 이체주기
        String prtnrKnm, // 등록담당자
        String fnlMdfcUsrId, // 등록자 사번
        String fnlMdfcDtm // 등록일시
    ) {}

    @Builder
    @ApiModel("WwdaDesignationWithdrawalCustomerMgtDto-SaveReq")
    public record SaveReq(
        @NotBlank
        String rowState,
        Integer dataRow,
        String cntr, // 계약번호
        @NotBlank
        String cntrNo, // 계약상세번호
        @NotBlank
        String cntrSn, // 계약상세 일련번호
        @NotBlank
        Integer dsnWdrwAmt, // 지정금액
        Integer dpAmt, // 입금금액
        Integer ucAmt, // 잔액
        @NotBlank
        String fntYn, // 이체구분
        @NotBlank
        String dsnWdrwFntD, // 이체일
        @NotBlank
        String dsnWdrwFntPrdCd, // 이체주기코드
        String dtaDlYn
    ) {
        public SaveReq {
            dtaDlYn = "N";
        }

    }

    @Builder
    @ApiModel("WwdaDesignationWithdrawalCustomerMgtDto-RemoveReq")
    public record RemoveReq(
        String cntr,
        @NotBlank
        String cntrNo, // 계약번호
        @NotBlank
        String cntrSn, // 계약일련번호
        @NotBlank
        String dsnWdrwFntD // 이체일

    ) {}

}
