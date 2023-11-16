package com.kyowon.sms.wells.web.withdrawal.bilfnt.dvo;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * 자동이체 지정 출금 고객 삭제 DVO
 * </pre>
 *
 * @author donghyun.yoo
 * @since 2023-01-30
 */
@Getter
@Setter
public class WwdaDesignationWithdrawalDeleteDvo {
    private String bilNo; // 청구번호
    private String bilDtlSn; // 청구상세일련번호
    private String cntr; // 계약번호
    private String cntrNo; // 계약상세번호
    private String cntrSn; // 계약일련번호
    private String fntYm; // 이체년월
}
