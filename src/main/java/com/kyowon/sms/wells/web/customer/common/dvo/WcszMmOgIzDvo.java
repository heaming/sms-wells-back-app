package com.kyowon.sms.wells.web.customer.common.dvo;

import lombok.Getter;
import lombok.Setter;

/**
 * 월조직내역 Dvo
 */
@Getter
@Setter
public class WcszMmOgIzDvo {
    private String ptlCstDbDvCd; /* 잠재고객DB 구분코드 */
    private String pspcCstInflwPhCd; /* 가망고객유입경로코드 */
    private String prPrtnrNwInflwCstYn; /* 홍보파트너신규유입고객여부 */
    private String copnDvCd; /* 법인격구분코드 */

    private String baseYm; /* 기준년월 */
    private String ogId; /* 조직ID */
    private String hgrOgId; /* 상위조직ID */
    private String ogTpCd; /* 조직유형코드 */
    private String ogLevlDvCd; /* 조직레벨구분코드 */
    private String ogCd; /* 조직코드 */
    private String ogNm; /* 조직명 */
    private String ogAbbrNm; /* 조직약어명 */
    private String vdtcrPdDvCd; /* 화상교사상품구분코드 */
    private String cpsnDtrcOgCd; /* 강제지국조직코드 */
    private String dtrcSbrncOgCd; /* 지국지소조직코드 */
    private String sbrncYn; /* 지소여부 */
    private String sbrncDvCd; /* 지소구분코드 */
    private String opDt; /* 개설일자 */
    private String cloYn; /* 폐쇄여부 */
    private String cloDt; /* 폐쇄일자 */
    private String hooOgTpCd; /* 조직장조직유형코드 */
    private String hooPrtnrNo; /* 조직장파트너번호 */
    private String hooPrtnrNm; /* 조직장파트너명 */
    private String bizSpptPrtnrNo; /* 업무지원파트너번호 */
    private String ogUpbrngPrtnrNo; /* 조직육성파트너번호 */
    private Integer dtrcN; /* 지국수 */
    private String bldCd; /* 빌딩코드 */
    private String bldNm; /* 빌딩명 */
    private String dgr1LevlOgId; /* 1차레벨조직ID */
    private String dgr1LevlOgCd; /* 1차레벨조직코드 */
    private String dgr1LevlOgNm; /* 1차레벨조직명 */
    private String dgr1LevlDgPrtnrNo; /* 1차레벨대표파트너번호 */
    private String dgr1LevlDgPrtnrNm; /* 1차레벨대표파트너명 */
    private String dgr2LevlOgId; /* 2차레벨조직ID */
    private String dgr2LevlOgCd; /* 2차레벨조직코드 */
    private String dgr2LevlOgNm; /* 2차레벨조직명 */
    private String dgr2LevlDgPrtnrNo; /* 2차레벨대표파트너번호 */
    private String dgr2LevlDgPrtnrNm; /* 2차레벨대표파트너명 */
    private String dgr3LevlOgId; /* 3차레벨조직ID */
    private String dgr3LevlOgCd; /* 3차레벨조직코드 */
    private String dgr3LevlOgNm; /* 3차레벨조직명 */
    private String dgr3LevlDgPrtnrNo; /* 3차레벨대표파트너번호 */
    private String dgr3LevlDgPrtnrNm; /* 3차레벨대표파트너명 */
    private String dgr4LevlOgId; /* 4차레벨조직ID */
    private String dgr4LevlOgCd; /* 4차레벨조직코드 */
    private String dgr4LevlOgNm; /* 4차레벨조직명 */
    private String dgr4LevlDgPrtnrNo; /* 4차레벨대표파트너번호 */
    private String dgr4LevlDgPrtnrNm; /* 4차레벨대표파트너명 */
    private String dgr5LevlOgId; /* 5차레벨조직ID */
    private String dgr5LevlOgCd; /* 5차레벨조직코드 */
    private String dgr5LevlOgNm; /* 5차레벨조직명 */
    private String dgr5LevlDgPrtnrNo; /* 5차레벨대표파트너번호 */
    private String dgr5LevlDgPrtnrNm; /* 5차레벨대표파트너명 */
    private String srnExpsrExcdOjYn; /* 화면노출제외대상여부 */
    private String dtaDlYn; /* 데이터삭제여부 */
}
