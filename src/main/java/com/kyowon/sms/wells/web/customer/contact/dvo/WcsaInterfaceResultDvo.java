package com.kyowon.sms.wells.web.customer.contact.dvo;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * 인터페이스 리턴 결과 Dvo - 고객관리
 * </pre>
 *
 * @author jeongeon.kim
 * @since 2023-02-01
 */
@Getter
@Setter
public class WcsaInterfaceResultDvo {
    private String cstNo; /* 고객번호 */
    private String rsCd; /* 결과코드 */
    private String rsMsg; /* 결과메시지 */
}
