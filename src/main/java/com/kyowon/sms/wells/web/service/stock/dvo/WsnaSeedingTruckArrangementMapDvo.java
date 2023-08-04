package com.kyowon.sms.wells.web.service.stock.dvo;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 *  K-W-SV-U-0302M01 모종 출하대차MAP
 * </pre>
 *
 * @author heymi.cho
 * @since 2023.08.03
 */

@Getter
@Setter
public class WsnaSeedingTruckArrangementMapDvo {
    String ggLctCd;
    String ggLctNm;
    String sdingPkgGrpCd;
    String sdingPkgGrpNm;
    Integer sdingPkgGrpqty;
}
