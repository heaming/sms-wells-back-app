package com.kyowon.sms.wells.web.service.stock.dvo;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * W-SV-U-0298P01 모종패키지 조정수량 등록 dvo
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-07-25
 */

@Setter
@Getter
public class WsnaSeedingPackageCtrQtyRegDvo {

    private String dgGgLctCd;
    private String sdingPkgCd;
    private String ostrDuedt;
    private BigDecimal excdQty;
    private BigDecimal spmtQty;

}
