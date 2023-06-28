package com.kyowon.sms.wells.web.service.stock.dvo;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * W-SV-U-0129M01 모종 출고가능수량 관리 excel dvo
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-06-28
 */

@Getter
@Setter
public class WsnaSeedingOstrQtyExcelDvo {

    // 방문일자
    private String vstDt;
    // 모종패키지그룹코드
    private String sdingPkgGrpCd;
    // 서비스업무대분류코드
    private String svBizHclsfCd;
    // 한도수량
    private String limQty;

}
