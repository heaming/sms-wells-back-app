package com.kyowon.sms.wells.web.withdrawal.bilfnt.dvo;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * 자동이체 지정 출금 고객 저장 DVO
 * </pre>
 *
 * @author donghyun.yoo
 * @since 2023-01-30
 */
@Getter
@Setter
public class WwdaAutomaticFntOjYnConfDvo {

    private String dpTpCd; // 입금유형코드
    private String fnitAprRsCd; // 금융기관승인결과코드
    private String bilNo; // 청구번호
    private String bilDtlSn; // 청구상세일련번호
    private String cstKnm; // 고객명
    private String sellTpCd; // 판매유형코드

}
