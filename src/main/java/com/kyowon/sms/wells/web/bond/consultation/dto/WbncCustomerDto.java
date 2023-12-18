package com.kyowon.sms.wells.web.bond.consultation.dto;

import org.apache.commons.lang.StringUtils;

import com.sds.sflex.common.utils.DbEncUtil;

import io.swagger.annotations.ApiModel;

public class WbncCustomerDto {

    @ApiModel(value = "WbncCustomerDto-FindBaseYmRes")
    public static record FindBaseYmRes(
        String baseYm /* 기준년월 */
    ) {}

    @ApiModel(value = "WbncCustomerDto-SearchReq")
    public record SearchReq(
        String schClctamNo, /* 집금번호 */
        String schCstNo, /* 고객번호 */
        String schCralLocaraTno, /* 계약휴대전화번호1 */
        String schMexnoEncr, /* 계약휴대전화번호2 */
        String schCralIdvTno, /* 계약휴대전화번호 3*/
        String schCstNm, /* 고객명 */
        String schClctamPsic, /* 집금담당자 */
        String schSfK, /* 세이프키 */
        String schIstCralLocaraTno, /* 설치휴대전화번호1 */
        String schIstMexnoEncr, /* 설치휴대전화번호2 */
        String schIstCralIdvTno, /* 설치휴대전화번호3 */
        String schDlqMcntStrt, /* from 연체개월 */
        String schDlqMcntEnd, /* to 연체개월 */
        String schFntDv, /* 이체구분 */
        String schFntDtStrt, /* from 이체일자 */
        String schFntDtEnd, /* to 이체일자 */
        String schOjBlamStrt, /* from 대상잔액 */
        String schOjBlamEnd, /* to 대상잔액 */
        String schCstDv, /* 고객구분 */
        String schCpsnRsgYn, /* 강제해지여부 */
        String schDv1, /* 구분1 */
        String schDv2, /* 구분2 */
        String schDv3, /* 구분3 */
        String schDv4 /* 구분4 */
    ) {
        public SearchReq {
            schMexnoEncr = StringUtils.isNotEmpty(schMexnoEncr) ? DbEncUtil.enc(schMexnoEncr)
                : schMexnoEncr;
            schIstMexnoEncr = StringUtils.isNotEmpty(schIstMexnoEncr) ? DbEncUtil.enc(schIstMexnoEncr)
                : schIstMexnoEncr;
        }
    }

    @ApiModel(value = "WbncCustomerDto-SearchRes")
    public record SearchRes(
        String ctt, /* 컨택 */
        String bilDt, /* 이체일자 */
        String dpTpCd, /* 입금유형코드*/
        String fnt, /* 이체 */
        String cstNo, /* 고객번호 */
        String cstNm, /* 고객명 */
        String dlqMcnt, /* 연체개월 */
        String fnlCnslD, /* 최종상담일 */
        String ojAmt, /* 대상금액 */
        String ojDp, /* 대상입금 */
        String ojBlam, /* 대상금액 - 대상입금 = 대상잔액 */
        String totDlqAmt, /* 연체금액 + 연체가산금 = 총연체금 */
        String totDlqDp, /* 연체입금금액 + 연체가산금입금금액 = 총연체입금 */
        String totDlqBlam, /* 총연체금 - 총연체입금 = 총연체잔액 */
        String dlqAmt, /* 연체금액 */
        String dlqDp, /* 연체입금 */
        String dlqBlam, /* 연체금액 - 연체입금 = 연체잔액 */
        String mmChramAmt, /* 월요금액 */
        String mmChramDp, /* 월요금입금 */
        String mmChramBlam, /* 당월요금금액 - 당월요금입금금액 = 월요금잔액 */
        String dlqAddAmt, /* 연체가산금액 */
        String dlqAddDp, /* 연체가산입금 */
        String dlqAdamtBlam, /* 연체가산금액 - 연체가산금입금금액 = 연체가산금잔액 */
        String ucAmt, /* 미수금액 */
        String ucDp, /* 미수입금 */
        String ucBlam, /* 미수금 - 총입금액 = 미수잔액 */
        String totDpAmt, /* 연체입금금액 + 연체가산입금금액 + 해지위약금입금금액 + 당월요금입금금액 = 총입금액 */
        String spmtSl, /* 추가매출 */
        String ccam, /* 위약금 */
        String lsfe, /* 분실료 */
        String rtlfe1, /* 렌탈료1 */
        String rtlfeIstm1, /* 렌탈료1할 */
        String rtlfe2, /* 렌탈료2 */
        String rtlfeIstm2, /* 렌탈료2할 */
        String clctamPrtnrNo, /* 집금번호 */
        String clctamIchr, /* 집급담당 */
        String cralLocaraTno, /* 계약휴대전화번호1 */
        String mexnoEncr, /* 계약휴대전화번호2 */
        String cralIdvTno, /* 계약휴대전화번호3 */
        String istCralLocaraTno, /* 설치휴대전화번호1 */
        String istMexnoEncr, /* 설치휴대전화번호2 */
        String istCralIdvTno, /* 설치휴대전화번호3 */
        String vtAcBnk, /* 가상계좌은행 */
        String vtAcNo, /* 가상계좌번호 */
        String sfk, /* 세이프키*/
        String clnPsbl, /* 회수가능성*/
        String clnPrcs, /* 회수절차*/
        String cstStat, /* 고객상태*/
        String cvcpInf, /* 민원정보*/
        String unuslArtc, /* 특이사항*/
        String cntrTpCd, /* 고객구분*/
        String cntrNo, /* 계약번호 */
        String cntrSn /* 계약일련번호 */
    ) {
        public SearchRes {
            mexnoEncr = StringUtils.isNotEmpty(mexnoEncr) ? DbEncUtil.dec(mexnoEncr) : mexnoEncr;
            istMexnoEncr = StringUtils.isNotEmpty(istMexnoEncr) ? DbEncUtil.dec(istMexnoEncr) : istMexnoEncr;
        }

    }

    @ApiModel(value = "WbncCustomerDto-FindReq")
    public static record FindReq(
        String schBaseYm, /* 기준년월 */
        String schCstNo, /* 고객번호 */
        String schCntrNo, /* 계약번호 */
        String schCntrSn/* 계약일련번호 */
    ) {}

    @ApiModel(value = "WbncCustomerDto-FindRes")
    public static record FindRes(
        String cntrNo, /* 계약번호 */
        String cntrSn, /* 계약일련번호 */
        String cntrDtlNo, /* 상세계약번호 */
        String cstNm, /* 고객명 */
        String sexDvCd, /* 성별코드 */
        String sexDvNm, /* 성별코드명 */
        String bryyMmdd, /* 생년월일 */
        String copnDvCd, /* 고객구분 */
        String cntrCralLocaraTno, /* 계약처휴대지역전화번호 */
        String cntrMexnoEncr, /* 계약처휴대전화국번호암호화 */
        String cntrCralIdvTno, /* 계약처휴대전화번호3 */
        String cstNo, /* 고객번호 */
        String sfk, /* 세이프키 */
        String buNotiTpCd, /* 부담유형 */
        String rcgvpKnm, /* 설치자명 */
        String istLocaraTno, /* 설치처지역전화번호 */
        String istExnoEncr, /* 설치처전화국번호암호화 */
        String istIdvTno, /* 설치처개별전화번호 */
        String istCralLocaraTno, /* 설치처휴대지역전화번호 */
        String istMexnoEncr, /* 설치처휴대전화국번호암호화 */
        String istCralIdvTno, /* 설치처휴대개별전화번호 */
        String cntrSppZip, /* 계약처우편번호 */
        String cntrSppAdr, /* 계약처기본주소 */
        String cntrSppDtlAdr, /* 계약처상세주소 */
        String istSppZip, /* 설치처우편번호 */
        String istSppAdr, /* 설치처기본주소 */
        String istSppDtlAdr, /* 설치처상세주소 */
        String pdNm, /* 상품명 */
        String cntrDtlStatCd, /* 현재회원상태 */
        String cntrDtlStatNm, /* 현재회원상태명 */
        String rcpDt, /* 접수일 */
        String istDt, /* 설치일 */
        String canDt, /* 취소일 */
        String reqdDt, /* 철거일 */
        String bnk, /* 은행/카드사 */
        String vtAc, /* 자동이체 */
        String fntDt, /* 이체일자 */
        String mpyMthdTpCd, /* 이체상태 */
        String mpyMthdTpNm, /* 이체상태 */
        String plar, /* 판매자 */
        String plarCralLocaraTno,
        String plarMexnoEncr,
        String plarCralIdvTno, /* 판매자 휴대전화번호 */
        String cltnDt, /* 판매자 해약일 */
        String dsmn, /* 지국장 */
        String dsmnCralLocaraTno,
        String dsmnMexnoEncr,
        String dsmnCralIdvTno, /* 지국장 휴대전화번호 */
        String dsmnCltnDt, /* 지국장 해약일 */
        String bldCd, /* 소속빌딩코드 */
        String bldNm, /* 소속빌딩명 */
        String bldLocaraTno,
        String bldMexnoEncr,
        String bldIdvTno, /* 빌딩 전화번호 */
        String alncCd, /* 제휴코드 */
        String alncNm, /* 제휴명 */
        String alncStat, /* 제휴상태 */
        String vacNo, /* 가상계좌번호 */
        String tfDt, /* 이관일자 */
        String prtnrNo, /* 집금담당번호 */
        String prtnrNm, /*집금담당명*/
        String dfltDt, /* 채무불이행 */
        String lwmMoCn, /* 법조치 */
        String slStp, /* 매출중지 */
        String bndClnPsblDvCd, /* 회수가능성 */
        String bndClnPrcsDvCd, /* 회수절차 */
        String lwscBilAmt, /* 소송비 청구금액 */
        String lwmDpAmt, /* 소송비 입금액 */
        String lwscBlam, /* 소송비 잔액 */
        String ucAmt, /* 미수금합산 */
        String bzrno, /* 사업자번호 */
        String tnoCnt1,
        String tnoCnt2,
        String tnoCnt3,
        String bndBizDvCd, /* 채권업무구분코드 */
        String baseYm, /* 기준년월 */
        String rsNm /* 이체상태 */
    ) {
        public FindRes {
            cntrMexnoEncr = StringUtils.isNotEmpty(cntrMexnoEncr) ? DbEncUtil.dec(cntrMexnoEncr) : cntrMexnoEncr;
            istExnoEncr = StringUtils.isNotEmpty(istExnoEncr) ? DbEncUtil.dec(istExnoEncr) : istExnoEncr;
            istMexnoEncr = StringUtils.isNotEmpty(istMexnoEncr) ? DbEncUtil.dec(istMexnoEncr) : istMexnoEncr;
            plarMexnoEncr = StringUtils.isNotEmpty(plarMexnoEncr) ? DbEncUtil.dec(plarMexnoEncr) : plarMexnoEncr;
            bldMexnoEncr = StringUtils.isNotEmpty(bldMexnoEncr) ? DbEncUtil.dec(bldMexnoEncr) : bldMexnoEncr;
            dsmnMexnoEncr = StringUtils.isNotEmpty(dsmnMexnoEncr) ? DbEncUtil.dec(dsmnMexnoEncr) : dsmnMexnoEncr;
            vtAc = StringUtils.isNotEmpty(vtAc) ? DbEncUtil.dec(vtAc) : vtAc;
        }

    }

    @ApiModel(value = "WbncCustomerDto-FindCustomerDetailReq")
    public static record FindCustomerDetailReq(
        String schBaseYm, /* 기준년월 */
        String schCstNo, /* 고객번호 */
        String schCntrNo, /* 계약번호 */
        String schCntrSn/* 계약일련번호 */
    ) {}

    @ApiModel(value = "WbncCustomerDto-FindCustomerDetailRes")
    public static record FindCustomerDetailRes(
        String ctt, /* 이체 */
        String bndBizDvCd, /* 업무구분 */
        String bndBizDvNm, /* 업무구분명 */
        String pdClsfNm, /* 제품군 */
        String pdNm, /* 제품명 */
        String cntrNo, /* 계약번호 */
        String cntrSn, /* 계약일련번호 */
        String cntrDtlNo, /* 상세계약번호 */
        String cstKnm, /* 고객명 */
        String dlqMcn, /* 연체개월 */
        String authRsgCnfmdt, /* 직권해지일자 */
        String ojAmt, /* 대상금액 */
        String ojDp, /* 대상입금 */
        String ojBlam, /* 대상금액 - 대상입금 = 대상잔액 */
        String totDlqAmt, /* 연체금액 + 연체가산금 = 총연체금 */
        String totDlqDp, /* 연체입금금액 + 연체가산금입금금액 = 총연체입금 */
        String totDlqBlam, /* 총연체금 - 총연체입금 = 총연체잔액 */
        String dlqAmt, /* 연체금액 */
        String dlqDp, /* 연체입금 */
        String dlqBlam, /* 연체금액 - 연체입금 = 연체잔액 */
        String thmOcDlqAddAmt, /* 당월발생연체가산금액 */
        String mmChramAmt, /* 월요금액 */
        String mmChramDp, /* 월요금입금 */
        String mmChramBlam, /* 당월요금금액 - 당월요금입금금액 = 월요금잔액 */
        String dlqAddAmt, /* 연체가산금액 */
        String dlqAddDp, /* 연체가산입금 */
        String dlqAdamtBlam, /* 연체가산금액 - 연체가산금입금금액 = 연체가산금잔액 */
        String svCs, /*서비스비용*/
        String svDp, /*서비스입금*/
        String svBlam, /*서비스잔액*/
        String ucAmt, /* 미수금액 */
        String rsgBorDpAmt, /* 해지위약입금금액 */
        String ucDp, /* 미수입금 */
        String ucBlam, /* 미수금 - 총입금액 = 미수잔액 */
        String totDpAmt, /* 연체입금금액 + 연체가산입금금액 + 해지위약금입금금액 + 당월요금입금금액 = 총입금액 */
        String spmtSl, /* 추가매출 */
        String lsRntf, /* 분실손료 */
        String vacVncoDvCd, /*가상계좌구분*/
        String bnkNm, /* 가상계좌은행 */
        String vacNo, /* 가상계좌번호 */
        String ccam, /* 위약금 */
        String lsfe, /* 분실료 */
        String rtlfe1, /* 렌탈료1 */
        String rtlfeIstm1, /* 렌탈료1할 */
        String rtlfe2, /* 렌탈료2 */
        String rtlfeIstm2, /* 렌탈료2할 */
        String dprNm, /* 입금자 */
        String prtnrNo, /* 집금담당번호 */
        String prtnrNm, /*집금담당명*/
        String bndTfDt, /* 이관일자 */
        String sfkVal, /* 세이프키*/
        String cstNo, /* 세이프키*/
        String baseYm /* 기준년월*/
    ) {}

    @ApiModel(value = "WbncCustomertDetailDto-FindUnusualArticlesReq")
    public static record FindUnusualArticlesReq(
        String schBaseYm, /* 기준년월 */
        String schCstNo, /* 고객번호 */
        String schCntrNo, /* 계약번호 */
        String schCntrSn/* 계약일련번호 */
    ) {}

    @ApiModel(value = "WbncCustomertDetailDto-FindUnusualArticlesRes")
    public static record FindUnusualArticlesRes(
        String cnslUnuitmCn /* 특이사항 */
    ) {}

    @ApiModel(value = "WbncCustomertDetailDto-FindCounselHistoryReq")
    public static record FindCounselHistoryReq(
        String schBaseYm, /* 기준년월 */
        String schCstNo, /* 고객번호 */
        String schCntrNo, /* 계약번호 */
        String schCntrSn/* 계약일련번호 */
    ) {}

    @ApiModel(value = "WbncCustomerDto-FindCounselHistoryRes")
    public static record FindCounselHistoryRes(
        String telCnslId, /* 전화상담ID */
        String inDt, /* 입력일시 */
        String inIchr, /* 등록담당사번 */
        String inIchrNm, /* 등록담당자 */
        String cnslPh, /* 상담경로 */
        String crncyRs, /* 통화결과 */
        String dprNm, /* 입금자명 */
        String cnslCn /* 전화상담내용 */
    ) {}

    @ApiModel("WbncCustomerDto-SaveUnuitmCnReq")
    public record SaveUnuitmCnReq(
        String bndUnuitmId, /* 채권특이사항ID */
        String bndCntrTpCd, /* 채권계약유형코드 */
        String bndBizDvCd, /* 채권업무구분코드 */
        String cstNo, /* 고객번호 */
        String cntrNo, /* 계약번호 */
        String cntrSn, /* 계약일련번호 */
        String clctnOjOgTpCd, /* 추심대상조직유형코드 */
        String clctnOjPrtnrNo, /* 추심대상파트너번호 */
        String cnslUnuitmCn, /* 상담특이사항내용 */
        String cvcpCn /* 민원내용 */
    ) {}

    @ApiModel("WbncCustomerDto-SaveCounselReq")
    public record SaveCounselReq(
        String telCnslId, /* 전화상담ID */
        String bndCntrTpCd, /* 채권계약유형코드 */
        String bndBizDvCd, /* 채권업무구분코드 */
        String cstNo, /* 고객번호 */
        String cntrNo, /* 계약번호 */
        String cntrSn, /* 계약일련번호 */
        String clctnOjOgTpCd, /* 추심대상조직유형코드 */
        String clctnOjPrtnrNo, /* 추심대상파트너번호 */
        String clctnOjPrtnrBzrno, /* 추심대상파트너사업자등록번호 */
        String clctamPrtnrNo, /* 집금파트너번호 */
        String telCnslPhCd, /* 전화상담경로코드 */
        String bndCnslMtrDvCd, /* 채권상담자료구분코드 */
        String telCnslRsCd, /* 전화상담결과코드 */
        String cnslCstPrpCd, /* 상담고객속성코드 */
        String telCnslCn, /* 전화상담내용 */
        String bndClnPsblDvCd, /* 채권회수가능성구분코드 */
        String bndCnslCstStatCd, /* 채권상담고객상태코드 */
        String bndClnPrcsDvCd, /* 채권회수절차구분코드 */
        String dprNm, /* 입금자명 */
        String rdgId, /* 녹취ID */

        String promBooId, /* 약속예약ID */
        String promDt, /* 약속일자 */
        String promHh, /* 약속시간 */
        String promTp, /* 집금약속유형코드 */
        String promAmt, /* 회수약속금액 */
        String promCn, /* 약속내용 */
        String promFshYn /* 약속완료여부 */
    ) {}

    @ApiModel(value = "WbncCustomerDto-SearchUserInfoReq")
    public static record SearchUserInfoReq(
        String prtnrNo, /*파트너번호*/
        String ogTpCd /*조직유형*/
    ) {}

    @ApiModel(value = "WbncCustomerDto-SearchUserInfoRes")
    public static record SearchUserInfoRes(
        String tno1, /*전화번호1*/
        String tno2, /*전화번호2*/
        String tno3 /*전화번호3*/
    ) {}

    @ApiModel(value = "WbncCustomerDto-FindRecIdReq")
    public static record FindRecIdReq(
        String inlnNo /*파트너번호*/
    ) {}

    @ApiModel(value = "WbncCustomerDto-FindRecIdRes")
    public static record FindRecIdRes(
        String recId /*녹취ID*/
    ) {}

    @ApiModel(value = "WbncCustomerDto-FindCounselRegistrationReq")
    public static record FindCounselRegistrationReq(
        String schCstNo, /*고객번호*/
        String schCntrNo, /*계약번호*/
        String schCntrSn /*계약일련번호*/
    ) {}

    @ApiModel(value = "WbncCustomerDto-FindCounselRegistrationRes")
    public static record FindCounselRegistrationRes(
        String cnslId, /* 전화상담ID */
        String cnslPh, /*상담경로*/
        String crncyRs, /*통화결과*/
        String cstPrp, /*고객속성*/
        String dprNm, /*입금자*/
        String cnslTp, /*상담유형*/
        String cstStat, /*고객상태*/
        String clnPsbl, /*회수가능성*/
        String clnPrcs /*회수절차*/
    ) {}
}
