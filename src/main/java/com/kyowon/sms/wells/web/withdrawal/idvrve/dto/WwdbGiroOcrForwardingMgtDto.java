package com.kyowon.sms.wells.web.withdrawal.idvrve.dto;

import javax.validation.constraints.NotBlank;

public class WwdbGiroOcrForwardingMgtDto {
    public record SearchReq(
        String wkDt,
        String giroRglrDvCd //정기구분
    ) {

    }

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

    public record SaveReq(
        String rowState,
        @NotBlank
        String wkDt,
        String wkSn, // --작업일련번호
        String cntrNo,
        String cntrSn,
        String cntr,
        String cstFnm, //--고객명
        String slDt, // --매출일자
        String strtGiroTn, //--시작
        String endGiroTn, //--종료
        String thm0Amt, //--0차월
        String istmMcn,
        String istmAmt,
        String istmDscAmt,
        String pyAmt,
        String stplNmnAmt,
        String exnNmnAmt, //--만료차월
        String ltpayYn, //--후납여부
        String giroRglrDvCd //--후납여부
    ) {

    }

    public record SearchObjectRes(
        String cntrNo,
        String cntrSn,
        String cntr,
        String cstFnm,
        String slDt,
        String rentalTn,
        String recapDutyPtrmN,
        String strtGiroTn,
        String endGiroTn,
        String istmMcn,
        String thm0Amt,
        String istmAmt,
        String istmDscAmt,
        String pyAmt,
        String stplNmnAmt,
        String exnNmnAmt,
        String ltpayYn,
        String giroBizDvCd,
        String giroBizTpCd
    ) {

    }

    public record RemoveReq(
        @NotBlank
        String wkDt,
        @NotBlank
        String wkSn
    ) {

    }

    public record SearchPrintReq(
        String giroOcrPblDtm,
        String giroOcrPrntStatus
    ) {

    }

    public record SearchPrintRes(
        String giroOcrPblDtm,
        String giroOcrPblDate, //발송날짜
        String giroOcrPblTime, //발송시간
        String giroOcrPblSeqn,
        String giroOcrPblOjStrtdt,
        String giroOcrPblOjEnddt,
        String giroOcrPblOj, //발송 구간
        String giroOcrPblTotQty, //총수량
        String giroOcrPrntDt, //출력일자
        String giroOcrPrnt, //출력
        String giroOcrDlDt,
        String dtaDlYn
    ) {

    }

    public record SearchDateSeq(
        String giroOcrPblDtm,
        int giroOcrPblSeqn
    ) {}

    public record SavePrintReq(
        String state,
        @NotBlank
        String wkDt, //작업일자
        @NotBlank
        String giroOcrPblOjStrtdt
    ) {}

    public record removePrintReq(
        String giroOcrPblDtm,
        String giroOcrPblSeqn
    ) {}
}
