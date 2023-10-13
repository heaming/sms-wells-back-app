package com.kyowon.sms.wells.web.service.stock.dvo;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * W-SV-U-0192M01 개인창고출고관리 물류 전송 dvo
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-07-26
 */

@Getter
@Setter
public class WsnaIndividualWareOstrLgstDvo {
    // 배정년월
    private String asnOjYm;
    // 회차
    private BigDecimal cnt;
    // 출고창고
    private String ostrWareNo;
    // 창고구분
    private String wareDvCd;
    // 창고상세구분
    private String wareDtlDvCd;
}
