package com.kyowon.sms.wells.web.service.interfaces.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;

public class WsniSeasonFiltersInterfaceDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    @ApiModel(value = "WsniSeasonFiltersInterfaceDto-SearchReq")
    public record SearchReq(
        @JsonProperty(value = "CNTR_NO")
        String cntrNo,
        @JsonProperty(value = "CNTR_SN")
        int cntrSn
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    @ApiModel(value = "WsniSeasonFiltersInterfaceDto-SearchRes")
    public record SearchRes(
        @JsonProperty(value = "PDCT_PD_CD")
        String pdctPdCd,
        @JsonProperty(value = "FILT_CMU_NM")
        String filtCmuNm,
        @JsonProperty(value = "FILT_CMU_EPL")
        String filtCmuEpl,
        @JsonProperty(value = "SVPD_NM_KOR")
        String svpdNmKor
    ) {}
}
