package com.kyowon.sms.wells.web.service.stock.dvo;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * W-SV-U-0274M01 기타출고 사유내역
 * </pre>
 *
 * @author songTaeSung
 * @since 2023.01.13
 */

@Getter
@Setter
public class WsnaEtcOutOfStorageRsonDvo {
    String sapMatCd;
    String itmPdCd;
    String itmNm;
    String itmGdCd;
    String ostrDt;
    String ostrQty;
    String whlsUprcAmt;
    String totalAmt;
    String deptNm2;
    String ostrRsonCd;
    String wareNm;
    String rmkCn;
}
