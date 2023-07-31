package com.kyowon.sms.wells.web.service.stock.dvo;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 *  출고 집계 현황 창고 dvo
 * </pre>
 *
 * @author junggheejin
 * @since 2023.07.25
 */
@Getter
@Setter
public class WsnaOutOfStorageAgrgWareDvo {

    String wareNo;
    String wareNm;
    boolean isSumFields; // 합계 필드 노출 여부
}
