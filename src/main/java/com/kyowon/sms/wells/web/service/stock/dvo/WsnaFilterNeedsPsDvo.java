package com.kyowon.sms.wells.web.service.stock.dvo;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * W-SV-U-0278M01 필터소요 현황(교체주기) dvo
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-08-10
 */

@Getter
@Setter
public class WsnaFilterNeedsPsDvo {

    // SAP코드
    private String sapMatCd;
    // 품목코드
    private String pdCd;
    // 품목명
    private String pdNm;

    /**
     * 전체
     */
    // 1월 소요수량
    private BigDecimal vCnt01;
    // 1월 배정수량
    private BigDecimal pCnt01;
    // 2월 소요수량
    private BigDecimal vCnt02;
    // 2월 배정수량
    private BigDecimal pCnt02;
    // 3월 소요수량
    private BigDecimal vCnt03;
    // 3월 배정수량
    private BigDecimal pCnt03;
    // 4월 소요수량
    private BigDecimal vCnt04;
    // 4월 배정수량
    private BigDecimal pCnt04;
    // 5월 소요수량
    private BigDecimal vCnt05;
    // 5월 배정수량
    private BigDecimal pCnt05;
    // 6월 소요수량
    private BigDecimal vCnt06;
    // 6월 배정수량
    private BigDecimal pCnt06;
    // 7월 소요수량
    private BigDecimal vCnt07;
    // 7월 배정수량
    private BigDecimal pCnt07;
    // 8월 소요수량
    private BigDecimal vCnt08;
    // 8월 배정수량
    private BigDecimal pCnt08;
    // 9월 소요수량
    private BigDecimal vCnt09;
    // 9월 배정수량
    private BigDecimal pCnt09;
    // 10월 소요수량
    private BigDecimal vCnt10;
    // 10월 배정수량
    private BigDecimal pCnt10;
    // 11월 소요수량
    private BigDecimal vCnt11;
    // 11월 배정수량
    private BigDecimal pCnt11;
    // 12월 소요수량
    private BigDecimal vCnt12;
    // 12월 배정수량
    private BigDecimal pCnt12;
    // 계 소요수량
    private BigDecimal vCnt99;
    // 계 배정수량
    private BigDecimal pCnt99;

    /**
     * B2B 체크
     */
    // 1월 소요수량 W, 1월 소요수량 A09
    private BigDecimal vCnt01W;
    // 1월 소요수량 F, 1월 소요수량 A12
    private BigDecimal vCnt01F;
    // 1월 배정수량 W, 1월 배정수량 A09
    private BigDecimal pCnt01W;
    // 1월 배정수량 F, 1월 배정수량 A12
    private BigDecimal pCnt01F;
    // 2월 소요수량 W, 2월 소요수량 A09
    private BigDecimal vCnt02W;
    // 2월 소요수량 F, 2월 소요수량 A12
    private BigDecimal vCnt02F;
    // 2월 배정수량 W, 2월 배정수량 A09
    private BigDecimal pCnt02W;
    // 2월 배정수량 F, 2월 배정수량 A12
    private BigDecimal pCnt02F;
    // 3월 소요수량 W, 3월 소요수량 A09
    private BigDecimal vCnt03W;
    // 3월 소요수량 F, 3월 소요수량 A12
    private BigDecimal vCnt03F;
    // 3월 배정수량 W, 3월 배정수량 A09
    private BigDecimal pCnt03W;
    // 3월 배정수량 F, 3월 배정수량 A12
    private BigDecimal pCnt03F;
    // 4월 소요수량 W, 4월 소요수량 A09
    private BigDecimal vCnt04W;
    // 4월 소요수량 F, 4월 소요수량 A12
    private BigDecimal vCnt04F;
    // 4월 배정수량 W, 4월 배정수량 A09
    private BigDecimal pCnt04W;
    // 4월 배정수량 F, 4월 배정수량 A12
    private BigDecimal pCnt04F;
    // 5월 소요수량 W, 5월 소요수량 A09
    private BigDecimal vCnt05W;
    // 5월 소요수량 F, 5월 소요수량 A12
    private BigDecimal vCnt05F;
    // 5월 배정수량 W, 5월 배정수량 A09
    private BigDecimal pCnt05W;
    // 5월 배정수량 F, 5월 배정수량 A12
    private BigDecimal pCnt05F;
    // 6월 소요수량 W, 6월 소요수량 A09
    private BigDecimal vCnt06W;
    // 6월 소요수량 F, 6월 소요수량 A12
    private BigDecimal vCnt06F;
    // 6월 배정수량 W, 6월 배정수량 A09
    private BigDecimal pCnt06W;
    // 6월 배정수량 F, 6월 배정수량 A12
    private BigDecimal pCnt06F;
    // 7월 소요수량 W, 7월 소요수량 A09
    private BigDecimal vCnt07W;
    // 7월 소요수량 F, 7월 소요수량 A12
    private BigDecimal vCnt07F;
    // 7월 배정수량 W, 7월 배정수량 A09
    private BigDecimal pCnt07W;
    // 7월 배정수량 F, 7월 배정수량 A12
    private BigDecimal pCnt07F;
    // 8월 소요수량 W, 8월 소요수량 A09
    private BigDecimal vCnt08W;
    // 8월 소요수량 F, 8월 소요수량 A12
    private BigDecimal vCnt08F;
    // 8월 배정수량 W, 8월 배정수량 A09
    private BigDecimal pCnt08W;
    // 8월 배정수량 F, 8월 배정수량 A12
    private BigDecimal pCnt08F;
    // 9월 소요수량 W, 9월 소요수량 A09
    private BigDecimal vCnt09W;
    // 9월 소요수량 F, 9월 소요수량 A12
    private BigDecimal vCnt09F;
    // 9월 배정수량 W, 9월 배정수량 A09
    private BigDecimal pCnt09W;
    // 9월 배정수량 F, 9월 배정수량 A12
    private BigDecimal pCnt09F;
    // 10월 소요수량 W, 10월 소요수량 A09
    private BigDecimal vCnt10W;
    // 10월 소요수량 F, 10월 소요수량 A12
    private BigDecimal vCnt10F;
    // 10월 배정수량 W, 10월 배정수량 A09
    private BigDecimal pCnt10W;
    // 10월 배정수량 F, 10월 배정수량 A12
    private BigDecimal pCnt10F;
    // 11월 소요수량 W, 11월 소요수량 A09
    private BigDecimal vCnt11W;
    // 11월 소요수량 F, 11월 소요수량 A12
    private BigDecimal vCnt11F;
    // 11월 배정수량 W, 11월 배정수량 A09
    private BigDecimal pCnt11W;
    // 11월 배정수량 F, 11월 배정수량 A12
    private BigDecimal pCnt11F;
    // 12월 소요수량 W, 12월 소요수량 A09
    private BigDecimal vCnt12W;
    // 12월 소요수량 F, 12월 소요수량 A12
    private BigDecimal vCnt12F;
    // 12월 배정수량 W, 12월 배정수량 A09
    private BigDecimal pCnt12W;
    // 12월 소요수량 F, 12월 배정수량 A12
    private BigDecimal pCnt12F;
    // 계 소요수량 W, 계 소요수량 A09
    private BigDecimal vCnt99W;
    // 계 소요수량 F, 계 소요수량 A12
    private BigDecimal vCnt99F;
    // 계 배정수량 W, 계 배정수량 A09
    private BigDecimal pCnt99W;
    // 계 배정수량 F, 계 배정수량 A12
    private BigDecimal pCnt99F;

}
