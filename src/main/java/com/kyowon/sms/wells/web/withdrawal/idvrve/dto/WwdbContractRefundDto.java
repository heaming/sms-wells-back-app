package com.kyowon.sms.wells.web.withdrawal.idvrve.dto;

import javax.validation.constraints.NotBlank;

import com.sds.sflex.common.utils.DbEncUtil;
import com.sds.sflex.common.utils.StringUtil;

import io.swagger.annotations.ApiModel;

public class WwdbContractRefundDto {

    @ApiModel(value = "WwdbContractRefundDto-SearchContractRefundReq")
    public record SearchContractRefundReq(
        @NotBlank
        String startDay, // 처리일자 시작일
        @NotBlank
        String endDay, // 처리일자 종료일
        String perfDtStartDay, // 실적일자 시작일
        String perfDtEndDay, // 실적일자 종료일
        String cntrwTpCd // 업무구분
        //        String prntDv // 확인필요. 출력구분. 센터, 신용
    ) {

    }

    @ApiModel(value = "WwdbContractRefundDto-SearchContractRefundRes")
    public record SearchContractRefundRes(
        String rveNo,
        String rveSn,
        String rfndRcpNo,
        String rfndRcpDtlSn,
        String cntrNo,
        String cntrSn,
        String cntrDtlNo, /* 계약상세번호 */
        String cstKnm, // 고객명
        String rfndRveDt, // 처리일자
        String rfndPerfDt, // 실적일자
        String cntrwTpCd, // 업무구분
        String tmp1, // 출력구분
        String sellAmt, // 판매금액
        String dsbAmt, // 지급금액. 추후 확인 필요
        String rfndDsbAmt, // 환불금액 = 환불지급금액
        String rfndDsbPspInt, // 지연이자
        String cardRfndFee, // 카드수수료
        String cshCardRfndFnitCd, // 은행/카드사
        String cshRfndAcnoEncr, // 계좌번호
        String cardRfndCrcdnoEncr, // 카드번호
        String cshCardRfndAcnoCrcdnoEncr, // 계좌/카드번호
        String cshRfndAcownNm, // 예금주
        String istmMcn, // 할부개월
        String cardRfndCrdcdAprno // 승인번호

    ) {
        public SearchContractRefundRes {
            if (!StringUtil.isEmpty(cshRfndAcnoEncr)) {
                cshCardRfndAcnoCrcdnoEncr = DbEncUtil.dec(cshRfndAcnoEncr); // 계좌번호 복호화
            }
            if (!StringUtil.isEmpty(cardRfndCrcdnoEncr)) {
                cshCardRfndAcnoCrcdnoEncr = DbEncUtil.dec(cardRfndCrcdnoEncr); // 카드번호 복호화
            }
        }
    }

    @ApiModel(value = "WwdbContractRefundDto-SearchContractRefundAggregateReq")
    public record SearchContractRefundAggregateReq(
        @NotBlank
        String startDay, // 처리일자 시작일
        @NotBlank
        String endDay, // 처리일자 종료일
        String perfDtStartDay, // 실적일자 시작일
        String perfDtEndDay, // 실적일자 종료일
        String cntrwTpCd // 업무구분
        //        String prntDv // 확인필요. 출력구분. 센터, 신용
    ) {

    }

    @ApiModel(value = "WwdbContractRefundDto-SearchContractRefundAggregateRes")
    public record SearchContractRefundAggregateRes(
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
        String blngAmt,/* 확인 필요: 회사귀속 */
        String cashCardRfndDdtnAmtSum, // 현금계(현금 환불공제금액 합계 + 카드 공제(환불공제금액 합계))
        String cardRfndDsbAmtSum, // 카드 (환불지급금액) 합계
        String tkMrntBltfSum,/* 확인 필요: 인수 전금 웰스: 홈케어: */
        String tkHcrBltfSum,
        String tkBltfResultSum,/* 확인 필요: 인수 전금 계 */
        String istmBltfSum,/* 확인 필요: 할부전금(웰스)  */
        String istmBltfResultSum,/* 확인 필요: 할부전금계 = 인수전금 합계 + 할부전금 합계  */
        String rfndDsbPspIntSum, // 지연이자 합계
        String pointSum,/* 확인 필요:K 포인트 */
        String rfndBltfSum,/* 확인 필요:전금합계 */
        String rfTotalSum/* 확인 필요:환불 총계 */
    ) {
    }

    @ApiModel(value = "WwdbContractRefundDto-SearchContractRefundSummaryRes")
    public record SearchContractRefundSummaryRes(
        String cntCstKnm,
        String totSellAmt,
        String totDsbAmt,
        String totRfndDsbAmt,
        String totRfndDsbPspInt,
        String totCardRfndFee
    ) {
    }
}
