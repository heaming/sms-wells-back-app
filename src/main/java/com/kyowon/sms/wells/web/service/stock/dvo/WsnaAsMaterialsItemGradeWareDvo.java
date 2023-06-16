package com.kyowon.sms.wells.web.service.stock.dvo;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * W-SV-U-0115M01 AS자재 품목등급관리 창고정보 dvo
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-06-15
 */

@Getter
@Setter
public class WsnaAsMaterialsItemGradeWareDvo {

    // 창고번호
    private String wareNo;
    // 창고구분코드
    private String wareDvCd;

}
