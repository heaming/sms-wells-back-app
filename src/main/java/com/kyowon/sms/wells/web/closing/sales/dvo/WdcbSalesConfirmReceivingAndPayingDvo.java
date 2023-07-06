package com.kyowon.sms.wells.web.closing.sales.dvo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <pre>
 * 매출확정생성 서비스 물류배송방식코드, SAP플랜트코드, SAP저장위치값 DVO
 * </pre>
 *
 * @author WOO SEUNG MIN
 * @since 2023-06-22
 */
@Getter
@Setter
@ToString
public class WdcbSalesConfirmReceivingAndPayingDvo {
    private String sppMthdTpCd;
    private String sapPlntCd;
    private String sapSaveLctCd;
    private int cnt;
}
