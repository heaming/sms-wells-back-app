package com.kyowon.sms.wells.web.service.stock.dvo;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * W-SV-U-0128M01 모종 출고 예정리스트 조회 dvo
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-07-03
 */

@Getter
@Setter
public class WsnaSeedReleaseScheduleDvo {

    private String cntrNo;
    private int cntrSn;
    private String svBizHclsfCd;
    private String svBizDclsfCd;
    private String sppOrdNo;
    private int sppPlanSn;

    private String dpDt;

}
