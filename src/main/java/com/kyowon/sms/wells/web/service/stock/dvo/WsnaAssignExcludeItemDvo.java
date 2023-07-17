package com.kyowon.sms.wells.web.service.stock.dvo;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * W-SV-U-0189P01 배정제외품목 등록 dvo
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-07-14
 */

@Getter
@Setter
public class WsnaAssignExcludeItemDvo {
    private String asnExcdDvCd;
    private String strWareNo;
    private String itmPdCd;
    private String itmKndCd;
}
