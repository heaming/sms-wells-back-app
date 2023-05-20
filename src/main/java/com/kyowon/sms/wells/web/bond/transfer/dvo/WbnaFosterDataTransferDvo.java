package com.kyowon.sms.wells.web.bond.transfer.dvo;

import com.sds.sflex.system.config.annotation.DBDecField;
import com.sds.sflex.system.config.annotation.DBEncField;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WbnaFosterDataTransferDvo {
    private String lcuser; /* LCUSER 고객번호 */
    private String clcoCd; /* LCGUB1 추심사코드 		추심사구분 */
    private String clcoTfDt; /* LCTRDT 이관일자 */
    private String cstRrno; /* LCCINO 주민번호/사업자등록번호 */
    private String cstNm; /* LCCNAM 고객명 */
    private String vacBnkCd; /* LCBANK 가상계좌은행코드 	가상은행 */
    private String vacNo; /* LCBNNO 가상계좌번호 		가상계좌 */
    private Integer dlqMcn; /* LCDCNT 연체개월수 		연체개월 */
    private String cstZip; /* LCHZIP 고객우편번호 */
    private String cstBasAdr; /* LCADD1 고객기본주소 */
    private String cstDtlAdr; /* LCADD2 고객상세주소 */
    private String cstEtcAdr; /* LCADD3 고객기타주소 */
    private String istPlcLocaraTno; /* LCHTEL 설치장소지역전화번호*/
    @DBEncField
    @DBDecField
    private String istPlcExnoEncr; /* LCHTEL 설치장소전화국번호암호화 */
    private String istPlcIdvTno; /* LCHTEL 설치장소개별전화번호 */
    private String istCstCralLocaraTno; /* LCBTEL 설치고객휴대지역전화번호 */
    @DBEncField
    @DBDecField
    private String istCstMexnoEncr; /* LCBTEL 설치고객휴대전화국번호암호화 */
    private String istCstCralIdvTno; /* LCBTEL 설치고객휴대개별전화번호 */
    private String mngtAoffceOgId; /* LCDDPT 관리사무소조직ID */
    private String cnrNm; /* LCCENT 센터명 */
    private String cnrLocaraTno; /* LCCTEL 센터지역전화번호 */
    @DBEncField
    @DBDecField
    private String cnrMexnoEncr; /* LCCTEL 센터전화국번호암호화 */
    private String cnrIdvTno; /* LCCTEL 센터개별전화번호 */
    private String pdCd; /* LCICDE 상품코드 */
    private String pdNm; /* LCINAM 상품명  */
    private Integer pdQty; /* LCIQTY 상품수량 */
    private String istDt; /* LCSETT 설치일자 */
    private String rtrnDt; /* LCCANT 반환일자 */
    private Long ucAmt; /* LCAMT1 미수금액 */
    private Long dlqAmt; /* LCAMT2 연체금액 */
    private Long thmChramAmt; /* LCAMT3 당월요금 */
    private Long rsgBorAmt; /* LCAMT4 해지위약금액 */
    private Long lsRntf; /* LCAMT5 분실손료 */
    private Long nadvAmt; /* LCAMT6 미도래금액 */
    private String bndBizDvCd; /* LCTYPE 채권업무구분코드 */
    private String rcpAoffceCd; /* LCTJOB 접수사무소코드 */
    private String dlqRckDt; /* LCDDAT 연체기산일 */
    private String mpyMthdTpCd; /* LCCHK1 납부방식유형코드 */
    private String bilDt; /* LCTRDD 청구일자 */
    private Long dlqAddAmt; /* LCAM82 연체가산금 */
    private Long spmtSlAmt; /* LCADAM 추가매출금액 */
    private String bnkCd; /* LCBKNO 은행코드 */
    private String cstNo; /* LCKKEY 고객코드 교원키 */
    private String cntrtZip; /* LCCZIP 계약자우편번호 */
    private String cntrtBasAdr; /* LCCAD1 계약자기본주소 */
    private String cntrtDtlAdr; /* LCCAD2 계약자상세주소 */
    private String cntrtEtcAdr; /* LCCAD3 계약자기타주소 */
    private String cntrtLocaraTno; /* LCHNO1 계약자지역전화번호 */
    @DBEncField
    @DBDecField
    private String cntrtExnoEncr; /* LCHNO1 계약자전화국번호암호화 */
    private String cntrtIdvTno; /* LCHNO1 계약자개별전화번호 */
    private String cntrtCralLocaraTno; /* LCBNO1 계약자휴대지역전화번호 */
    @DBEncField
    @DBDecField
    private String cntrtMexnoEncr; /* LCBNO1 계약자휴대전화국번호암호화 */
    private String cntrtCralIdvTno; /* LCBNO1 계약자휴대개별전화번호 */
    private String lcbno1;
    private String alncmpCd; /* LCETC8 제휴사코드 */
    private String alncNm; /* LCENAM 제휴명 */
    private String alncStatNm; /* LCJNAM 제휴상태명 */
    private String sfkVal; /* LCSKEY 세이프키값 */

    public String toTransmissionContent() {
        StringBuilder transmissionContent = new StringBuilder();
        transmissionContent.append(clcoCd);
        transmissionContent.append("|");
        transmissionContent.append(clcoTfDt);
        transmissionContent.append("|");
        transmissionContent.append(lcuser);
        transmissionContent.append("|");
        transmissionContent.append(cstRrno);
        transmissionContent.append("|");
        transmissionContent.append(cstNm);
        transmissionContent.append("|");
        transmissionContent.append(vacBnkCd);
        transmissionContent.append("|");
        transmissionContent.append(vacNo);
        transmissionContent.append("|");
        transmissionContent.append(dlqMcn);
        transmissionContent.append("|");
        transmissionContent.append(cstZip);
        transmissionContent.append("|");
        transmissionContent.append(cstBasAdr);
        transmissionContent.append("|");
        transmissionContent.append(cstDtlAdr);
        transmissionContent.append("|");
        transmissionContent.append(cstEtcAdr);
        transmissionContent.append("|");
        transmissionContent.append(istPlcLocaraTno + istPlcExnoEncr + istPlcIdvTno);//lchtel
        transmissionContent.append("|");
        transmissionContent.append(istCstCralLocaraTno + istCstMexnoEncr + istCstCralIdvTno);//lcbtel
        transmissionContent.append("|");
        transmissionContent.append(mngtAoffceOgId);
        transmissionContent.append("|");
        transmissionContent.append(cnrNm);
        transmissionContent.append("|");
        transmissionContent.append(cnrLocaraTno + cnrMexnoEncr + cnrIdvTno);//lcctel
        transmissionContent.append("|");
        transmissionContent.append(pdCd);
        transmissionContent.append("|");
        transmissionContent.append(pdNm);
        transmissionContent.append("|");
        transmissionContent.append(pdQty);
        transmissionContent.append("|");
        transmissionContent.append(istDt);
        transmissionContent.append("|");
        transmissionContent.append(rtrnDt);
        transmissionContent.append("|");
        transmissionContent.append(ucAmt);
        transmissionContent.append("|");
        transmissionContent.append(dlqAmt);
        transmissionContent.append("|");
        transmissionContent.append(thmChramAmt);
        transmissionContent.append("|");
        transmissionContent.append(rsgBorAmt);
        transmissionContent.append("|");
        transmissionContent.append(lsRntf);
        transmissionContent.append("|");
        transmissionContent.append(nadvAmt);
        transmissionContent.append("|");
        transmissionContent.append(bndBizDvCd);
        transmissionContent.append("|");
        transmissionContent.append(rcpAoffceCd);
        transmissionContent.append("|");
        transmissionContent.append(dlqRckDt);
        transmissionContent.append("|");
        transmissionContent.append(mpyMthdTpCd);
        transmissionContent.append("|");
        transmissionContent.append(bilDt);
        transmissionContent.append("|");
        transmissionContent.append(dlqAddAmt);
        transmissionContent.append("|");
        transmissionContent.append(spmtSlAmt);
        transmissionContent.append("|");
        transmissionContent.append(bnkCd);
        transmissionContent.append("|");
        transmissionContent.append(cstNo);
        transmissionContent.append("|");
        transmissionContent.append(cntrtZip);
        transmissionContent.append("|");
        transmissionContent.append(cntrtBasAdr);
        transmissionContent.append("|");
        transmissionContent.append(cntrtDtlAdr);
        transmissionContent.append("|");
        transmissionContent.append(cntrtEtcAdr);
        transmissionContent.append("|");
        transmissionContent.append(cntrtLocaraTno + cntrtExnoEncr + cntrtIdvTno);//lchno1
        transmissionContent.append("|");
        transmissionContent.append(lcbno1);
        transmissionContent.append("|");
        transmissionContent.append(alncmpCd);
        transmissionContent.append("|");
        transmissionContent.append(alncNm);
        transmissionContent.append("|");
        transmissionContent.append(alncStatNm);
        transmissionContent.append("|");
        transmissionContent.append(sfkVal);
        transmissionContent.append("\n");
        return transmissionContent.toString();
    }
}
