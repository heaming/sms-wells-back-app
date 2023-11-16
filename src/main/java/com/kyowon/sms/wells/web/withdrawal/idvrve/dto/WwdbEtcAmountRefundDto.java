package com.kyowon.sms.wells.web.withdrawal.idvrve.dto;

import javax.validation.constraints.NotBlank;

import com.sds.sflex.common.utils.DbEncUtil;
import com.sds.sflex.common.utils.StringUtil;

import io.swagger.annotations.ApiModel;

/**
 * <pre>
 * 기타 선수금 환불 목록 DTO
 * </pre>
 *
 * @author Sonkiseok
 * @since 2023-04-07
 */
public class WwdbEtcAmountRefundDto {

    // *********************************************************
    // Request Dto
    // *********************************************************
    // 기타 선수금 환불 목록 조회 Request Dto
    @ApiModel(value = "WwdbEtcAmountRefundDto-SearchEtcAmountRefundReq")
    public record SearchEtcAmountRefundReq(
        @NotBlank
        String startDay, // 처리일자 시작일
        @NotBlank
        String endDay, // 처리일자 종료일

        String perfDtStartDay, // 실적일자 시작일
        String perfDtEndDay, // 실적일자 종료일
        String rfndDv, // 환불구분
        String icptSellYn // 불완전판매여부
    ) { }

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 기타 선수금 환불 목록 조회 Result Dto
    @ApiModel(value = "WwdbEtcAmountRefundDto-SearchEtcAmountRefundRes")
    public record SearchEtcAmountRefundRes(
        String rfndRcpNo, // 환불접수번호
        String rfndRcpDtlSn, // 환불접수상세일련번호
        String cntrNo, /* 계약번호 */
        String cntrSn, /* 계약일련번호 */
        String cntrDtlNo, /* 계약상세번호 */
        String cstKnm, /* 고객명 -한글 */
//        String cstEnm, /* 고객명 -영문*/
        String rfndRveDt, /* 처리일자 */
        String rfndPerfDt, /* 실적일자 */
        String bizDv, /* 업무구분 */
        String dpDv, /* 입금종류 */
        String rfndDv, /* 환불구분 */
        String icptSellDv, /* 불완전판매구분 */
        String sellAmt, /* 판매금액 */
        String dsbAmt, /* 지급금액 */
        String rfndDsbAmt, /* 환불금액 */
        String rfndDsbPspInt, /* 지연이자 */
        String cardRfndFee, /* 카드수수료 */
        String cshCardRfndFnitCd, /*  은행/카드사 */
        String cardRfndCrcdnoEncr, /* 카드번호 */
        String cshRfndAcnoEncr, /* 계좌번호 */
        String cshCardRfndAcnoCrcdnoEncr, /* 계좌/카드번호 */
        String cshRfndAcownNm, /* 예금주 */
        String istmMcn, /* 할부개월 */
        String cardRfndCrdcdAprno /* 승인번호 */

    ) {
        public SearchEtcAmountRefundRes {
            if (!StringUtil.isEmpty(cshRfndAcnoEncr)) {
                cshCardRfndAcnoCrcdnoEncr = DbEncUtil.dec(cshRfndAcnoEncr); // 계좌번호 복호화
            }
            if (!StringUtil.isEmpty(cardRfndCrcdnoEncr)) {
                cshCardRfndAcnoCrcdnoEncr = DbEncUtil.dec(cardRfndCrcdnoEncr); // 카드번호 복호화
            }
        }
    }

    // *********************************************************
    // Request Dto
    // *********************************************************
    // 기타 선수금 환불 집계 Request Dto
    @ApiModel(value = "WwdbEtcAmountRefundDto-SearchEtcAmountRefundAggregateReq")
    public record SearchEtcAmountRefundAggregateReq(
        @NotBlank
        String startDay, // 처리일자 시작일
        @NotBlank
        String endDay, // 처리일자 종료일

        String perfDtStartDay, // 실적일자 시작일

        String perfDtEndDay, // 실적일자 종료일
        String rfndDv, // 환불구분
        String icptSellYn // 불완전판매여부
    ) { }

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 기타 선수금 환불 집계 Result Dto
    @ApiModel(value = "WwdbEtcAmountRefundDto-SearchEtcAmountRefundAggregateRes")
    public record SearchEtcAmountRefundAggregateRes(
        String cashRfndDsbAmtSum, // 현금 환불지급금액 합
        String bcCardRfndDsbAmtSum, // BC 환불지급금액 합계
        String kbCardRfndDsbAmtSum, // KB 환불지급금액 합계
        String ssCardRfndDsbAmtSum, // 삼성 환불지급금액 합계
        String hnCardRfndDsbAmtSum, // 하나 환불지급금액 합계
        String shCardRfndDsbAmtSum, // 신한 환불지급금액 합계
        String ltCardRfndDsbAmtSum, // 롯데 환불지급금액 합계
        String hdCardRfndDsbAmtSum, // 현대 환불지급금액 합계
        String nhCardRfndDsbAmtSum, // 농협 환불지급금액 합계
        String ydCardRfndDsbAmtSum, // 여민동락 환불지급금액 합계
        String cardRfndDdtnAmtSum, // 카드 공제(환불공제금액 합계)
        String cashCardRfndDdtnAmtSum, // 현금계(현금 환불공제금액 합계 + 카드 공제(환불공제금액 합계))
        String cardRfndDsbAmtSum, // 카드 (환불지급금액) 합계
        /* 확인필요 : 웰스 인수 전금 */
        String welsIstmBltf,/* 확인필요 : 웰스 할부 전금 */
        String welsRentalBltf,/* 확인필요 : 웰스 렌탈 전금 */
        String welsMmbrBltf,/* 확인필요 : 웰스 멤버 전금 */

        String rfndDsbPspIntSum, // 지연이자 합계
        // k머니 합계
        String rfndBltfSum, /* 확인 필요:전금합계 */
        String rfTotalSum/* 확인 필요:환불 총계 */
    ) { }

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 기타 선수금 환불 목록 조회 총 합계 Result Dto
    @ApiModel(value = "WwdbEtcAmountRefundDto-SearchEtcAmountRefundSummaryRes")
    public record SearchEtcAmountRefundSummaryRes(
        String cntCstKnm, /* 총 건수*/
        String totSellAmt, /* 판매금액합계 */
        String totDsbAmt, /* 지급금액합계 */
        String totRfndDsbAmt, /* 환불금액합계 */
        String totRfndDsbPspInt, /* 지연이자합계 */
        String totCardRfndFee /* 카드수수료합계 */
    ) { }
}
