package com.kyowon.sms.wells.web.service.stock.dvo;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * W-SV-U-0179P01 정상출고등록 상세 조회 dvo
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-08-01
 */

@Getter
@Setter
public class WsnaNormalOutOfStorageDetailDvo {

    private String chk;
    private String ostrAkTpCd;
    private String ostrTpCd;
    private String strTpCd;
    private String ostrOjWareNo;
    private String strOjWareNo;
    private String ostrAkRgstDt;
    private String ostrAkNo;
    private Integer ostrAkSn;
    private String strHopDt;
    private String svpdMgtTyp;
    private String svpdSapCd;
    private String itmPdCd;
    private String svpdNmKor;
    private String svpdItemKnd;
    private String itemLoc;

    private String itmGdCd;
    private BigDecimal qty;
    private BigDecimal reqStckQty;
    private BigDecimal ostrAkQty;
    private BigDecimal ostrCnfmQty;
    private BigDecimal ostrCnfmQtyOrg;

    private String rmkCn;
    private String ostrCnfmCd;
    private String rectOstrDt;

    private BigDecimal ostrAggQty;
    private BigDecimal outQty;
    private BigDecimal outQtyOrg;
    private String strConfDt;

    private String strWareDvCd;
    private String strWareDtlDvCd;
    private String strWareNm;
    private String strPrtnrNo;
    private String strOgTpCd;
    private String ostrWareDvCd;
    private String ostrWareDtlDvCd;
    private String ostrWareNm;
    private String ostrPrtnrNo;
    private String ostrOgTpCd;

    private BigDecimal boxUnitQty;
    private String mngtUnitCd;
    private String mngtUnitNm;
    private String itmOstrNo;
    private Integer ostrSn;
    private BigDecimal ostrTms;
    private String itmStrNo;
    private Integer strSn;
    private BigDecimal avgOut;
}
