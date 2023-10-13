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

    // AS위치코드
    private String acLctCd;
    // AS현상코드
    private String asPhnCd;
    // AS원인코드
    private String asCausCd;
    // 현장수당항목코드
    private String siteAwAtcCd;
    // 불량구분코드
    private String badDvCd;
}
