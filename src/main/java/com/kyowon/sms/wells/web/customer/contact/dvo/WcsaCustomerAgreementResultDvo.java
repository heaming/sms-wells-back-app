package com.kyowon.sms.wells.web.customer.contact.dvo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * 고객 약관동의 정보 결과 DVO
 */
@Getter
@Setter
@AllArgsConstructor
public class WcsaCustomerAgreementResultDvo {
    private Boolean rsStat;                 /* 결과상태 (true/false) */
    private String rsCd;                    /* 결과코드 (0000 : 정상, 9999 : 시스템 오류) */
    private String rsMsg;                   /* 결과메세지 (에러의 경우만 에러 메시지) */
}
