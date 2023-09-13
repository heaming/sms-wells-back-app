package com.kyowon.sms.wells.web.service.interfaces.dto;

import javax.validation.constraints.NotBlank;

import org.apache.commons.lang.StringUtils;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WsniChkOverdueCustDto {
    @Builder
    @ApiModel(value = "WsniChkOverdueCustDto-FindReq")
    public record FindReq(
        @NotBlank
        @JsonProperty("CNTR_CST_NO") /*계약고객번호*/
        String cntrCstNo,

        @JsonProperty("DLQ_MCN") /*연체개월수*/
        int dlqMcn
    ) {
        public FindReq {
            String strDlqMcn = String.valueOf(dlqMcn);
            if (StringUtils.isEmpty(strDlqMcn) || "0".equals(strDlqMcn)) {
                dlqMcn = 3;
            }
        }

    }

    @Builder
    @ApiModel(value = "WsniChkOverdueCustDto-FindRes")
    public record FindRes(
        @JsonProperty("RSLT_CD")
        String rsltCd, /*결과코드*/
        @JsonProperty("RSLT_MSG")
        String rsltMsg, /*결과메세지*/
        @JsonProperty("EOT_DLQ_AMT")
        String eotDlqAmt, /*총연체금액*/
        @JsonProperty("OVERDUE_AMOU_ORD")
        String overdueAmouOrd /* 주문번호별연체금액  */

    ) {}
}
