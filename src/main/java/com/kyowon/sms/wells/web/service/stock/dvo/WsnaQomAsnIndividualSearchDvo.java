package com.kyowon.sms.wells.web.service.stock.dvo;

import java.math.BigDecimal;

import com.sds.sflex.system.config.annotation.DBDecField;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * W-SV-U-0190M01 개인창고 물량배정 조회 결과 dvo
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-07-11
 */

@Getter
@Setter
public class WsnaQomAsnIndividualSearchDvo {

    private String sapCd;
    private String itmPdCd;
    private String itmPdNm;
    private String mngtUnit;
    private String matGdCd;

    private String wareNo;
    private String prtnrNo;
    private String prtnrNm;
    private String wareNm;

    private BigDecimal centerQty;
    private BigDecimal geQty;
    private BigDecimal crpQty;
    private BigDecimal totalQty;
    private BigDecimal apyQty;
    private BigDecimal ostrQty;
    private BigDecimal bsQty;
    private BigDecimal stocQty;
    private BigDecimal thwkQty;
    private BigDecimal borrQty;
    private BigDecimal cnfmQty;
    private BigDecimal boxQty;

    private BigDecimal bsFullQty;
    private BigDecimal bsAsnQty;
    private BigDecimal stockOgQty;
    private BigDecimal stockIndvQty;
    private BigDecimal nedQty;
    private BigDecimal boxUnitQty;

    private String bldCd;
    private String bldNm;
    private String locaraTno;
    @DBDecField
    private String exnoEncr;
    private String idvTno;
    private String adrZip;
    private String rnadr;
    private String rdadr;

}
