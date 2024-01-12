package com.kyowon.sms.wells.web.service.common.dvo;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * <pre>
 * W-SV-U-0101M01 유상 AS 출장비 관리
 * </pre>
 *
 * @author gs.piit122 김동엽
 * @since 2022.11.18
 */
@Setter
@Getter
public class WsnyAsVisitCostMgtDvo {

    String pdCd;
    Integer izSn;
    BigDecimal bstrCsAmt;
    String apyStrtdt;
    String apyEnddt;
    String rmkCn;
    String sapMatCd;
    String pdNm;
    String isLast;
}
