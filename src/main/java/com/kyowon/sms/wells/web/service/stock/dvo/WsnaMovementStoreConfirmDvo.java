package com.kyowon.sms.wells.web.service.stock.dvo;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * W-SV-U-0169P01 이관입고 등록
 * </pre>
 *
 * @author inho.Choi
 * @since 2023.04.14
 */
@Getter
@Setter
public class WsnaMovementStoreConfirmDvo {
    String itmStrNo;
    int strSn;
    String strWareNo;
    String itmGdCd;
    String itmPdCd;
    int strQty;
}
