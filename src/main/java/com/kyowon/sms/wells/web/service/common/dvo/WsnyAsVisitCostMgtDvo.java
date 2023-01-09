package com.kyowon.sms.wells.web.service.common.dvo;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * <pre>
 * W-SV-U-0101M01 유상 AS 출장비 관리
 * </pre>
 *
 * @author gs.piit122 김동엽
 * @since 2022.11.18
 */
@Setter
@Getter
public class WsnyAsVisitCostMgtDvo {
    public WsnyAsVisitCostMgtDvo() {}

    public WsnyAsVisitCostMgtDvo(String pdCd, String izSn, String vlStrtDtm, String vlEndDtm) {
        this.pdCd = pdCd;
        this.izSn = izSn;
        this.vlStrtDtm = vlStrtDtm;
        this.vlEndDtm = vlEndDtm;
    }

    String pdCd;
    String izSn;
    String bstrCsAmt;
    String vlStrtDtm;
    String vlEndDtm;
    String rmkCn;
    String prevIzSn;
    String nextIzSn;
}
