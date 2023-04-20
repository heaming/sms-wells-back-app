package com.kyowon.sms.wells.web.withdrawal.idvrve.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WwdbConsumablesRefundDvo {
    private String rfndRcpNo; // 환불신청번호
    private String rfndRcpDtlSn; // 환불신청일련번호
    private String kwGrpCoCd; // 법인구분
    private String rfndRveDt; // 수납일자
    private String rfndPerfDt; // 실적일자
    private String cntrNo; // 계약번호
    private String cntrSn; // 계약일련번호
    private String cstKnm; // 고객명
    private String tmp1; // 상세
    private String ogNm; // 부서
    private String prtnrKnm; // 파트너명
    private String rfndRcpPrtnrNo; // 번호
    private String rfndDsbDuedt; // 예정일자
    private String rfndDsbDt; // 매출일자
    private String rfndFshDt; // 환불일자
    private String tmp2; // 유형
    private String sellAmt; // 판매금액
    private String cntrAmt; // 계약금액
    private String rfndCshAmt; // 처리금액
    private String rfndCardAmt; // 처리금액
    private String rfndBltfAmt; // 처리금액
    private String rfndDsbDvCd; // 지급구분
    private String cardRfndFnitCd; // 카드번호/계좌번호
    private String cshRfndAcnoEncr; // 카드번호/계좌번호
    private String cardRfndCrdcdAprno; // 승인번호
    private String cardRfndCrdcdIstmMcn; // 할부개월
    private String rfndEvidMtrFileId; // 자료보관

    private String cstNo; // 고객번호
    private String pyAmt; // 금액
    private String rveNo; // 수납번호
    private String rfndDvCd; // 환불구분
    private String rfndDpKndCd; // 입금종류
    private String cardRfndCrcdnoEncr; // 카드번호
    private String cardRfndCrcdonrNm; // 카드주명
    private String cardRfndFee; // 카드공제
    private String cardRfndFer; // 카드공제율
    private String rfndDsbAmt; // 실지금액
    private String rfndPspDc; // 지연일수
    private String rfndDsbPspInt; // 지연이자
    private String rfndRntfExstYn; // 손료존재
    //    private String rfndDsbDvCd; // 지급유형
    private String rfndRsonCd; // 환불유형
    private String cshRfndFnitCd; // 은행구분
    //    private String cshRfndAcnoEncr; // 계좌번호
    private String cshRfndAcownNm; // 예금주명
    private String bltfRfndTpCd; // 전금유형
    private String bltfOjCntrNo; // 전금고객 전금대상계약번호
    private String bltfOjCntrSn; // 전금고객 전금대상계약일련번호

}
