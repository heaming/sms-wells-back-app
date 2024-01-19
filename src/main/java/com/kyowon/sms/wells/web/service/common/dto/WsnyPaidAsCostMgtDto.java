package com.kyowon.sms.wells.web.service.common.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;

/**
 * <pre>
 * W-SV-U-0159M01 유상A/S 서비스비용 관리
 * </pre>
 *
 * @author kyunglyn.lee
 * @since 2023-03-08
 */
public class WsnyPaidAsCostMgtDto {

    @ApiModel(value = "WsnyPaidAsCostMgtDto-SearchReq")
    public record SearchReq(
        String pdGrpCd, // 상위상품코드
        String pdCd, //품목코드
        String cmnPartChk, //공통부품 체크 여부
        String apyMtrChk //현재적용자료 체크 여부
    ) {}
    @ApiModel(value = "WsnyPaidAsCostMgtDto-SearchRes")
    public record SearchRes(
        String sapMatCd, // SAP코드
        String useMatPdCd, // 품목코드
        String pdNm, // 품목명
        String apyStrtdt, // 적용시작일자
        String orgApyStrtdt, // 변경전 적용시작일자
        String apyEnddt, // 적용종료일자
        String orgApyEnddt, // 변경 전 적용종료일자
        BigDecimal csmrUprcAmt, // 소비자단가금액
        BigDecimal whlsUprcAmt, // 도매단가금액
        BigDecimal insiUprcAmt, // 내부단가금액
        BigDecimal tcfeeAmt, // 기술료금액
        BigDecimal sumAmt, // 합계(소비자단가금액+기술료금액)
        BigDecimal izSn,
        String pdctPdCd,
        int rn
    ) {}

    @ApiModel(value = "WsnyPaidAsCostMgtDto-SaveReq")
    public record SaveReq(
        @NotBlank
        String useMatPdCd, // 상품코드
        @NotBlank
        String pdctPdCd, // 기준상품코드
        BigDecimal izSn, //내역일련번호
        String apyStrtdt, // 적용시작일자
        String orgApyStrtdt, // 변경 전 적용시작일자
        String apyEnddt, // 적용종료일자
        BigDecimal csmrUprcAmt, // 소비자단가금액
        BigDecimal whlsUprcAmt, // 도매단가금액
        BigDecimal insiUprcAmt, // 내부단가금액
        BigDecimal tcfeeAmt // 기술료금액
    ) {}
}
