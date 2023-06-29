package com.kyowon.sms.wells.web.service.stock.dvo;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * W-SV-U-0143M01 기타출고 등록
 * </pre>
 *
 * @author songTaeSung
 * @since 2023-02-06
 */
@Getter
@Setter
public class WsnaEtcOutOfStorageDvo {
    String itmOstrNo;
    String ostrTpCd;
    String ostrWareNo;
    String sapMatCd;
    String itmPdCd;
    String itmKndCd;
    String ostrSn;
    String pdAbbrNm;
    String itmGdCd;
    int onQty;
    String mngtUnitCd;
    int ostrQty;
    String ostrRsonCd;
    String rmkCn;
    String wareNm;
    String wareMngtPrtnrNo;
    String ostrDt;
    String strOjWareNo;

    String bilDept;
}
