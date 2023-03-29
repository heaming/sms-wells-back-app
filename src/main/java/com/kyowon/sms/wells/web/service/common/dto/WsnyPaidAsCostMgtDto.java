package com.kyowon.sms.wells.web.service.common.dto;

import io.swagger.annotations.ApiModel;

import javax.validation.constraints.NotBlank;

public class WsnyPaidAsCostMgtDto {

    @ApiModel(value = "WsnyPaidAsCostMgtDto-SearchReq")
    public record SearchReq(
        String hgrPdCd, // 상위상품코드
        String pdCd, //품목코드
        String cmnPartChk, //공통부품 체크 여부
        String apyMtrChk //현재적용자료 체크 여부
    ){}
    @ApiModel(value = "WsnyPaidAsCostMgtDto-SearchRes")
    public record SearchRes(
        String sapMatCd, // SAP코드
        String pdCd, // 품목코드
        String pdNm, // 품목명
        String apyStrtdt, // 적용시작일자
        String apyEnddt, // 적용종료일자
        int csmrUprcAmt, // 소비자단가금액
        int whlsUprcAmt, // 도매단가금액
        int insiUprcAmt, // 내부단가금액
        int tcfeeAmt, // 기술료금액
        int sumAmt, // 합계(소비자단가금액+기술료금액)
        int izSn,
        String basePdCd
    ){}

    @ApiModel(value = "WsnyPaidAsCostMgtDto-PdReq")
    public record PdReq(
        String hgrPdCd
    ){}

    @ApiModel(value = "WsnyPaidAsCostMgtDto-PdRes")
    public record PdRes(
        String codeId,
        String codeName,
        String abbrName
    ){}

    @ApiModel(value = "WsnyPaidAsCostMgtDto-SaveReq")
    public record SaveReq(
        @NotBlank
        String pdCd, // 상품코드
        @NotBlank
        String basePdCd, // 기준상품코드
        @NotBlank
        int izSn, //내역일련번호
        String apyStrtdt, // 적용시작일자
        String apyEnddt, // 적용종료일자
        int csmrUprcAmt, // 소비자단가금액
        int whlsUprcAmt, // 도매단가금액
        int insiUprcAmt, // 내부단가금액
        int tcfeeAmt // 기술료금액
    ){}
}
