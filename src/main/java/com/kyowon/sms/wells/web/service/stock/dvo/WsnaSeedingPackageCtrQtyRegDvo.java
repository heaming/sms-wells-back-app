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

    // 대표집하위치코드
    private String dgGgLctCd;
    // 모종패키지코드
    private String sdingPkgCd;
    // 출고예정일자
    private String ostrDuedt;
    // 제외수량
    private BigDecimal excdQty;
    // 추가수량
    private BigDecimal spmtQty;

}
