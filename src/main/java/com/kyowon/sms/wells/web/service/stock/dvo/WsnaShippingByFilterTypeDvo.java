package com.kyowon.sms.wells.web.service.stock.dvo;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * W-SV-U-01261M01 필터 종류별 출고내역 dvo
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-08-09
 */

@Getter
@Setter
public class WsnaShippingByFilterTypeDvo {

    private String cstSvAsnNo;
    private int wkOstrSn;
    private String stkrPrntYn;
    private String rmkCn;
    private String ostrConfDt;

}
