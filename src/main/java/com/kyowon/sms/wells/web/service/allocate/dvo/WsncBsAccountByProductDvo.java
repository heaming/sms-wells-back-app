package com.kyowon.sms.wells.web.service.allocate.dvo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * <pre>
 * K-W-SV-U-0230M01 상품별 BS계정 현황
 * </pre>
 *
 * @author heymi.cho
 * @since 2023.08.07
 */
@Setter
@Getter
@ToString
public class WsncBsAccountByProductDvo {
    String dgr1LevlOgCd;
    String dgr2LevlOgCd;
    String dgr3LevlOgCd;
    String dgr3LevlOgNm;
    String bldNm;
    String pdCd;
    String pdNm;
    String pdGrpCd;
    String pdGrpNm;
    Integer cnt;
}
