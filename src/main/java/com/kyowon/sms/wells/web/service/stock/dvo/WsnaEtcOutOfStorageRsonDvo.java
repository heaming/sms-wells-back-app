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
    String sapMatCd; /*SAP자재코드 */
    String itmPdCd; /* 품목상품코드 */
    String itmNm; /* 품목명 */
    String itmGdCd; /* 품목등급코드 */
    String ostrDt; /* 출고일자 */
    String ostrQty; /* 출고수량 */
    String whlsUprcAmt; /* */
    String totalAmt; /* 총금액*/
    String deptNm2; /* 부서명*/
    String ostrRsonCd; /* 출고사유코드명*/
    String wareNm; /* 창고명 */
    String rmkCn; /* 비고*/
}
