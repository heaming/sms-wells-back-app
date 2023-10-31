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
        String schClctamNo,
        String schCstNo,
        String schCralLocaraTno, /* 계약휴대전화번호1 */
        String schMexnoEncr, /* 계약휴대전화번호2 */
        String schCralIdvTno, /* 계약휴대전화번호3 */
        String schCstNm,
        String schClctamPsic,
        String schSfK,
        String schIstCralLocaraTno, /* 설치휴대전화번호1 */
        String schIstMexnoEncr, /* 설치휴대전화번호2 */
        String schIstCralIdvTno, /* 설치휴대전화번호3 */
        String schDlqMcntStrt,
        String schDlqMcntEnd,
        String schFntDv,
        String schFntDtStrt,
        String schFntDtEnd,
        String schOjBlamStrt,
        String schOjBlamEnd,
        String schCstDv,
        String schCpsnRsgYn,
        String schDv
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
        String schBaseYm,
        String schCstNo,
        String schCntrNo,
        String schCntrSn
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
        String schBaseYm,
        String schCstNo,
        String schCntrNo,
        String schCntrSn
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
        String schBaseYm,
        String schCstNo,
        String schCntrNo,
        String schCntrSn
    ) {}

    @ApiModel(value = "WbncCustomertDetailDto-FindUnusualArticlesRes")
    public static record FindUnusualArticlesRes(
        String cnslUnuitmCn
    ) {}

    @ApiModel(value = "WbncCustomertDetailDto-FindCounselHistoryReq")
    public static record FindCounselHistoryReq(
        String schBaseYm,
        String schCstNo,
        String schCntrNo,
        String schCntrSn
    ) {}

    @ApiModel(value = "WbncCustomerDto-FindCounselHistoryRes")
    public static record FindCounselHistoryRes(
        String telCnslId,
        String inDt,
        String inIchr,
        String inIchrNm,
        String cnslPh,
        String crncyRs,
        String dprNm,
        String cnslCn
    ) {}

    @ApiModel("WbncCustomerDto-SaveUnuitmCnReq")
    public record SaveUnuitmCnReq(
        String bndUnuitmId,
        String bndCntrTpCd,
        String bndBizDvCd,
        String cstNo,
        String cntrNo,
        String cntrSn,
        String clctnOjOgTpCd,
        String clctnOjPrtnrNo,
        String cnslUnuitmCn,
        String cvcpCn
    ) {}

    @ApiModel("WbncCustomerDto-SaveCounselReq")
    public record SaveCounselReq(
        String telCnslId,
        String bndCntrTpCd,
        String bndBizDvCd,
        String cstNo,
        String cntrNo,
        String cntrSn,
        String clctnOjOgTpCd,
        String clctnOjPrtnrNo,
        String clctnOjPrtnrBzrno,
        String clctamPrtnrNo,
        String telCnslPhCd,
        String bndCnslMtrDvCd,
        String telCnslRsCd,
        String cnslCstPrpCd,
        String telCnslCn,
        String bndClnPsblDvCd,
        String bndCnslCstStatCd,
        String bndClnPrcsDvCd,
        String dprNm,
        String rdgId,

        String promBooId,
        String promDt,
        String promHh,
        String promTp,
        String promAmt,
        String promCn,
        String promFshYn
    ) {}

    @ApiModel(value = "WbncCustomerDto-FindUserInfoReq")
    public static record FindUserInfoReq(
        String prtnrNo,
        String ogTpCd
    ) {}

    @ApiModel(value = "WbncCustomerDto-FindUserInfoRes")
    public static record FindUserInfoRes(
        String tno1,
        String tno2,
        String tno3
    ) {}

    @ApiModel(value = "WbncCustomerDto-FindRecIdReq")
    public static record FindRecIdReq(
        String inlnNo
    ) {}

    @ApiModel(value = "WbncCustomerDto-FindRecIdRes")
    public static record FindRecIdRes(
        String recId
    ) {}

    @ApiModel(value = "WbncCustomerDto-FindCounselRegistrationReq")
    public static record FindCounselRegistrationReq(
        String schCstNo,
        String schCntrNo,
        String schCntrSn
    ) {}

    @ApiModel(value = "WbncCustomerDto-FindCounselRegistrationRes")
    public static record FindCounselRegistrationRes(
        String cnslPh,
        String crncyRs,
        String cstPrp,
        String dprNm,
        String cnslTp,
        String cstStat,
        String clnPsbl,
        String clnPrcs
    ) {}
}
