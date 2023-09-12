package com.kyowon.sms.wells.web.service.interfaces.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang.StringUtils;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sds.sflex.common.utils.DbEncUtil;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WsniMyKPaymentInfoDto {
    @Builder
    @ApiModel(value = "WsniSidingServiceChangesDto-FindReq")
    public record FindReq(
        @NotBlank
        @JsonProperty("CNTR_NO")
        String cntrNo,
        @NotNull
        @JsonProperty("CNTR_SN")
        int cntrSn
    ) {}

    @Builder
    @ApiModel(value = "WsniSidingServiceChangesDto-FindRes")
    public record FindRes(
        @JsonProperty("RSLT_CD")
        String rsltCd, /*결과코드*/
        @JsonProperty("RSLT_MSG")
        String rsltMsg, /*결과메세지*/
        @JsonProperty("ASK_AMT")
        String askAmt, /*실 청구금액 - 이체일이전=전월연체금액-당월입금액+당월환불액 / 이체일 이후=당월연체금액*/
        //        @JsonProperty("DP_TP_CD")
        //        String dpTpCd, /* 입금유형코드(납부방법) 0102 0203 */
        @JsonProperty("DP_TP_NM")
        String dpTpNm, /* 입금유형명(납부방법)   */
        @JsonProperty("BANK_CARD_CO_NM")
        String bankCardCoNm, /*은행_카드사명*/
        @JsonProperty("CRCDNO_ENCR") /* 카드자동이체 */
        String crcdnoEncr,
        @JsonProperty("ACNO_ENCR") /* 계좌정보 */
        String acnoEncr,
        @JsonProperty("AFTN_OWR_KNM")
        String aftnOwrKnm, /* 계좌정보-예금／카드주명 (ACCNM) */
        @JsonProperty("MPY_BSDT") /* 납부기준일자, 이체일자 */
        String mpyBsdt

    ) {
        public FindRes {
            crcdnoEncr = StringUtils.isNotEmpty(crcdnoEncr) ? DbEncUtil.dec(crcdnoEncr) : crcdnoEncr;
            if (!StringUtils.isNotEmpty(crcdnoEncr)) {
                acnoEncr = StringUtils.isNotEmpty(acnoEncr) ? DbEncUtil.dec(acnoEncr) : acnoEncr;
                crcdnoEncr = acnoEncr;
            }

        }
    }
}
