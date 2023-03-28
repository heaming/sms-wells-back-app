package com.kyowon.sms.wells.web.withdrawal.idvrve.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;

public class WwdbBillDepositMgtDto {
    public record SearchReq(
        String rcpStartDt,
        String rcpEndDt,
        String cntrNo,
        String billExprDt
    ) {

    }
    public record SearchRes(
        String cntrNo, /* 계약번호 */
        String mconBzsNm, /*거래처명*/
        String billRmkCn, /*어음구분*/
        String billDpAmt, /*입금액*/
        String billRcpDt, /* 접수일자 */
        String bzrno, /* 사업자등록번호 */
        String billBndNo, /* 채권번호 */
        String billBndAmt, /* 채권금액 */
        String billExprDt, /* 만기일자 */
        String cntrCount, /* 계약수 */
        String itgDpNo /* 통합입금번호 */
        /* 입금전표번호 */
        /* 대체전표번호 */
    ) {

    }

    public record SearchDetailRes(
        String dlpnrNm, /* 거래처명 */
        String bzrno, /* 사업자등록번호 */
        String cntr, /* 계약상세번호 */
        String cntrNo, /* 계약상세번호 */
        String cntrSn, /* 일련번호 */
        String sellAmt /* 금액 */
    ) {

    }
    public record SearchDetailReq(
        String bzrno, /* 사업자등록번호 */
        String cntrNo,
        String cntrSn
    ) {

    }

    public record SaveReq(
        SaveMainReq saveMainReq,
        List<SaveMainDtlReq> SaveMainDtlReq
    ) {

    }

    public record SaveMainReq(
        String state,
        @NotBlank
        String itgDpNo, /* 통합입금번호 */
        String fnitCd, /* 금융기관코드 */
        String billBndNo, /* 어음채권번호 */
        String billBndAmt, /* 어음채권금액 */
        String billRcpDt, /* 어음접수일자 */
        String billExprDt, /* 어음만기일자 */
        String billLnPsbDt, /* 어음대출가능일자 */
        String billBndHndovDt, /* 어음채권양도일자 */
        String billLnPsbAmt, /* 어음대출가능금액 */
        String billDlpnrNm, /* 어음거래처명 */
        String sellBzsBzrno, /* 판매업체사업자등록번호 */
        String pblBzsBzrno, /* 발행업체사업자등록번호 */
        String billRmkCn, /* 어음비고내용 */
        String canYn, /* 취소여부 */
        String canDtm, /* 취소일시 */
        String rveAkNo, /* 수납요청번호 */
        String dtaDlYn, /* 삭제여부 */
        String sellAmt, /* 입금금액 */
        String billDpAmt /* 입금금액 */
    ) {

    }
    public record SaveMainDtlReq(
        String rowState,
        @NotBlank
        String itgDpNo, /* 통합입금번호 */
        String cntrNo,
        String cntrSn,
        String billDpAmt /* 금액 */
    ) {

    }

    public record SearchElectronicReq(
        String itgDpNo, /* 통합입금번호 */
        String cntrNo /* 계약번호 */
    ) {

    }

    public record SearchElectronicRes(
        String itgDpNo, /* 통합입금번호 */
        String rveCd, /* 수납구분 */
        String billBndNo, /* 어음채권번호 */
        String billRmkCn, /* 어음비고내용 */
        String billRcpDt, /* 어음접수일자 */
        String billExprDt, /* 어음만기일자 */
        String cntrNo, /* 계약번호 */
        String cntrSn, /* 일련번호 */
        String cntr, /* 일련번호 */
        String billDpAmt /* 입금금액 */
    ) {

    }

}
