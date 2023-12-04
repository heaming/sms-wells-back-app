package com.kyowon.sms.wells.web.closing.performance.dvo;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class WdccProductSalesSapMaterialsDvo {
    private String sapMatEvlCd;
    private BigDecimal slSumAmt;
    private BigDecimal slSumVat;

}
