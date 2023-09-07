package com.kyowon.sms.wells.web.closing.performance.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;

public class WdccSalesPerformDto {
    /**
     * 매출현황 검색 조건
     * @param cntrNo 계약번호
     * @param cntrSn 계약일련번호
     * @param fromSlClYy 검색시작일
     * @param toSlClYy 검색종료일
     * @param slClYm 매출년월
     */
    @ApiModel(value = "WdccSalesPerformDto-SearchReq")
    public record SearchReq(
        String cntrNo,
        String cntrSn,
        String fromSlClYy,
        String toSlClYy,
        String slClYm
    ) {}

    /**
     * 매출현황 검색 결과
     * @param cntrNo 계약번호
     * @param cntrSn 계약일련번호
     * @param slClYm 매출년월
     * @param slStpYn 매출중지
     * @param rentalTn 렌탈차월
     * @param slCtrDvCd 매출조정구분코드
     * @param prmMcn 선납개월
     * @param thmSlSumAmt 매출합계
     * @param borAmt 위약금액
     * @param lsRntf 분실손료
     * @param sumBorAmt 위약금
     * @param dpAmt 입금금액
     * @param eotAtam 선수금액
     * @param thmUcBlam 미수금액
     * @param thmOcDlqAmt 연체금액
     * @param dlqMcn 연체개월수
     * @param btdDlqAddAmt 가산금기초
     * @param thmOcDlqAddAmt 가산금발생
     * @param thmCtrDlqAddAmt 가산금공제
     * @param thmDlqAddDpSumAmt 가산금입금
     * @param thmDlqAddRfndSumAmt 가산금환불
     * @param eotDlqAddAmt 가산금기말
     * @param slStpAmt 매출중지금액
     * @param sellTpCd 판매유형
     * @param islease 리스여부
     */
    @ApiModel(value = "WdccSalesPerformDto-SearchRes")
    public record SearchRes(
        String cntrNo,
        String cntrSn,
        String slClYm,
        String slStpYn,
        String rentalTn,
        String slCtrDvCd,
        String prmMcn,
        String thmSlSumAmt,
        String borAmt,
        String lsRntf,
        String sumBorAmt,
        String dpAmt,
        String eotAtam,
        String thmUcBlam,
        String thmOcDlqAmt,
        String dlqMcn,
        String btdDlqAddAmt,
        String thmOcDlqAddAmt,
        String thmCtrDlqAddAmt,
        String thmDlqAddDpSumAmt,
        String thmDlqAddRfndSumAmt,
        String eotDlqAddAmt,
        String slStpAmt,
        String sellTpCd,
        String islease
    ) {}

    @ApiModel("WdccSalesPerformDto-SearchSinglePaymentReq")
    public record SearchSinglePaymentReq(
        @NotBlank
        String cntrNo,
        @NotBlank
        String cntrSn
    ) {}

    @ApiModel("WdccSalesPerformDto-SearchSinglePaymentBaseRes")
    public record SearchSinglePaymentBaseRes(
        String cntrNo, /* 계약번호 */
        Integer cntrSn, /* 계약일련번호 */
        String cntrDtlNo, /* 계약상세번호 */
        String sellTpCd, /* 판매유형 */
        String sellTpCdNm, /* 판매유형명 */
        String cstNo, /* 고객번호 */
        String cstKnm, /* 고객명 */
        String copnDvCd, /* 고객구분 */
        String copnDvCdNm, /* 고객구분명 */
        String prtnrNo, /* 판매자사번 */
        String prtnrKnm, /* 판매자이름 */
        String cntrDt, /* 계약일 */
        String slDt, /* 매출일자 */
        String canDt, /* 취소일 */
        String ogInfo, /* 조직정보 */
        String pdInfo, /* 상품정보 */
        Long dscAmt, /* 할인금액 */
        String dscTp, /* @TO-DO 할인유형코드(LC30.LCETC4) */
        String alncmpCd, /* 제휴사코드 */
        String alncmpCdNm, /* 제휴상태(LC30.LCCK03) */
        String sellEvCd, /* 행사코드 */
        String sellEvCdNm, /* 행사정보(LC30.LCETC9) */
        Long sellAmt, /* 판매가격 */
        Long alncFee, /* 제휴수수료(LC30.LCFAMT) */
        Long subscAmt, /* 청약금 @TO-BE는 청약금+인수금(CWSAMT+CWAAMT) 하나로 관리(인수금액은 TOBE 미관리) */
        String tkAmt, /* @TO-DO 인수금액(CW50.CWAAMT) */
        //        Long tkAmt, /* @TO-DO 인수금액(CW50.CWAAMT) */
        Long crpUcAmt, /* 법인미수금액(LC30.LCGAMT) */
        Long istmPcamAmt, /* 잔액(CW50.CWJAMT(할부원금) 값이 0이면, LC30.LCJAMT 값) */
        Integer istmMcn, /* 할부개월(CW50.CWMONT) */
        Long mmIstmAmt, /* 월할부금액(CW50.CWMAMT) */
        String istmInfo, /* 할부정보(CW50.CWMONT, CW50.CWMAMT) */
        Long istmFeeLvyAmt, /* 수수료금액(CW50.CWCAMT 값이 0이면, LC30.LCCAMT) */
        String fulpyDt, /* 완불일자 */
        String fstFulpyDt, /* 최초완불일자(CW50.CWLSTY) -- @TO-DO TOBE는 관리하지 않음 */
        String cntrChnDt, /* 변동일자(CW50.CWCHGY) */
        String cwkgubnm, /* 계정구분(CW50.CWKGUB) CASE WHEN CW50.CWKGUB='2' THEN '대손' ELSE ' ' END AS CWKGUBNM */
        String pdChDt, /* 상변일자(LC30.LCCHGY) */
        String slChDt, /* 매변일자(LC30.LCMAEY) */
        String slChSn, /* @TO-DO 매변일련번호(CW45.CWSEQN) ※ TOBE 관리 필드 없음 */
        Long istmTotDpAmt, /* 할부입금액(CW50.CWPAMT) */
        Long subscTotDpAmt, /* 청약입금액(ASIS쿼리에는 조회하지 않음) */
        Long slChTotDpAmt, /* 매변입금액(CW45.CWPAMT) */
        //        Long tkDpAmt, /* @TO-DO 인수입금액(CW50.CWAAMT) ※ 인수금액 TOBE 삭제 */
        String tkDpAmt, /* @TO-DO 인수입금액(CW50.CWAAMT) ※ 인수금액 TOBE 삭제 */
        //        Long crpDpAmt, /* @TO-DO 법인미수금액(CW50.CWGAMT) 수납쪽 입금구분에 법인미수금 코드가 있는데, TOBE에서는 계약금으로 합치는거로 되어있음 */
        String crpDpAmt, /* @TO-DO 법인미수금액(CW50.CWGAMT) 수납쪽 입금구분에 법인미수금 코드가 있는데, TOBE에서는 계약금으로 합치는거로 되어있음 */
        Long totDpAmt, /* 입금총액(청약금액+인수금액+납입금액, CW50.CWSAMT+CW50.CWAAMT+CW50.CWPAMT) */
        String dpTpCd, /* 이체구분코드(LC31.LCCHK1) */
        String dpTpCdNm, /* 이체구분코드명(LC31.LCCHK1) */
        Integer mpyBsdt, /* 이체일자 */
        Integer dlqMcn, /* 연체개월수(CW50.CWDCNT) */
        Long thmOcDlqAmt, /* 연체금액(CW50.CWDAMT) */
        Long eotUcAmt, /* 연체금액(CW50.CWDAMT) */
        Long ucBlam, /* 미수금액(CW50.CWQAMT - (CW50.CWSAMT + CW50.CWAAMT + CW50.CWPAMT)) */
        String ucPrt, /* 미수출력(CW50.CWFLG2) */
        String acdbtDt, /* 대손일자(CW50.CWBDDT) */
        Long dfaAmt, /* 대손금액(CW50.CWAMT4) */
        Long dfaBlam, /* 대손잔액(대손금액 - 대손입금액) */
        Long dfaDpAmt, /* 대손입금액(CW5400P.CWAMT1) */
        String dfaRveDt, /* 대손납입일자(CW5400P.CWACTY) */
        String dfaFnsAmt, /* @TO-DO 대손완불금액(ASIS 쿼리 이상함) */
        //        Long dfaFnsAmt, /* @TO-DO 대손완불금액(ASIS 쿼리 이상함) */
        String bndAsnDt, /* 수임일자(CW50.CWPRTY) */
        String clctamDvCd, /* 집금구분(CW49.CWPGUB) */
        String clctamDvCdNm, /* 집금유형명 */
        String clctamPrtnrNo, /* 집금담당자사번(CW50.CWPRTY) */
        String clctamPrtnrKnm, /* 집금담당(CW50.CWPRTY) */
        String dsbGurTpCd, /* 지급보증유형(CW50.CWJFLG) */
        String DsbGurTpCdNm, /* 지급보증유형명(CW50.CWJFLG) */
        String buNotiDt, /* 부담통보일자(CW50.CWSTBY) */
        String redfDt, /* 되물림일자(CW50.CWSMLY)*/
        String adsbDt, /* 재지급일자(CW50.CWSJGY) */
        String buNotiTpCd, /* 통보내용(CW50.CWTFLG) */
        String buNotiTpCdNm, /* 통보내용명(CW50.CWTFLG) */
        String vacBnkCd, /* 은행명 */
        String vacNo /* 가상계좌번호 */
    ) {}
    @ApiModel("WdccSalesPerformDto-SearchSinglePaymentSalesRes")
    public record SearchSinglePaymentSalesRes(
        String slClYm, /* 기준년월(CW49.CWPAYY) */
        String cntrNo, /* 계약번호 */
        Integer cntrSn, /* 계약일련번호 */
        String dlqMcn, /* 연체개월(CW49.CWDCNT) */
        Long eotDlqAmt, /* 연체금액(CW49.CWDAMT) */
        String clctamDvCdNm, /* 집금구분(CW49.CWPGUB) */
        String clctamPrtnrNo, /* @TO-DO 집금유형담당(CW49.CWPCDE) */
        Long slDpAggAmt, /* 납입금액(CW49.CWPAMT) */
        Long thmIntamDpAmt, /* 당월할부금입금금액(CW49.CWAMT3) */
        Long crpUcAmt, /* 법인미수(CW49.CWWAM1) */
        Long sellAmt, /* 판매금(CW49.CWTAMT) */
        String istmInfo/* 할부개월*할부금액(CW49.CWMONT||'*'||CW49.CWMAMT||'원' ) */
    ) {}
    @ApiModel("WdccSalesPerformDto-SearchSinglePaymentDepositsRes")
    public record SearchSinglePaymentDepositsRes(
        String cntrNo, /* 계약번호 */
        String cntrSn, /* 계약일련번호 */
        String rveGubun, /* 입금구분 */
        String rveDt, /* 수납일자 */
        String perfDt, /* 실적일자 */
        String dpDvCd, /* 입금구분 */
        String rveDvCd, /* 수납구분 */
        String dpTpCd, /* 입금유형 */
        Long rveAmt /* 금액 */
    ) {}
}
