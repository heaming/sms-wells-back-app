package com.kyowon.sms.wells.web.service.stock.dvo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * K-W-SV-U-0302M01 모종 출하대차 MAP dvo
 * </pre>
 *
 * @author heymi.cho
 * @since 2023-09-07
 */

@Getter
@Setter
@AllArgsConstructor
public class WsnaSeedingTruckArrangementMapSeedDvo {

    private String sdingPkgGrpCd;
    private String sdingPkgGrpCdNm;
    private Integer sdingPkgGrpqty;

}
