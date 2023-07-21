package com.kyowon.sms.wells.web.service.stock.dvo;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * W-SV-U-0192M01 개인창고출고관리
 * </pre>
 *
 * @author songTaeSung
 * @since 2023.02.16
 */
@Getter
@Setter
public class WsnaIndividualWareOstrDvo {

    private String chk;
    private String wareNm;
    private String sapMatCd;
    private String itmPdCd;
    private String pdAbbrNm;

    private BigDecimal partUseQty;
    private BigDecimal under20per;
    private BigDecimal hgrCrtlStocQty;
    private BigDecimal totOutQty;

    private String mngtUnit;
    private String mngtUnitNm;
    private String matGdCd;

    // 물류 재고 관련
    private BigDecimal logisticStocQty;
    private BigDecimal logisticFilterQty;

    private BigDecimal boxUnitQty;
    private BigDecimal crtlStocQty;
    private BigDecimal useQty;
    private BigDecimal cnfmQty;
    private BigDecimal cnfmBoxQty;
    private BigDecimal aclOstrQty;
    private BigDecimal aclOstrBoxQty;

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
}
