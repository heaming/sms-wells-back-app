package com.kyowon.sms.wells.web.service.stock.dvo;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 *  W-SV-U-0172P01 출고요청 등록
 * </pre>
 *
 * @author songTaeSung
 * @since 2022.01.30
 */
@Getter
@Setter
public class WsnaOutOfStorageItemizationDvo {
    String ostrTpCd;
    String ostrWareNo;
    String strWareNo;
    String itmOstrNo;
    String ostrSn;
    String itmStrNo;
    String ostrDt;
    String wareNm;
    String wareAdrId;
    String newAdrZip;
    String txtNote;
}
