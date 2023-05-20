package com.kyowon.sms.wells.web.closing.sales.dvo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <pre>
 * 전표초기화 DVO
 * </pre>
 *
 * @author WOO SEUNGMIN
 * @since 2023-05-10
 */
@Getter
@Setter
@ToString
public class WdcbBusinessAtamAdjustDvo {
    private String sellTpCd;
    private String sellTpDtlCd;
    private String sapAlrpySlpno;
    private String slBndAlrpyAmt;
    private String bktxt;
    private String orglFnlMdfcDtm;
}
