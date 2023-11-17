package com.kyowon.sms.wells.web.withdrawal.bilfnt.dvo;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * 묶음 등록 DTO
 * </pre>
 *
 * @author donghyun.yoo
 * @since 2023-02-01
 */
@Getter
@Setter
public class WwdaBundleWithdrawalRgstDvo {

    String cntrNo; // 계약번호
    String cntrSn; // 계약 일련번호
    String cntrPdStrtdt; // 접수시작일
    String cntrPdEnddt; // 접수종료일
    String unrgRsCd; // 접수구분
}
