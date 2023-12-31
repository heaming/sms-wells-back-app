package com.kyowon.sms.wells.web.closing.sales.dvo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <pre>
 * 위약금 손료 초기화 서비스 DVO
 * </pre>
 *
 * @author WOO SEUNGMIN
 * @since 2023-06-23
 */
@Getter
@Setter
@ToString
public class WdcbLossRentFeeClearingDvo {
    private String cntrNo; /*계약번호*/
    private int cntrSn; /*계약일련번호*/
    private String reqdDt; /*철거일자*/
}
