package com.kyowon.sms.wells.web.bond.consultation.dvo;

import lombok.*;

/**
 * <pre>
 * TB_CBBO_WELLS_UCAM_ADVNT_OJ_IZ
 * WELLS미수금안내장대상내역 T
 * </pre>
 *
 * @author gilyong.han
 * @since 2023-07-13
 */

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WbncUncollectedAdviceNoteOjIzDvo {
    private String wkDt; /* 작업일자 */
    private String cntrNo; /* 계약번호 */
    private Integer cntrSn; /* 계약일련번호 */
    private String ucAmtFwTpCd; /* 미수금액발송유형코드 */
    private String cstNo; /* 고객번호 */
    private String cstNm; /* 고객명 */
    private String sfkVal; /* 세이프키값 */
    private String copnDvCd; /* 법인격구분코드 */
    private String cntrBasAdr; /* 계약기본주소 */
    private String cntrDtlAdr; /* 계약상세주소 */
    private String cntrRefAdr; /* 계약참조주소 */
    private String zip; /* 우편번호 */
    private String istPlcNm; /* 설치장소명 */
    private String sellTpCd; /* 판매유형코드 */
    private String rcpY; /* 접수년도 */
    private String rcpMm; /* 접수월 */
    private String rcpD; /* 접수일 */
    private String istY; /* 설치년도 */
    private String istMm; /* 설치월 */
    private String istD; /* 설치일 */
    private Long rentalAmt1; /* 렌탈금액1 */
    private Long rentalAmt2; /* 렌탈금액2 */
    private Long dlqAmt; /* 연체금액 */
    private Long npdAmt; /* 미납금액 */
    private Long thmChramAmt; /* 당월요금금액 */
    private Long dlqAddAmt; /* 연체가산금액 */
    private Long spmtSlAmt; /* 추가매출금액 */
    private Integer dlqMcn; /* 연체개월수 */
    private Long totNpdAmt; /* 총미납금액 */
    private String pdgrpNm; /* 상품군명 */
    private String pdNm; /* 상품명 */
    private String fnlPyY; /* 최종납입년도 */
    private String fnlPyMm; /* 최종납입월 */
    private String fnlPyD; /* 최종납입일 */
    private String bnkCd; /* 은행코드 */
    private String vacNo; /* 가상계좌번호 */
    private String achldrNm; /* 예금주명 */
    private String pyTmlmDt; /* 납입기한일자 */
    private String clctamOgTpCd; /* 집금조직유형코드 */
    private String clctamPrtnrNo; /* 집금파트너번호 */
    private String clctamPrtnrNm; /* 집금파트너명 */
    private String ofrmTnoVal; /* 사무실전화번호값 */
    private String excdYn; /* 제외여부 */
    private String cnfmYn; /* 확정여부 */
    private String dtaDlYn; /* 데이터삭제여부 */
}
