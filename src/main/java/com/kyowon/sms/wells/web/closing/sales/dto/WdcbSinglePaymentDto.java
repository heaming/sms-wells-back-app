package com.kyowon.sms.wells.web.closing.sales.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;

public class WdcbSinglePaymentDto {
    // *********************************************************
    // Search Dto
    // *********************************************************
    // 매출 상세정보 Search Dto
    @ApiModel("WdcbSinglePaymentDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String cntrNo,
        @NotBlank
        String cntrSn
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 매출 상세정보 Search Result Dto
    @ApiModel("WdcbSinglePaymentDto-BaseSearchRes")
    public record BaseSearchRes(
        String sellTpCdNm, /* 판매유형 */
        String cntrDtlNo, /* 계약상세번호 */
        String cstKnm, /* 고객명 */
        String copnDvCdNm, /* 고객구분 */
        String prtnrKnm, /* 판매자정보 */
        String cntrDt, /* 계약일 */
        String slRcogDt, /* 매출일자 */
        String canDt, /* 취소일 */
        String ogInfo, /* 조직정보 */
        String pdInfo, /* 상품정보 */
        Long dscAmt, /* 할인금액 */
        String dscTp, /* @TO-DO 할인유형코드(LC30.LCETC4) */
        String alncStu, /* @TO-DO 제휴상태(LC30.LCCK03) */
        String sellEvCd, /* 행사정보(LC30.LCETC9) */
        Long sellAmt, /* 판매가격 */
        Long alncFee, /* 제휴수수료(LC30.LCFAMT) */
        Long subscAmt, /* @TO-DO 청약금액(CW50.CWSAMT 값이 0이면, LC30.LCSAMT) */
        String tkAmt, /* @TO-DO 인수금액(CW50.CWAAMT) */
        //        Long tkAmt, /* @TO-DO 인수금액(CW50.CWAAMT) */
        Long crpUcAmt, /* 법인미수금액(LC30.LCGAMT) */
        Long istmPcamAmt, /* 잔액(CW50.CWJAMT(할부원금) 값이 0이면, LC30.LCJAMT 값) */
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
        Integer mpyBsdt, /* 이체일자 */
        Integer dlqMcn, /* 연체개월수(CW50.CWDCNT) */
        Long thmOcDlqAmt, /* 연체금액(CW50.CWDAMT) */
        Long ucBlam, /* 미수금액(CW50.CWQAMT - (CW50.CWSAMT + CW50.CWAAMT + CW50.CWPAMT)) */
        String ucPrt, /* 미수출력(CW50.CWFLG2) */
        String acdbtDt, /* 대손일자(CW50.CWBDDT) */
        Long dfaAmt, /* 대손금액(CW50.CWAMT4) */
        Long dfaDpAmt, /* 대손입금액(CW5400P.CWAMT1) */
        Long dfaBlam, /* 대손잔액(대손금액 - 대손입금액) */
        String dfaRveDt, /* 대손납입일자(CW5400P.CWACTY) */
        String dfaFnsAmt, /* @TO-DO 대손완불금액(ASIS 쿼리 이상함) */
        //        Long dfaFnsAmt, /* @TO-DO 대손완불금액(ASIS 쿼리 이상함) */
        String actcsDt, /* 수임일자(CW50.CWPRTY) */
        String clctamDvCdNm, /* 집금유형 */
        String clctamPrtnrNm, /* 집금담당(CW50.CWPRTY) */
        String dsbGurTpCd, /* 지급보증유형(CW50.CWJFLG) */
        String buNotiDt, /* 부담통보일자(CW50.CWSTBY) */
        String redfDt, /* 되물림일자(CW50.CWSMLY)*/
        String adsbDt, /* 재지급일자(CW50.CWSJGY) */
        String buNotiTpCdNm, /* 통보내용(CW50.CWTFLG) */
        String vacBnkCd, /* 은행명 */
        String vacNo /* 가상계좌번호 */
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 매출 상세정보 Search Result Dto
    @ApiModel("WdcbSinglePaymentDto-SalesSearchRes")
    public record SalesSearchRes(
        String slClYm, /* 기준년월 */
        String dlqMcn, /* 연체개월 */
        Long eotDlqAmt, /* 연체금액 */
        String clctamDvCdNm, /* 집금유형 */
        String clctamPrtnrNo, /* 집금유형담당 */
        Long slDpAggAmt, /* 총납부액 */
        Long thmIntamDpAmt, /* 당월납부액 */
        Long crpUcAmt, /* 당월계약금입금액 */
        Long sellAmt, /* 판매금액 */
        String istmInfo/* 상세 */
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 매출 상세정보 Search Result Dto
    @ApiModel("WdcbSinglePaymentDto-DepositSearchRes")
    public record DepositSearchRes(
        String rveDvCd1, /* 수납구분1 */
        String rveDt, /* 수납일자 */
        String perfDt, /* 실적일자 */
        String dpDvCd, /* 입금구분 */
        String rveDvCd2, /* 수납구분 */
        String dpTpCd, /* 입금유형 */
        Long rveAmt /* 금액 */
    ) {}

}
