package com.kyowon.sms.wells.web.service.stock.dvo;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 *  K-W-SV-U-0126M01 입고 미승인 현황
 * </pre>
 *
 * @author heymi.cho
 * @since 2023.06.20
 */
@Getter
@Setter
public class WsnaStoreNaprvStateDvo {
    String strWareNo;
    String wareNm;
    String itmPdCd;
    String pdNm;
    String naprvQty;
}
