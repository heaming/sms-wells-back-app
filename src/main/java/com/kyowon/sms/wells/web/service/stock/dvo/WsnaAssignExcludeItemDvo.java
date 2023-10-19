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
    // 배정제외구분코드
    private String asnExcdDvCd;
    // 입고창고번호
    private String strWareNo;
    // 품목상품코드
    private String itmPdCd;
    // 품목종류코드
    private String itmKndCd;
}
