package com.kyowon.sms.wells.web.service.stock.dvo;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 *  K-W-SV-U-0254M01 업무유형별 자재출고금액
 * </pre>
 *
 * @author heymi.cho
 * @since 2023.07.26
 */

@Getter
@Setter
public class WsnaOutOfStorageCostByTaskTypeDvo {
    String itmPdCd;
    String sapMatCd;
    String pdNm;
    Long istQty;
    Long istPdctUprcSum;
    Long bsQty;
    Long bsPdctUprcSum;
    Long asFreeQty;
    Long asFreePdctUprcSum;
    Long asPayQty;
    Long asPayPdctUprcSum;
}
