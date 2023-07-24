package com.kyowon.sms.wells.web.withdrawal.idvrve.dto;

import javax.validation.constraints.NotBlank;

import com.sds.sflex.common.utils.DbEncUtil;
import com.sds.sflex.common.utils.StringUtil;

import io.swagger.annotations.ApiModel;

public class WwdbRefundCurrentStatusDto {

    @ApiModel(value = "WwdbRefundCurrentStatusDto-SearchRefundHistoryReq")
    public record SearchRefundHistoryReq(
        @NotBlank
        String rveDtStart, // 환불일자 시작일
        @NotBlank
        String rveDtFinish, // 환불일자 종료일
        @NotBlank
        String perfDtStart, // 실적일자 시작일
        @NotBlank
        String perfDtFinish, // 실적일자 종료일
        // 일괄생성구분 은 설계자가 테이블 컬럼 매핑하지 못함. 알 수 없음이라고 작성되어 있음.
        String rfndDsbDvCd, // 귀속환불구분
        String sellTpCd, // 판매유형
        String sellTpDtlCd, // 판매유형상세
        // String rveDvCd, // 대손구분
        String dpMesCd // 포인트구분
    ) {}

    @ApiModel(value = "WwdbRefundCurrentStatusDto-SearchRefundHistoryRes")
    public record SearchRefundHistoryRes(
        String cntrNo, // 계약번호
        String cntrSn, // 계약일련번호
        String cstKnm, // 고객명
        String sellTpCd, // 판매유형
        String dpAmt, // 입금총액
        String rveAmt, // 지급금액
        String rfndDsbAmt, // 환불금액
        String rfndDsbPspInt, // 지연이자
        String rfndDdtnAmt, // 카드공제
        String cshRfndFnitCd, // 0
        String cshFnitNm, // 은행사
        String cardRfndFnitCd, // 0
        String cardFnitNm, // 카드사
        String cshRfndAcnoEncr, // 계좌번호
        String cardRfndCrcdnoEncr, // 카드번호
        String cshRfndAcownNm, // 예금주현금
        String cardRfndCrdcdAprno, // 예금주카드
        String sellTpDtlCd, // 판매유형.알수없음으로작성되어있음.
        String rveDvCd, // 입금유형
        String cstNo, // 전금고객번호
        String tmp2 // 전금고객명.작성되어있지않음.전금고객번호가잘못잘성되어있는것으로판단되어고객명으로사용하지않음.

    ) {
        public SearchRefundHistoryRes {
            if (!StringUtil.isEmpty(cshRfndAcnoEncr)) {
                cshRfndAcnoEncr = DbEncUtil.dec(cshRfndAcnoEncr); // 계좌번호 복호화
            }
            if (!StringUtil.isEmpty(cardRfndCrcdnoEncr)) {
                cardRfndCrcdnoEncr = DbEncUtil.dec(cardRfndCrcdnoEncr); // 카드번호 복호화
            }
        }
    }

    @ApiModel(value = "WwdbRefundCurrentStatusDto-SearchCardRefundHistoryReq")
    public record SearchCardRefundHistoryReq(
        @NotBlank
        String rveDtStart, // 환불일자 시작일
        @NotBlank
        String rveDtFinish, // 환불일자 종료일
        @NotBlank
        String perfDtStart, // 실적일자 시작일
        @NotBlank
        String perfDtFinish, // 실적일자 종료일
        // 일괄생성구분 은 설계자가 테이블 컬럼 매핑하지 못함. 알 수 없음이라고 작성되어 있음.
        String rfndDsbDvCd, // 귀속환불구분
        String sellTpCd, // 판매유형
        String sellTpDtlCd, // 판매유형상세 은 설계자가 테이블 컬럼 매핑하지 못함. 알 수 없음이라고 작성되어 있음.
        String rveDvCd, // 대손구분
        String dpMesCd // 포인트구분
    ) {}

    @ApiModel(value = "WwdbRefundCurrentStatusDto-SearchCardRefundHistoryRes")
    public record SearchCardRefundHistoryRes(
        String refundDivision, // 환불구분
        String rfndDsbAmt, // 현금
        String rfndDdtnAmt, // 카드공제
        String bcRfndDsbAmt, // 비씨
        String kbRfndDsbAmt, // 국민
        String hnRfndDsbAmt, // 외환 대신 하나
        String shRfndDsbAmt, // 신한
        String ssRfndDsbAmt, // 삼성
        String hdRfndDsbAmt, // 현대
        String ltRfndDsbAmt, // 롯데
        String nhRfndDsbAmt, // 농협
        String sumRfndDsbDdtnAmt, // 환불총계
        String sumRfndDsbPspInt // 지연이자

    ) {}

    @ApiModel(value = "WwdbRefundCurrentStatusDto-SearchBalanceTransferRefundHistoryReq")
    public record SearchBalanceTransferRefundHistoryReq(
        @NotBlank
        String rveDtStart, // 환불일자 시작일
        @NotBlank
        String rveDtFinish, // 환불일자 종료일
        @NotBlank
        String perfDtStart, // 실적일자 시작일
        @NotBlank
        String perfDtFinish, // 실적일자 종료일
        // 일괄생성구분 은 설계자가 테이블 컬럼 매핑하지 못함. 알 수 없음이라고 작성되어 있음.
        String rfndDsbDvCd, // 귀속환불구분
        String sellTpCd, // 판매유형
        String sellTpDtlCd, // 판매유형상세 은 설계자가 테이블 컬럼 매핑하지 못함. 알 수 없음이라고 작성되어 있음.
        String rveDvCd, // 대손구분
        String dpMesCd // 포인트구분
    ) {}

    @ApiModel(value = "WwdbRefundCurrentStatusDto-SearchBalanceTransferRefundHistoryRes")
    public record SearchBalanceTransferRefundHistoryRes(
        String refundDivision, // 전금구분
        String rtRfndDsbAmt, // 렌탈
        String lsRfndDsbAmt, // 리스
        String elRfndDsbAmt, // 환경리스
        String mbRfndDsbAmt, // 멤버십
        String hcRfndDsbAmt, // 홈케어멤버십
        String lnRfndDsbAmt, // 장기할
        String lmRfndDsbAmt, // 할부금
        String kmRfndDsbAmt, // K머니
        String rgRfndDsbAmt, // 정기배송
        String sumRfndDsbAmt // 전금합계

    ) {}
}
