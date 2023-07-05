package com.kyowon.sms.wells.web.service.stock.dvo;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * W-SV-U-0128M01 모종 출고 예정리스트 확정 dvo
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-07-04
 */

@Getter
@Setter
public class WsnaSeedReleaseScheduleCnfmDvo {

    private String cntrNo;
    private int cntrSn;
    private String svBizHclsfCd;
    private String svBizDclsfCd;
    private String sppOrdNo;
    private int sppPlanSn;
    private String cstNm;
    private String sdingMcnrCntrNo;
    private String sdingMcnrPdCd;
    private String sdingPkgPdCd;

    private String sdingPdCd1;
    private BigDecimal sdingQty1;
    private String sdingSowDt1;
    private String sdingPdCd2;
    private BigDecimal sdingQty2;
    private String sdingSowDt2;
    private String sdingPdCd3;
    private BigDecimal sdingQty3;
    private String sdingSowDt3;
    private String sdingPdCd4;
    private BigDecimal sdingQty4;
    private String sdingSowDt4;
    private String sdingPdCd5;
    private BigDecimal sdingQty5;
    private String sdingSowDt5;

    private String rcpDt;
    private String vstDt;
    private String sppDuedt;
    private String sppCnfmdt;
    private String mngrDvCd;
    private String ogTpCd;
    private String ichrPrtnrNo;
    private String cntrAdrpcId;
    private String refriDvCd;
    private BigDecimal recapCsAmt;
    private String dpEpttNm;
    private String dpDt;
    private String sppDvCd;
    private String dtaDlYn;
}
