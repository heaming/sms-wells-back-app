package com.kyowon.sms.wells.web.service.stock.dvo;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * W-SV-U-0257M01 AS자재 품목등급현황 조회조건 dvo
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-06-20
 */

@Getter
@Setter
public class WsnaAsMaterialItemGradePsDvo {

    // 기준년월
    private String baseYm;
    // 사용여부
    private String useYn;
    // 자재구분
    private String matUtlzDvCd;
    // PIVOT 창고번호 조건
    private String wareNoInStr;
    // PIVOT 창고번호 필드
    private String wareNoFields;
}
