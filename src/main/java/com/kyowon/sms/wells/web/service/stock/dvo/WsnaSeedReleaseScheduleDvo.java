package com.kyowon.sms.wells.web.service.stock.dvo;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * W-SV-U-0128M01 모종 출고 예정리스트 조회 dvo
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-07-03
 */

@Getter
@Setter
public class WsnaSeedReleaseScheduleDvo {

    // 계약번호
    private String cntrNo;
    // 계약일련번호
    private int cntrSn;
    // 서비스업무대분류코드
    private String svBizHclsfCd;
    // 서비스업무세분류코드
    private String svBizDclsfCd;
    // 배송주문번호
    private String sppOrdNo;
    // 배송일련번호
    private int sppPlanSn;
    // 입금일자
    private String dpDt;

}
