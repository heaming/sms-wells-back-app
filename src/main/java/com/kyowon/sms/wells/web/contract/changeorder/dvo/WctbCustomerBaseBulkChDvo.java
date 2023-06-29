package com.kyowon.sms.wells.web.contract.changeorder.dvo;

import com.sds.sflex.system.config.annotation.DBDecField;
import com.sds.sflex.system.config.annotation.DBEncField;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WctbCustomerBaseBulkChDvo {
    private String sellTpCd; // 업무구분
    private String prcDvCd; // 처리구분
    private String cntrNo; // 계약번호
    private String cntrSn; // 계약일련번호
    private String cntrCstNo; // 고객번호
    private String rcpDtFrom; // 접수기간From
    private String rcpDtTo; // 접수기간To
    private String prtnrNo; // 파트너번호
    private String emadr; // 이메일
    @DBDecField
    @DBEncField
    private String cardAccNo; // 계좌/카드번호
    private String ogCd; // 조직코드
    private String sellPrtnrNo; //  판매파트너번호
    private String prtnrKnm; //  [이름] 파트너한글명
    private String sellTpNm; //  [업무구분] 판매유형명
    private String cstKnm; //  [고객명] 고객한글명
    private String cntrCnfmDtm; //  [접수일자] 계약확정일시
    private String txinvPblOjYn; //  [세금계산서] 세금계산서발행대상여부
    private String atmtStat; //  [자동이체정보-상태]
    private String mpyBsdt; //  [자동이체정보-이체일] 납부기준일자
    private String dpTpCd; //  입금유형코드
    private String dpTpNm; //  [자동이체정보-이체구분] 입금유형명
    private String bnkCdcoCd; //  은행/카드사코드
    private String bnkCdcoNm; //  [자동이체정보-카드사/은행] 은행/카드사명
    @DBDecField
    private String acnoCrcdno; //  [자동이체정보-카드/계좌번호]
    private String isBndl; //  [자동이체정보-묶음/대표] 묶음출금여부
    private String isBndlMast; //  [자동이체정보-묶음/대표] 묶음출금 대표주문 여부
    private String evidOcyInqr; //  [자동이체정보-선택]
    private String resign; // [자동이체정보-해지]
    private String istKnm; //  [설치자 정보-설치고객명] 수령자한글명
    private String wCralLocaraTno; //  [설치자 정보-휴대전화번호1]
    @DBDecField
    private String wMexnoEncr; //  [설치자 정보-휴대전화번호2]
    private String wCralIdvTno; //  [설치자 정보-휴대전화번호3]
    private String wLocaraTno; //  [설치자 정보-전화번호1]
    @DBDecField
    private String wExnoEncr; //  [설치자 정보-전화번호2]
    private String wIdvTno; //  [설치자 정보-전화번호3]
    private String wAdrZip; //  [설치자 정보-우편번호]
    private String wRnadr; //  [설치자 정보-주소1] 주소
    private String wRdadr; //  [설치자 정보-주소2] 상세주소
    private String cralLocaraTno; //  [계약자 정보-휴대전화번호1]
    @DBDecField
    private String mexnoEncr; //  [계약자 정보-휴대전화번호2]
    private String cralIdvTno; //  [계약자 정보-휴대전화번호3]
    private String locaraTno; //  [계약자 정보-전화번호1]
    @DBDecField
    private String exnoEncr; //  [계약자 정보-전화번호2]
    private String idvTno; //  [계약자 정보-전화번호3]
    private String adrZip; //  [계약자 정보-우편번호]
    private String rnadr; // [계약자정보-주소1]
    private String rdadr; // [계약자정보-주소2]
    private String rprsCntrNo; // 묶음출금 대표계약번호
    private String slDt; /* [매출일자] 매출인식일자 */
    private String dgr3LevlDgPrtnrNo; /* [계약마스터-지점장코드] 3차레벨대표파트너번호 */
    private String dgr2LevlDgPrtnrNo; /* [계약마스터-지역단장코드] 2차레벨대표파트너번호 */
    private String dgr1LevlDgPrtnrNo; /* [계약마스터-총괄단장코드] 1차레벨대표파트너번호 */
    private String dgr1LevlOgCd; /* [계약마스터-총괄단코드] 1차레벨조직코드 */
    private String curOgCd; /* [대리인마스터-조직코드] 조직코드 */
    private String curDgr3LevlDgPrtnrNo; /* [대리인마스터-지점장코드] 3차레벨대표파트너번호 */
    private String curDgr2LevlDgPrtnrNo; /* [대리인마스터-지역단장코드] 2차레벨대표파트너번호 */
    private String curDgr1LevlDgPrtnrNo; /* [대리인마스터-총괄단장코드] 1차레벨대표파트너번호 */
    private String curDgr1LevlOgCd; /* [대리인마스터-총괄단코드] 1차레벨조직코드 */
    private String chEpNo; /* [변경사번] */
}
