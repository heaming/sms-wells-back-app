package com.kyowon.sms.wells.web.service.stock.dvo;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * W-SV-U-0296M01 BS자재 발주수량 산출 dvo
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-06-29
 */

@Getter
@Setter
public class WsnaItemOrderQuantityDvo {

    // 제품군
    private String commGbNm;
    // SAP코드
    private String sapCd;
    // 품목코드
    private String pdCd;
    // 품목명
    private String pdNm;
    // 영업조직
    private BigDecimal bznsOgQty;
    // 영업개인
    private BigDecimal bznsIndvQty;
    // 서비스센터
    private BigDecimal svCnrQty;
    // 물류센터
    private BigDecimal logisticCnrQty;
    // 당월물량배정
    private BigDecimal thmQomAsnQty;
    // 물류계
    private BigDecimal logisticSum;
    // 예상소요량 + 1개월
    private BigDecimal etNedQty1;
    // 예상소요량 + 2개월
    private BigDecimal etNedQty2;
    // 예상소요량 + 3개월
    private BigDecimal etNedQty3;
    // 예상소요량 + 4개월
    private BigDecimal etNedQty4;
    // 총 발주량
    private BigDecimal totGoQty;
    // MOQ
    private BigDecimal moq;
    // 리드타임
    private BigDecimal leadTime;
    // 예상소요량 + 5개월
    private BigDecimal etNedQty5;

}
