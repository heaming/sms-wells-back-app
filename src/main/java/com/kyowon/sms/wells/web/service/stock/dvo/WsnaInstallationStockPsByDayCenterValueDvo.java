package com.kyowon.sms.wells.web.service.stock.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WsnaInstallationStockPsByDayCenterValueDvo {
    private String ogCd;
    private String ogNm;
    private String pdCd;
    private String sapMatCd;
    private String pdNm;
    private Integer sumQtyCenter;
    private Integer sumQtyEng;
    private Integer sumQtyTot;
    private String asnDate;
    private String asnDow;
    private Integer asnQty;
    private Integer aggAsnCnt;
    private Integer dateQty;
    private Integer rn;
}
