package com.kyowon.sms.wells.web.withdrawal.idvrve.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WwdbRefundApplicationBasicDvo {
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

    private String rfndRveDt; // 수납일자
    private String rfndPerfDt; // 실적일자
    private String rfndDsbDt; // 지급일자 rfndDsbDt rfndFshDt
    private String rfndProcsCn; // 처리내용

}
