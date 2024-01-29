package com.kyowon.sms.wells.web.withdrawal.idvrve.dto;

import javax.validation.constraints.NotBlank;

/**
 * <pre>
 * 지로OCR발송관리 DTO
 * </pre>
 *
 * @author heungjun.lee
 * @since 2023-02-15
 */
public class WwdbGiroOcrForwardingMgtDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // 지로OCR발송관리 목록 조회 Request Dto
    public record SearchReq(
        String wkDt, // 작업일자
        String giroRglrDvCd //정기구분
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 지로OCR발송관리 목록 조회 Result Dto
    public record SearchRes(
        String wkDt, //작업일자
        String wkSn, //작업일련번호
        String giroBizDvCd, //지로업무구분코드
        String giroBizTpCd, //지로업무유형코드
        String cntrNo, //계약번호
        String cntrSn, //계약일련번호
        String cntr, //계약일련번호
        String cstFnm, // 고객성명
        String slDt, //매출일자
        String rentalTn, //현재차월
        String recapDutyPtrmN, //약정개월
        String strtGiroTn, //시작지로회차
        String endGiroTn, //종료지로회차
        String thm0Amt, //0차월금액
        String istmMcn, //할부개월수
        String istmAmt, //할부금액
        String istmDscAmt, //할부할인금액
        String pyAmt, //납입금액
        String stplNmnAmt, //약정차월금액
        String exnNmnAmt, //만료차월금액
        String ltpayYn, //후납여부
        String giroRglrDvCd //정기구분
    ) {}

    // *********************************************************
    // Request Dto
    // *********************************************************
    // 지로OCR발송관리 저장 Request Dto
    public record SaveReq(
        String rowState,
        @NotBlank
        String wkDt, // 작업일자
        String wkSn, // --작업일련번호
        String cntrNo, // 계약번호
        String cntrSn, // 계약일련번호
        String cntr,
        String cstFnm, //--고객명
        String slDt, // --매출일자
        String strtGiroTn, //--시작
        String endGiroTn, //--종료
        String thm0Amt, //--0차월
        String istmMcn, //할부개월수
        String istmAmt, //할부금액
        String istmDscAmt, //할부할인금액
        String pyAmt, //납입금액
        String stplNmnAmt, //약정차월금액
        String exnNmnAmt, //--만료차월
        String ltpayYn, //--후납여부
        String giroRglrDvCd, //--후납여부
        String sellTam, // 판매총액
        String subscAmt, // 청약금액
        String tkAmt, // 계약인수금액
        String cshBlam, // 현금잔액
        String giroFeeAmt, // 지로수수료금액
        String cntrTam, // 계약총액
        String giroBizDvCd, //지로업무구분코드
        String giroBizTpCd //지로업무유형코드
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 지로OCR발송관리 대상 조회 Result Dto
    public record SearchObjectRes(
        String cntrNo, // 계약번호
        String cntrSn, // 계약상세번호
        String cntr,
        String cstFnm, // 고객성명
        String slDt, //매출일자
        String rentalTn, //현재차월
        String recapDutyPtrmN, //약정개월
        String strtGiroTn, // 시작지로회차
        String endGiroTn, // 종료지로회차
        String istmMcn, //할부개월수
        String thm0Amt, //0차월금액
        String istmAmt, //할부금액
        String istmDscAmt, //할부할인금액
        String pyAmt, //납입금액
        String stplNmnAmt, //약정차월금액
        String exnNmnAmt, //만료차월금액
        String ltpayYn, //후납여부
        String giroBizDvCd, //지로업무구분코드
        String giroBizTpCd, //지로업무유형코드
        String sellTam, // 판매총액
        String subscAmt, // 청약금액
        String tkAmt, // 계약인수금액
        String cshBlam, // 현금잔액
        String giroFeeAmt, // 지로수수료금액
        String cntrTam // 계약총액
    ) {}

    // *********************************************************
    // Request Dto
    // *********************************************************
    // 지로OCR발송관리 삭제 Request Dto
    public record RemoveReq(
        @NotBlank
        String wkDt, // 작업일자
        @NotBlank
        String wkSn // 작업일련번호
    ) {}

    // *********************************************************
    // Request Dto
    // *********************************************************
    // 지로OCR발송관리 출력 조회 Request Dto
    public record SearchPrintReq(
        String giroOcrPblDtm, // 지로OCR발행일시
        String giroOcrPrntStatus,
        String giroRglrDvCd //정기비정기
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 지로OCR발송관리 출력 조회 Result Dto
    public record SearchPrintRes(
        String giroOcrPblDtm, // 지로OCR발행일시
        String giroOcrPblDate, //발송날짜
        String giroOcrPblTime, //발송시간
        String giroOcrPblSeqn, // 지로OCR발행순번
        String giroOcrPblOjStrtdt, // 지로OCR발행대상시작일자
        String giroOcrPblOjEnddt,
        String giroOcrPblOj, //발송 구간
        String giroOcrPblTotQty, //총수량
        String giroOcrPrntDt, //출력일자
        String giroOcrPrnt, //출력
        String giroOcrDlDt, // 지로OCR삭제일자
        String dtaDlYn // 삭제여부
    ) {}

    // *********************************************************
    // Request Dto
    // *********************************************************
    // Request Dto - 사용x
    public record SearchDateSeq(
        String giroOcrPblDtm, // 지로OCR발행일시
        int giroOcrPblSeqn // 지로OCR발행순번
    ) {}

    // *********************************************************
    // Request Dto
    // *********************************************************
    // 지로OCR발송관리 출력 등록 Request Dto
    public record SavePrintReq(
        String state,
        @NotBlank
        String wkDt, //작업일자
        @NotBlank
        String giroOcrPblOjStrtdt, // 지로OCR발행대상시작일자
        String giroRglrDvCd //정기구분
    ) {}

    // *********************************************************
    // Request Dto
    // *********************************************************
    // 지로OCR발송관리 출력 삭제 Request Dto
    public record removePrintReq(
        String giroOcrPblDtm, // 지로OCR발행일시
        String giroOcrPblSeqn // 지로OCR발행순번
    ) {}

    // *********************************************************
    // Request Dto
    // *********************************************************
    // 지로 출력 업데이트 Request Dto
    public record saveGiroPrintReq(
        String giroOcrPblDtm, // 지로OCR발행일시
        String giroOcrPblSeqn // 지로OCR발행순번
    ) {}

    // *********************************************************
    // Request Dto
    // *********************************************************
    // 지로OCR발송관리 대상 조회 Request Dto
    public record SearchCntrReq(
        String cntrNo,
        String cntrSn,
        String cntr,
        String wkDt // 작업일자
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 지로OCR발송관리 출력 등록 Result Dto
    public record SearchPrintCreateRes(
        String c1,
        String c2,
        String c3
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 지로OCR 입금유형이지로인지확인 출력 등록 Result Dto
    public record SearchGiroCntractRes(
        String cntrStlmId
    ) {}
}
