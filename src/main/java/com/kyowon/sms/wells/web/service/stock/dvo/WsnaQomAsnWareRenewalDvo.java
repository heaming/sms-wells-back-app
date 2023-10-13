package com.kyowon.sms.wells.web.service.stock.dvo;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * W-SV-U-0190M01 개인창고 물량배정 창고갱신 dvo
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-07-12
 */

@Getter
@Setter
public class WsnaQomAsnWareRenewalDvo {

    // 배정년월
    private String asnOjYm;
    // 기준년월
    private String apyYm;
    // 출고창고번호
    private String ostrWareNo;

}
