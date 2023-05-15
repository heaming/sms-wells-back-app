package com.kyowon.sms.wells.web.withdrawal.idvrve.dvo;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WwdbRefundApplicationDvo {

    private String cntrNo; // 계약번호 
    private String cntrSn;
    private String cstNo; // 고객번호
    private String exRfndRsonCn; // 예외환불 사유
    private String rfndCshAmt; // 현금 환불금액
    private String rfndCardAmt; // 카드 환불금액
    private String rfndBltfAmt; // 전금 금액
    private String rfndPsbResAmt; // 환불가능 잔액
    private String totRfndEtAmt; // 총환불가능 잔액

    private List<WwdbRefundApplicationDetailDvo> details; // 추가 버튼 누르면 추가로 생성되는 부분

}
