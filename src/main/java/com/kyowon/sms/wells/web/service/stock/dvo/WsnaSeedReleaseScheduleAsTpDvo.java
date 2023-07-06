package com.kyowon.sms.wells.web.service.stock.dvo;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * W-SV-U-0128M01 모종 출고 예정리스트 조회 AS 유형 dvo
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-07-05
 */

@Getter
@Setter
public class WsnaSeedReleaseScheduleAsTpDvo {

    private String acLctCd;
    private String asPhnCd;
    private String asCausCd;
    private String siteAwAtcCd;
    private String badDvCd;
}
