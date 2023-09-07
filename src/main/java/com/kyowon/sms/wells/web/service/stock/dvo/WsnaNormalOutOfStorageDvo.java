package com.kyowon.sms.wells.web.service.stock.dvo;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * W-SV-U-0142M01 정상출고 관리 dvo
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-08-01
 */

@Setter
@Getter
public class WsnaNormalOutOfStorageDvo {

    private String itmOstrNo;
    private Integer ostrSn;
    private String ostrAkNo;
    private Integer ostrAkSn;
    private String itmStrNo;
    private Integer strSn;
    private String ostrTpCd;
    private String strTpCd;
    private String mngtUnitCd;

    private String itmPdCd;
    private String itmGdCd;
    private BigDecimal outQty;
    private String svpdItemKnd;
    private BigDecimal boxUnitQty;

    private String ostrOjWareNo;
    private String ostrWareDvCd;
    private String ostrPrtnrNo;
    private String ostrOgTpCd;

    private String strOjWareNo;
    private String strWareDvCd;
    private String strPrtnrNo;
    private String strOgTpCd;
}
