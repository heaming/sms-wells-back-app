package com.kyowon.sms.wells.web.service.stock.dvo;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * W-SV-U-0193M01 독립창고출고관리 물류 전송 dvo
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-07-26
 */

@Getter
@Setter
public class WsnaIndependenceWareOstrLgstDvo {
    private String asnOjYm;
    private BigDecimal cnt;
    private String ostrWareNo;
    private String wareDvCd;
    private String wareDtlDvCd;

}
