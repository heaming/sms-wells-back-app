package com.kyowon.sms.wells.web.service.stock.dvo;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * W-SV-U-0109M01 이동입고현황
 * </pre>
 *
 * @author songTaeSung
 * @since 2023.02.13
 */
@Getter
@Setter
public class WsnaMovementStoreDvo {
    String strTpCd; /*입고유형코드,*/
    String strWareNo; /*입고창고번호*/
    String strSn; /*입고일련번호*/
    String strRgstDt; /*입고등록일자*/
    String dlvgDlpnrNo; /*납품거래처번호*/
    String itmStrNo; /*품목입고번호*/
    String ostrTpCd; /*출고유형코드*/
    String ostrWareNo; /*출고창고번호*/
    String ostrDt; /*출고일자*/
    String ostrSn; /*출고일련번호*/
    String itmOstrNo; /*품목출고번호*/
    String wareNm; /*창고명*/
}
