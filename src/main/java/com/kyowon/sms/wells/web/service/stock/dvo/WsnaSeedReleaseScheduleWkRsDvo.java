package com.kyowon.sms.wells.web.service.stock.dvo;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * W-SV-U-0128M01 모종 출고 예정리스트 작업 결과 dvo
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-07-05
 */

@Getter
@Setter
public class WsnaSeedReleaseScheduleWkRsDvo {

    private String cstSvAsnNo;
    private String cntrNo;
    private int cntrSn;
    private String pdctPdCd;
    private String svBizHclsfCd;
    private String svBizDclsfCd;
    private String refriDvCd;
    private String acLctCd;
    private String asPhnCd;
    private String asCausCd;
    private String badDvCd;
    private String svProcsCn;
}
