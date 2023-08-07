package com.kyowon.sms.wells.web.closing.performance.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WdccMembershipCnfmGenDto {

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 멤버십 확정 생성 - SearchReq
    @Builder
    @ApiModel(value = "WdccMembershipCnfmGenDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String sellInflwChnlDtlCd, /* 조직선택 (판매유입채널상세코드)*/
        @NotBlank
        String fromCntrRcpFshDtm, /* 시작접수년월 (계약접수완료일시)*/
        @NotBlank
        String toCntrRcpFshDtm, /* 종료접수년월 (계약접수완료일시)*/
        String cntrNo, /* 계약번호 */
        String cntrSn, /* 계약일련번호 */
        String sellTpCd, /* 판매유형 (판매유형코드)*/
        String sellTpDtlCd /* 멤버십구분 (판매유형상세코드)*/
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 멤버십 확정 생성 - SearchReq
    @ApiModel(value = "WdccMembershipCnfmGenDto-SearchRes")
    public record SearchRes(
        String cntrNo, /* 계약번호*/
        String cntrSn, /* 계약일련번호*/
        String cntrNoSn, /* 계약상세번호 */
        String cstKnm, /* 고객명 */
        String sellTpNm, /* 판매유형명*/
        String sellTpDtlNm, /* 판매유형상세코드명*/
        String cntrwTpCd, /* 계약서유형코드*/
        String sellOgTpCd, /* 판매자조직유형코드*/
        String basePdCd, /* 기준상품코드*/
        String pdNm, /* 상품명*/
        String uswy, /* 용도*/
        String svPrd, /* 서비스주기*/
        String frisuYn, /* 무상여부*/
        String stplPtrm, /* 약정기간*/
        String hcrDvCd, /* 구분1(홈케어구분코드)*/
        String dv, /* 상품구분2*/
        String fntDv, /* 이체구분*/
        String cntrCstNo, /* 계약고객번호*/
        String cntrRcpFshDtm, /* 계약접수완료일시*/
        String cntrPdStrtdt, /* 계약상품시작일자*/
        String istDt, /* 설치일자*/
        Long sellAmt, /* 판매금액*/
        String stlmTpCd, /* 결제유형코드*/
        Long dscAmt, /* 할인금액*/
        String cttRsCd, /* 컨택결과코드*/
        String cttRsNm, /* 컨택결과코드명*/
        String cttPsicId, /* 컨택담당자ID*/
        String cttPsicNm, /* 컨택담당자*/
        String fstRgstDtm, /* 최초등록일시*/
        String fstRgstUsrId, /* 최초등록사용자ID*/
        String fstRgstUsrNm, /* 등록자*/
        String fstRgstPrgId, /* 최초등록프로그램ID*/
        String fnlMdfcDtm, /* 최종수정일자*/
        String fnlMdfcUsrId, /* 최종수정사용자ID*/
        String fnlMdfcUsrNm, /* 수정자*/
        String fnlMdfcPrgId, /* 최종수정프로그램ID*/
        String cntrPdEnddt, /* 계약상품종료일자*/
        String cntrCnfmDtm /* 계약확정일시*/
    ) {}
    @ApiModel(value = "WdccMembershipCnfmGenDto-SaveReq")
    public record SaveReq(
        SearchReq searchReq, /* 조회조건 */
        @NotBlank
        String sellInflwChnlDtlCd, /* 조직선택 (판매유입채널상세코드)*/
        @NotBlank
        String fromCntrRcpFshDtm, /* 시작접수년월 (계약접수완료일시)*/
        @NotBlank
        String toCntrRcpFshDtm, /* 종료접수년월 (계약접수완료일시)*/
        String cntrNo, /* 계약번호 */
        String cntrSn, /* 계약일련번호 */
        String sellTpCd, /* 판매유형 (판매유형코드)*/
        String sellTpDtlCd, /* 멤버십구분 (판매유형상세코드)*/
        @NotBlank
        String confirmDate, /* 확정일자 */
        @NotBlank
        String joinDate /* 가입일자 */
    ) {
        public SaveReq {
            searchReq = SearchReq.builder()
                .sellInflwChnlDtlCd(sellInflwChnlDtlCd)
                .fromCntrRcpFshDtm(fromCntrRcpFshDtm)
                .toCntrRcpFshDtm(toCntrRcpFshDtm)
                .cntrNo(cntrNo)
                .cntrSn(cntrSn)
                .sellTpCd(sellTpCd)
                .sellTpDtlCd(sellTpDtlCd)
                .build();
        }
    }

}
