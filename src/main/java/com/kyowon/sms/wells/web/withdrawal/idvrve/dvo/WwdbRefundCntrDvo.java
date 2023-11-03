package com.kyowon.sms.wells.web.withdrawal.idvrve.dvo;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * 환불 요청 계약 DVO
 * </pre>
 *
 * @author kimoon.lim
 * @since 2023-10-31
 */
@Getter
@Setter
public class WwdbRefundCntrDvo {
    private String cntrNo; // 계약번호
    private String cntrSn; // 계약일련번호
    private String cstNo; // 고객번호
    private String cstKnm; // 고객 한글명
    private String cntrDtlNo; // 계약상세번호
    private String rveNo; // 수납상세.수납번호
    private String borAmt; // 위약금액
    private String dlqAddAmt; // 연체가산금액
    private String dpAmt; // 입금금액
    private String mmIstmAmt; // 월할부금액
    private String rfndPsbAmt; // 환불가능금액
    private String sellAmt; // 판매금액
    private String svAmt; // 서비스금액
    private String rfndAkNo; // 환불번호
    private String aftRfndAkNo; // 채번용 키값
}
