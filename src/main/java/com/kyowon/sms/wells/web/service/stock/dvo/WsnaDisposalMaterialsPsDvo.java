package com.kyowon.sms.wells.web.service.stock.dvo;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * W-SV-U-0277M01 매각자재관리 현황 dvo
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-07-18
 */

@Getter
@Setter
public class WsnaDisposalMaterialsPsDvo {

    // 창고명
    private String wareNm;
    // 창고번호
    private String wareNo;
    // 구분
    private String gubun;
    // 구분코드
    private String gubunCd;

    // 1일 수량
    private BigDecimal d01Qty;
    // 2일 수량
    private BigDecimal d02Qty;
    // 3일 수량
    private BigDecimal d03Qty;
    // 4일 수량
    private BigDecimal d04Qty;
    // 5일 수량
    private BigDecimal d05Qty;
    // 6일 수량
    private BigDecimal d06Qty;
    // 7일 수량
    private BigDecimal d07Qty;
    // 8일 수량
    private BigDecimal d08Qty;
    // 9일 수량
    private BigDecimal d09Qty;
    // 10일 수량
    private BigDecimal d10Qty;
    // 11일 수량
    private BigDecimal d11Qty;
    // 12일 수량
    private BigDecimal d12Qty;
    // 13일 수량
    private BigDecimal d13Qty;
    // 14일 수량
    private BigDecimal d14Qty;
    // 15일 수량
    private BigDecimal d15Qty;
    // 16일 수량
    private BigDecimal d16Qty;
    // 17일 수량
    private BigDecimal d17Qty;
    // 18일 수량
    private BigDecimal d18Qty;
    // 19일 수량
    private BigDecimal d19Qty;
    // 20일 수량
    private BigDecimal d20Qty;
    // 21일 수량
    private BigDecimal d21Qty;
    // 22일 수량
    private BigDecimal d22Qty;
    // 23일 수량
    private BigDecimal d23Qty;
    // 24일 수량
    private BigDecimal d24Qty;
    // 25일 수량
    private BigDecimal d25Qty;
    // 26일 수량
    private BigDecimal d26Qty;
    // 27일 수량
    private BigDecimal d27Qty;
    // 28일 수량
    private BigDecimal d28Qty;
    // 29일 수량
    private BigDecimal d29Qty;
    // 30일 수량
    private BigDecimal d30Qty;
    // 31일 수량
    private BigDecimal d31Qty;

    // 계
    private BigDecimal totQty;
}
