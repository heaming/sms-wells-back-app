package com.kyowon.sms.wells.web.withdrawal.bilfnt.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WwdaAutoFntDsnWdrwMgtDto {

    @ApiModel("WwdaAutomaticFntDsnWdrwDto-SearchAutoFntDsnWdrwCstReq")
    public record SearchAutoFntDsnWdrwCstReq(
        String cntrNo, // 계약번호
        Integer cntrSn, // 계약일련번호
        String sellTpCd // 판매유형코드
    ) {}

    @ApiModel("WwdaAutomaticFntDsnWdrwDto-SearchAutoFntDsnWdrwCstRes")
    public record SearchAutoFntDsnWdrwCstRes(
        String cntr, // 계약상세번호
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
    @ApiModel("WwdaAutomaticFntDsnWdrwDto-SaveReq")
    public record SaveReq(
        @NotBlank
        String rowState,
        String dataRow,
        @NotBlank
        String cntrNo, // 계약번호
        @NotBlank
        Integer cntrSn, // 계약일련번호
        @NotBlank
        Integer dsnWdrwAmt, // 지정금액
        Integer dpAmt, // 입금금액
        @NotBlank
        String fntYn, // 이체구분
        @NotBlank
        String dsnWdrwFntD, // 이체일
        @NotBlank
        String dsnWdrwFntPrdCd, // 이체주기코드
        String reInsertYn // 삭제된 코드 재등록 여부 
    ) {}

    @ApiModel("WwdaAutomaticFntDsnWdrwDto-SearchAutomaticFntOjYnConfRes")
    public record SearchAutomaticFntOjYnConfRes(
        String mpyMthdTpCd, // 납부방식유형코드 
        String fnitAprRsCd // 금융기관승인결과코드
    ) {}

    @ApiModel("WwdaAutomaticFntDsnWdrwDto-SearchWwdaBilFntAkDtlRes")
    public record SearchWwdaBilFntAkDtlRes(
        String bilNo, // 청구번호
        Integer bilDtlSn // 청구상세일련번호
    ) {}

}
