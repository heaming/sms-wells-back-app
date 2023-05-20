package com.kyowon.sms.wells.web.contract.ordermgmt.dvo;

import java.math.BigDecimal;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WctaContractRegStep4Dvo {
    private WctaContractBasDvo bas;
    private WctaContractCstRelDvo cntrt;
    private WctaContractCstRelDvo lrnr;
    private WctaContractAdrpcBasDvo adrpc;
    private WctaContractPrtnrRelDvo prtnr;
    private WctaContractPrtnrRelDvo prtnr7;
    private List<StlmDtlDvo> stlmDtls; /* 결제상세정보 */

    @Getter
    @Setter
    public static class StlmDtlDvo {
        String pdNm;
        String stlmTpCd;
        String stlmTpNm;
        BigDecimal fnlAmt;
        BigDecimal dscAmt;
        BigDecimal c1001;
        BigDecimal c10010702;
        BigDecimal c10010802;
        BigDecimal c10010803;
        BigDecimal c2001;
        BigDecimal c20010702;
        BigDecimal c20010802;
        BigDecimal c20010803;
        BigDecimal c2003;
    }
}
