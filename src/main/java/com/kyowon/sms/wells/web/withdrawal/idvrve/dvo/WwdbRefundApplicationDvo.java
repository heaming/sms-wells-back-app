package com.kyowon.sms.wells.web.withdrawal.idvrve.dvo;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WwdbRefundApplicationDvo {
    private String rfndRcpNo; // 환불접수번호
    private String cntrNo; // 계약번호
    private String cntrSn;
    private String cstNo; // 고객번호
    private String exRfndRsonCn; // 예외환불 사유
    private String rfndCshAmt; // 현금 환불금액
    private String rfndCardAmt; // 카드 환불금액
    private String rfndBltfAmt; // 전금 금액
    private String rfndPsbResAmt; // 환불가능 잔액
    private String totRfndEtAmt; // 총환불가능 잔액
    private String rfndAkNo; // 환불요청번호

    /* 환불상세 */
    private String rfndCshAkAmt; //  환불요청금액 (현금)
    private String rfndCardAkAmt; //  환불요청금액(카드)
    private String crdcdFeeAmt; // 카드수수료(전액)
    private String crdcdFer; // 수수료율 ( 저장 )
    private String totRfndBltfAkAmt; // 전금요청금액
    private String rfndRsonCd; // 환불사유
    private String rfndRsonCn; // 환불내용

    private String ogTpCd; /* 조직유형코드 */
    private String prtnrNo; /* 파트너번호 */
    private String fnitCd; /* 금융기관코드 */
    private String acownNm; /* 계좌주명 */
    private String acnoEncr; /* 계좌번호암호화 */

    private String rveNo; /* 수납번호 */
    private String rveSn; /* 수납일련번호 */
    /* 전금상세 */
    private String rfndBltfAkAmt;//  전금요청금액
    private String bltfOjCntrNo;// 전금계약번호
    private String bltfOjCntrSn; // 전금일련번호
    private String bltfOjCntrDtlNo; // 전금계약상세번호
    private String bltfRfndMbDvCd; // 회원구분
    private String rfndEvidMtrFileId; // 자료

    /* 환불기본(합산데이터) */
    private String totRfndCshAkAmt;
    private String totRfndCardAkAmt;
    //    private String totRfndBltfAkAmt;
    private String totCrdcdFeeAmt;

    private String rfndAkStatCd; /* 환불요청상태코드 */

    private List<WwdbRefundApplicationDetailDvo> details; // 추가 버튼 누르면 추가로 생성되는 부분

}
