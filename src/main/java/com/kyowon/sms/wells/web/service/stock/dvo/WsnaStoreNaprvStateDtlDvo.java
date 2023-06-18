package com.kyowon.sms.wells.web.service.stock.dvo;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 *  K-W-SV-U-0176P01 입고 미승인상세현황
 * </pre>
 *
 * @author heymi.cho
 * @since 2023.06.13
 */
@Getter
@Setter
public class WsnaStoreNaprvStateDtlDvo {
    String strRgstDt; // 입고등록일자
    String strWareNm; // 입고유형
    String strTpCd; // 입고창고이름
    String itmStrNo; // 품목입고번호
    Integer strSn; // 입고일련번호
    String itmOstrNo; // 품목출고번호
    Integer ostrSn; // 출고일련번호
    String ostrWareNm; // 출고창고이름
    Integer strQty; // 입고수량
    String itmPdCd; // 품목상품코드
    String itmGdCd;
    String userId;
    String strWareNo;
}
