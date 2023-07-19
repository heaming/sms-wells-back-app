package com.kyowon.sms.wells.web.closing.standard.dvo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <pre>
 * 일마감통제확인 서비스 DVO
 * </pre>
 *
 * @author WOO SEUNGMIN
 * @since 2023-07-17
 */
@Getter
@Setter
@ToString
public class WdcyBusinessCloseHourConfirmDvo {
    private String procsPsbYn; /* 처리가능 여부 */
    private String perfDt; /* 실적 일자 */
}
