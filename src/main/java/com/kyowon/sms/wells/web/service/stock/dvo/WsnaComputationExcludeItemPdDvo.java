package com.kyowon.sms.wells.web.service.stock.dvo;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * W-SV-U-0296P01 산출 제외품목 등록 상품정보 dvo
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-06-23
 */

@Getter
@Setter
public class WsnaComputationExcludeItemPdDvo {

    // 상품코드
    private String pdCd;
    // 상품명
    private String pdNm;
    // 품목종류코드
    private String itmKndCd;
}
