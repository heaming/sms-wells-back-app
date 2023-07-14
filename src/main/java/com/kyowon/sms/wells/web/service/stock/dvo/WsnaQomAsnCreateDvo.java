package com.kyowon.sms.wells.web.service.stock.dvo;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * W-SV-U-0190M01 개인창고 물량배정 데이터 생성 dvo
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-07-12
 */

@Getter
@Setter
public class WsnaQomAsnCreateDvo {

    private String asnOjYm;
    private BigDecimal asnTnN;
    private String strWareNo;
    private String ostrWareNo;
    private String itmPdCd;
    private String wareDvCd;
    private String wareDtlDvCd;
    private String sppDvCd;
    private String wareMngtPrtnrNo;
    private String ogTpCd;
    private String bldCd;
    private String adrId;
    private String matGdCd;
    private BigDecimal geAsnQomCt;
    private BigDecimal crpAsnQomCt;
    private BigDecimal woAsnQomCt;
    private BigDecimal etnWtcfApyQty;
    private BigDecimal mcbyAcuOstrQty;
    private BigDecimal crtlStocQty;
    private BigDecimal thwkExpQty;
    private BigDecimal borrExpQty;
    private BigDecimal cnfmQty;
    private BigDecimal boxUnitQty;
    private BigDecimal aclOstrQty;
    private BigDecimal bfsvcFshCt;
    private int qomAsnNo;
}
