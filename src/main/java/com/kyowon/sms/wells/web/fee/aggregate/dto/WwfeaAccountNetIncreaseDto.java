package com.kyowon.sms.wells.web.fee.aggregate.dto;

import javax.validation.constraints.NotBlank;

public class WwfeaAccountNetIncreaseDto {

    public record SearchReq(
        @NotBlank
        String inqrDvCd, /* 유형구분코드 */
        @NotBlank
        String feeTcntDvCd, /* 수수료차수 */
        @NotBlank
        String perfYm, /* 실적년월 */
        @NotBlank
        String ogTpCd, /* 조직유형코드 */
        String cnclTpCd, /* 취소유형코드 */
        String sellTpCd, /* 판매유형코드 */
        String aggregateTpCd, /* 집계유형코드 */
        String dgr1LevlOgId, /* 조직1레벨ID */
        String dgr2LevlOgId, /* 조직2레벨ID */
        String dgr3LevlOgId, /* 조직3레벨ID */
        String prtnrNo /* 파트너번호 */
    ) {}

    public record SaveReq(
        @NotBlank
        String perfYm,
        @NotBlank
        String feeTcntDvCd

    ) {}

    public record SearchCancelRes(

        String inqrDvCd, /* 유형 */
        String cntrDtlNo, /* 계약상세번호 */
        String pdClsfNm, /* 상품명 */
        String pdClsfId, /* 상품코드 */
        String ichrMngerNm, /* 담당매니저 */
        String ichrMngerNo, /* 번호 */
        String ichrBrmgrNm, /* 담당지점장 */
        String ichrBrmgrNo, /* 번호 */
        String cntrCnfmDtm, /* 계약일 */
        String istDt, /* 설치일 */
        String cntrChRcpDtm, /* 취소요청일 */
        String cntrCanDtm, /* 취소일 */
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
        String perfExcdOjYn, /* 제외대상 여부 */
        String apyOjYn /* 적용대상 여부 */

    ) {}

    public record SearchNewSellRes(

        String inqrDvCd, /* 유형 */
        String cntrDtlNo, /* 계약상세번호 */
        String pdClsfNm, /* 상품명 */
        String pdClsfId, /* 상품코드 */
        String cntrCnfmDtm, /* 계약일 */
        String istDt, /* 설치일 */
        String cntrCanDtm, /* 취소일 */
        String mchnChTpCd, /* 기변유형 */
        String sellOgYn, /* 판매조직여부 */
        String sellPrtnrKnm, /* 판매자 */
        String sellPrtnrNo, /* 판매자번호 */
        String booSellYn, /* 예약여부 */
        String perfExcdOjYn, /* 제외대상 여부 */
        String apyOjYn /* 적용대상 여부 */

    ) {}

    public record SearchCheckRes(

        String inqrDvCd, /* 유형 */
        String prtnrKnm, /* 성명 */
        String prtnrNo, /* 번호 */
        int rentalCanCt, /* 렌탈취소건수 */
        int spayRsgCt, /* 일시불해지건수 */
        int rentalBooCanCt, /* 렌탈예약취소건수 */
        int spayBooCanCt, /* 일시불예약취소건수 */
        int rentalExnCt, /* 렌탈만료 */
        int mshSprCt, /* 멤버십이탈 */
        int canTotCt, /* 취소총건수 */
        int rentalNwCt, /* 렌탈신규건수 */
        int spayNwCt, /* 일시불신규건수 */
        int nwTotCt, /* 신규총건수 */
        int accNincYn /* 계정순증 */

    ) {}

    public record SearchBatchRes(

        String fnlMdfcDtm /* 최종수정시간 */

    ) {}
}
