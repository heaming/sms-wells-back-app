package com.kyowon.sms.wells.web.service.interfaces.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;

/**
 * <pre>
 * W-SV-I-0019 wells홈페이지 모종배송내역 조회
 * </pre>
 *
 * @author yeonghwa.cheon
 * @since 2023.07.10
 */
public class WsniSeedingShippingDto {

    @ApiModel(value = "WsniSeedingShippingDto-SearchReq")
    public record SearchReq(
        @JsonProperty("CNTR_NO")
        String cntrNo, /* 계약번호 */
        @JsonProperty("CNTR_SN")
        String cntrSn /* 계약일련번호 */
    ) {}

    @ApiModel(value = "WsniSeedingShippingDto-SearchRes")
    public record SearchRes(
        @JsonProperty("CNTR_NO")
        String cntrNo, /* 계약번호 */
        @JsonProperty("CNTR_SN")
        String cntrSn, /* 계약일련번호 */
        @JsonProperty("PERF_CT")
        String perfCt, /* 실적건수 */
        @JsonProperty("SEL_AMT")
        String selAmt, /* 판매금액 */
        @JsonProperty("VST_DT")
        String vstDt, /* 방문일자 */
        @JsonProperty("PLS_40")
        String pls40, /* 플러스40(SYSDATE+ 40) */
        @JsonProperty("CHNG_DT")
        String chngDt, /* 교체일자 */
        @JsonProperty("SPP_PRGS_STAT_CD")
        String sppPrgsStatCd, /* 배송진행상태코드 */
        @JsonProperty("SPP_ADR")
        String sppAdr, /* 설치주소 */
        @JsonProperty("ITM_KNM")
        String itmKnm /* 품목한글명 */
    ) {}

}
