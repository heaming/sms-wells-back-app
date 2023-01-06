package com.kyowon.sms.wells.web.service.stock.dvo;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * <pre>
 * W-SV-U-0117M01 출고요청 관리
 * </pre>
 *
 * @author gs.piit130 김혜원
 * @since 2022.11.25
 */
@Setter
@Getter
public class WsnaOutOfStorageAskMngtDvo {

    // String strOjWareNo; /* 출고요청창고 = 입고대상창고번호 */
    String ostrAkNo; /* 출고요청번호 */
    String ostrAkTpCd; /* 출고요청유형코드 */
    String strHopDt; /* 입고희망일자 */
    String rectOstrDt; /* 최근출고일자 */
    // String wareDvCd; /* 출고요청 접수창고 */
    // String wareLocaraCd; /* 창고지역코드 */
    String wareNm; /* 창고명 */

}
