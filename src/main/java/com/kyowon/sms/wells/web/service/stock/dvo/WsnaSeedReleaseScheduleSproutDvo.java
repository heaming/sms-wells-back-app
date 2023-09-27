package com.kyowon.sms.wells.web.service.stock.dvo;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * W-SV-U-0128M01 모종 출고 예정리스트 새싹재배기 dvo
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-09-20
 */

@Getter
@Setter
public class WsnaSeedReleaseScheduleSproutDvo {

    private String cstSvAsnNo;
    private String cntrNo;
    private int cntrSn;
    private String svBizMclsfCd;
    private String svBizDclsfCd;
    private int wkSn;
    private String vstDuedt;
    private String pdCd;
    private String svPdCd;
    private String mngrDvCd;
}
