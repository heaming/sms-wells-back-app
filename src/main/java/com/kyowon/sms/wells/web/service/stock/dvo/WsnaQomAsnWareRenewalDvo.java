package com.kyowon.sms.wells.web.service.stock.dvo;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * W-SV-U-0190M01 개인창고 물량배정 창고갱신 dvo
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-07-12
 */

@Getter
@Setter
public class WsnaQomAsnWareRenewalDvo {

    private String asnOjYm;
    private String cntrNo;
    private int cntrSn;
    private String pdctPdCd;
    private String svBizDclsfCd;
    private int istNmnN;
    private String filtChngLvCd;
    private String puPartPdCd;
    private String qomAsnWareNo;
    private String wareDvCd;
    private String strWareNo;

}
