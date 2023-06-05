package com.kyowon.sms.wells.web.service.interfaces.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;

import javax.validation.constraints.NotNull;

public class WsniFilterChangeExpectedInterfaceDto {
    @ApiModel(value = "WsniFilterChangeExpectedInterfaceDto-SearchReq")
    public record SearchReq(
        @JsonProperty(value = "CNTR_NO")
        @NotNull
        String cntrNo,
        @JsonProperty(value = "CNTR_SN")
        @NotNull
        Integer cntrSn
    ) {

    }
    @ApiModel(value = "WsniFilterChangeExpectedInterfaceDto-SearchRes")
    public record SearchRes(
        @JsonProperty(value = "CNTR_NO")
        String cntrNo,
        @JsonProperty(value = "CNTR_SN")
        Integer cntrSn,
        @JsonProperty(value = "CUST_NM")
        String custNm,
        @JsonProperty(value = "PART_PD_CD")
        String partPdCd,
        @JsonProperty(value = "PART_NM")
        String partNm,
        @JsonProperty(value = "LAST_USE_DT")
        String lastUseDt,
        @JsonProperty(value = "NEXT_VST_DT")
        String nextVstDt,
        @JsonProperty(value = "CHAGE_YN")
        String chageYn
    ) {

    }
}
