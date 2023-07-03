package com.kyowon.sms.wells.web.closing.sales.dvo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <pre>
 * 매출확정생성 서비스 SAP자재평가클래스값(SAP_MAT_EVL_CLSS_VAL) / SAP자재코드(SAP_MAT_CD) DVO
 * </pre>
 *
 * @author WOO SEUNG MIN
 * @since 2023-06-22
 */
@Getter
@Setter
@ToString
public class WdcbSalesConfirmSapMatDvo {

    private String pdCd;
    private String sapMatEvlClssVal;
    private String sapMatCd;
}
