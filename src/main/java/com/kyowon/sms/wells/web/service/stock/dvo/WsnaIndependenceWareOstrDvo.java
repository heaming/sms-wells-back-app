package com.kyowon.sms.wells.web.service.stock.dvo;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * W-SV-U-0193M01 독립창고출고관리 dvo
 * </pre>
 *
 * @author inho.choi
 * @since 2023.03.28
 */
@Getter
@Setter
public class WsnaIndependenceWareOstrDvo {

    private String lgstTrsYn;
    private String wareNm;
    private String sapMatCd;
    private String itmPdCd;
    private String pdAbbrNm;
    private String mngtUnit;
    private String mngtUnitNm;
    private String matGdCd;

    // 물류 재고 관련
    private BigDecimal logisticStocQty;

    private BigDecimal boxUnitQty;
    private BigDecimal crtlStocQty;
    private BigDecimal useQty;
    private BigDecimal cnfmQty;
    private BigDecimal cnfmBoxQty;
    private BigDecimal mcbyAcuOstrQty;
    private BigDecimal mcbyAcuOstrBoxQty;

    private BigDecimal filterBoxQty;
    private BigDecimal outQty;
    private BigDecimal outBoxQty;

    private String itmQomAsnNo;
    private String asnOjYm;
    private String ostrWareNo;
    private String strWareNo;
    private String wareMngtPrtnrNo;
    private String ogTpCd;
    private String itmKndCd;
    private String rmkCn;
    private BigDecimal asnTnN;
    private String wareDvCd;

    // 출고요청관련
    private String ostrAkNo;
    private Integer ostrAkSn;
    private String ostrDt;
    private String ostrWareDvCd;
    private String ostrPrtnrNo;
    private String ostrPrtnrOgTpCd;
}
