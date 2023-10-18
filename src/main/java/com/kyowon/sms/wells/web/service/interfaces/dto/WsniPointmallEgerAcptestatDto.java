package com.kyowon.sms.wells.web.service.interfaces.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;

/**
 * <pre>
 * W-SV-I-0018 포인트몰 금융리스 안마의자,전기레인지 엔지니어 수락상태값 조회
 * </pre>
 *
 * @author yeonghwa.cheon
 * @since 2023.04.20
 */
public class WsniPointmallEgerAcptestatDto {

    @ApiModel(value = "WsniPointmallEgerInqrDto-SearchReq")
    public record SearchReq(
        @JsonProperty("CNTR_NO")
        String cntrNo, /* 계약번호 */
        @JsonProperty("CNTR_SN")
        String cntrSn /* 계약상세번호 */
    ) {}

    @ApiModel(value = "WsniPointmallEgerInqrDto-SearchRes")
    public record SearchRes(
        @JsonProperty("IN_CHNL_DV_CD")
        String inChnlDvCd, /* 입력채널구분코드 */
        @JsonProperty("AS_IST_OJ_NO")
        String asIstOjNo, /* AS설치대상번호 */
        @JsonProperty("WK_ACPTE_STAT_CD")
        String wkAcpteStatCd, /* 작업수락상태코드 */
        @JsonProperty("WK_ACPTE_DT")
        String wkAcpteDt, /* 작업수락일자 */
        @JsonProperty("WK_ACPTE_HH")
        String wkAcpteHh, /* 작업수락시간 */
        @JsonProperty("VST_CNFMDT")
        String vstCnfmdt, /* 방문확정일자 */
        @JsonProperty("VST_CNFM_HH")
        String vstCnfmHh, /* 방문확정시간 */
        @JsonProperty("WK_PRGS_STAT_CD")
        String wkPrgsStatCd, /* 작업진행상태코드 */
        @JsonProperty("RTNGD_YN")
        String rtngdYn, /* 반품여부 */
        @JsonProperty("CNTR_CNFM_Y")
        String cntrCnfmY, /* 주문확정년 */
        @JsonProperty("SAMSUNG_YN_CD")
        String samsungYnCd /* 삼성제품여부코드 */
    ) {}
}
