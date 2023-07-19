package com.kyowon.sms.wells.web.fee.aggregate.dto;

import javax.validation.constraints.NotBlank;

public class WwfeaAccountNetIncreaseDto {

    public record SearchReq(
        @NotBlank
        String inqrDv,
        @NotBlank
        String feeTcntDvCd,
        @NotBlank
        String perfYm,
        @NotBlank
        String ogTpCd,
        String cnclTp,
        String sellTp,
        String aggregateTp,
        String dgr1LevlOgId,
        String dgr2LevlOgId,
        String dgr3LevlOgId,
        String prtnrNo
    ) {}

    public record SaveReq(
        @NotBlank
        String perfYm,
        @NotBlank
        String ogTpCd
    ) {}

    public record SearchRes(

        String inqrDv, /* 유형 */
        String cntrDtlNo, /* 계약상세번호 */
        String pdClsfNm,
        String pdClsfId,
        String ichrMngerNm, /* 담당매니저 */
        String ichrMngerNo,
        String ichrBrmgrNm, /* 담당지점장 */
        String ichrBrmgrNo,
        String cntrCnfmDtm, /* 계약일 */
        String istDt, /* 설치일 */
        String cntrChRcpDtm, /* 취소요청일 */
        String cntrCanDtm, /* 취소일 */
        String mchnChTpCd, /* 기변유형 */
        String cntrPdEnddt, /* 만료일 */
        String cntrPdStrtdt, /* 사용일 */
        String cntrRcpFshDtm, /* 멤버십접수일 */
        String cntrCnfmAprDtm, /* 멤버십가입일 */
        String cntrCanRsonCd, /* 취소사유 */
        String bzrno, /* 사업자번호 */
        String b2bYn, /* b2b여부 */
        String sellOgYn, /* 판매조직여부 */
        String sellPrtnrKnm, /* 판매자 */
        String sellPrtnrNo, /* 판매자번호 */
        String booSellYn, /* 예약여부 */
        String perfExcdOjYn, /* 제외대상 여부 */
        String apyOjYn, /* 적용대상 여부 */
        String prtnrKnm, /* 성명 */
        String prtnrNo, /* 번호 */
        int rentalCanCt, /* 렌탈취소건수 */
        int leaseCanCt, /* 리스취소건수 */
        int mshSprCt, /* 멤버십이탈 */
        int rentalExnCt, /* 렌탈만료 */
        int canTotCt, /* 취소총건수 */
        int rentalNwCt, /* 렌탈신규건수 */
        int leaseNwCt, /* 리스신규건수 */
        int nwTotCt, /* 신규총건수 */
        String accNincYn /* 계정순증 */

    ) {}
}
