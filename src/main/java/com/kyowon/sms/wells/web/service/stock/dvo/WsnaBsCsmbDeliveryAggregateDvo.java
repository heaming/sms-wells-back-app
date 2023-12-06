package com.kyowon.sms.wells.web.service.stock.dvo;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * W-SV-U-0294M01 B/S소모품 배부집계 현황 dvo
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-12-06
 */

@Getter
@Setter
public class WsnaBsCsmbDeliveryAggregateDvo {

    // 빌딩코드
    String bldCd;
    // 빌딩명
    String bldNm;
    // SAP코드
    String sapMatCd;
    // 품목코드
    String csmbPdCd;
    // 품목명
    String pdNm;
    // 신입 배부유형
    String nwcmr;
    // 개인 배부유형
    String indv;
    // 빌딩 배부유형
    String bld;

    // 배부수량 합계
    BigDecimal ddlvQtySum;
    // 5개월 전 배부수량
    BigDecimal mm1Qty;
    // 4개월 전 배부수량
    BigDecimal mm2Qty;
    // 3개월 전 배부수량
    BigDecimal mm3Qty;
    // 2개월 전 배부수량
    BigDecimal mm4Qty;
    // 1개월 전 배부수량
    BigDecimal mm5Qty;
    // 당월 배부수량
    BigDecimal mm6Qty;

    // 방문계정 수 합계
    BigDecimal vstAccSum;
    // 비데(개인)
    BigDecimal bdtIndv;
    // 비데(법인)
    BigDecimal bdtCrp;
    // 정수기
    BigDecimal wrfr;
    // 공기청정기(개인)
    BigDecimal arcleIndv;
    // 공기청정기(법인)
    BigDecimal arcleCrp;
    // 연수기
    BigDecimal wtrSftnr;
    // 안마의자
    BigDecimal msgcr;
    // 커피머신
    BigDecimal cffMchn;
    // 건조기
    BigDecimal dryr;
    // 세탁기
    BigDecimal wash;
    // 에어드레서
    BigDecimal ardrssr;
    // 삼성청소기
    BigDecimal sscling;
}
