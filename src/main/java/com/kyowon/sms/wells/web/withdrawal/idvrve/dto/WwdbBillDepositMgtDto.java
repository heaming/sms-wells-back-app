package com.kyowon.sms.wells.web.withdrawal.idvrve.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;
/**
 * <pre>
 * 어음입금 등록 DTO ( 메인 + 신규등록 팝업 )
 * </pre>
 *
 * @author heungjun.lee
 * @since 2023-10-24
 */
public class WwdbBillDepositMgtDto {

    // *********************************************************
    // Request Dto
    // *********************************************************
    // 어음입금 등록 Search Request Dto
    public record SearchReq(
        String rcpStartDt, /* 접수 시작일자 */
        String rcpEndDt,  /* 접수 종료일자 */
        String cntrNo,  /* 계약번호 */
        String billExprDt,  /* 만기일자 */
        String cntr  /* 일련번호 */
    ) {

    }

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 어음입금 등록 Search Result Dto
    public record SearchRes(
//        String cntrNo, /*계약번호*/
//        String cntrSn, /* 계약일련번호 */
//        String cntrDtlNo, /*계약상세번호*/
//        String cntrCstNo,
        String mconBzsNm, /*거래처명*/
        String billRmkCn, /*어음구분*/
        String billDpAmt, /*입금액*/
        String billRcpDt, /* 접수일자 */
        String bzrno, /* 사업자등록번호 */
        String billBndNo, /* 채권번호 */
        String billBndAmt, /* 채권금액 */
        String billExprDt, /* 만기일자 */
        String cntrCount, /* 계약수 */
        String itgDpNo, /* 통합입금번호 */
        String billDpSapSlpno, /* 어음입금SAP전표번호 */
        String billRplcSapSlpno,/* 입금전표번호 */
        /* 대체전표번호 */
        String sellBzsBzrno, /* 판매업체사업자등록번호 */
        String pblBzsBzrno /* 발행업체사업자등록번호 */
    ) {

    }

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 전자어음 입금대상 Search Result Dto
    public record SearchDetailRes(
        String dlpnrNm, /* 거래처명 */
        String bzrno, /* 사업자등록번호 */
        String cntr, /* 일련번호 */
        String cntrNo, /* 계약번호 */
        String cntrSn, /* 계약일련번호 */
        String sellAmt /* 금액 */
    ) {

    }

    // *********************************************************
    // Request Dto
    // *********************************************************
    // 전자어음 입금대상 상세 Search Request Dto
    public record SearchDetailReq(
        String bzrno, /* 사업자등록번호 */
        String cntrNo, /* 계약번호 */
        String cntrSn /* 계약일련번호 */
    ) {

    }

    // *********************************************************
    // Request Dto
    // *********************************************************
    // 전자어음 상세현황 Save Request Dto
    public record SaveReq(
        SaveMainReq saveMainReq,
        List<SaveMainDtlReq> SaveMainDtlReq
    ) {

    }

    // *********************************************************
    // Request Dto
    // *********************************************************
    // 어음입금 등록 팝업페이지 Save Request Dto
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

    // *********************************************************
    // Request Dto
    // *********************************************************
    // 어음입금 등록 팝업페이지 상세 Save Request Dto
    public record SaveMainDtlReq(
        String rowState,
        @NotBlank
        String itgDpNo, /* 통합입금번호 */
        String cntrNo, /* 계약번호 */
        String cntrSn,  /* 계약일련번호 */
        String billDpAmt, /* 입금액 */
        String sellBzsBzrno, /* 판매업체사업자등록번호 */
        String pblBzsBzrno /* 발행업체사업자등록번호 */
    ) {

    }

    // *********************************************************
    // Request Dto
    // *********************************************************
    // 전자어음 상세 조회 팝업페이지 Search Request Dto
    public record SearchElectronicReq(
        String itgDpNo, /* 통합입금번호 */
        String cntrNo, /* 계약번호 */
        String cntrSn, /* 계약일련번호 */
        String billBndNo  /* 어음채권번호 */
    ) {

    }

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 전자어음 상세 조회 팝업페이지 Search Result Dto
    public record SearchElectronicRes(
        String itgDpNo, /* 통합입금번호 */
        String rveCd, /* 수납구분 */
        String billBndNo, /* 어음채권번호 */
        String billRmkCn, /* 어음비고내용 */
        String billRcpDt, /* 어음접수일자 */
        String billExprDt, /* 어음만기일자 */
        String cntrNo, /* 계약번호 */
        String cntrSn, /* 계약일련번호 */
        String cntr, /* 일련번호 */
        String billDpAmt /* 입금금액 */
    ) {

    }

    // *********************************************************
    // Request Dto
    // *********************************************************
    // 지로입금관리 생성 Save Request Dto
    public record SaveIntegrationReq(
        String itgDpNo, /*통합입금번호*/
        String kwGrpCoCd, /*교원그룹번호*/
        String dpDtm, /*입금일시*/
        @NotBlank
        String fntDt, /*실적일자*/

        String rveDt, /*입금일자(수납일자)*/
        String pyAmt /*입금금액*/
    ) {

    }

    // *********************************************************
    // Request Dto
    // *********************************************************
    // 전자어음 상세 팝업페이지 Save Request Dto
    public record SaveDepositSlip(
        @NotBlank
        String itgDpNo, /*통합입금번호*/
        String cntrNo,  /* 계약번호 */
        String cntrSn,  /* 계약일련번호 */
        String billDpAmt,  /* 입금액 */
        String cntrCstNo, /* 계약고객번호 */
        String billBndNo,  /* 어음채권번호 */
        String sort,
        String resultSum

    ) {

    }

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 통합입금번호 PK 채번 Result Dto
    public record SearchItgNoRes(
        String itgDpNo /*통합입금번호*/
    ) {

    }

    // *********************************************************
    // Request Dto
    // *********************************************************
    // 계약조회 Search Request Dto
    public record SearchSlipReq(
        String zzsnum, /*전송번호*/
        String wrbtr, /*금액*/
        String itgDpNo /*통합입금번호*/

    ) {

    }

}
