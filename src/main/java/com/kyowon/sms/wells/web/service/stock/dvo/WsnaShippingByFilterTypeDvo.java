package com.kyowon.sms.wells.web.service.stock.dvo;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * W-SV-U-0261M01 필터 종류별 출고내역 dvo
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-08-09
 */

@Getter
@Setter
public class WsnaShippingByFilterTypeDvo {

    // 고객서비스배정번호
    private String cstSvAsnNo;
    // 작업출고일련번호
    private int wkOstrSn;
    // 반납여부
    private String stkrPrntYn;
    // 특이사항
    private String rmkCn;
    // 수거일자
    private String ostrConfDt;

}
