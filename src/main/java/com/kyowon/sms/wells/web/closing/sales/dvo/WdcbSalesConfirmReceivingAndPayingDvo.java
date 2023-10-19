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
    private String sppMthdTpCd; /*배송방식유형코드*/
    private String sapPlntCd; /* SAP플랜트코드 */
    private String sapSaveLctCd; /* sap저장위치코드*/
    private String lgstItmGdCd; /*물류품목등급코드*/
}
