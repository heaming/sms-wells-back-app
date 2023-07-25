package com.kyowon.sms.wells.web.closing.interfaces.dto;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WdccHomeCareBreachPromiseAmtInterfaceDto {

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 홈케어멤버십위약금조회 Result Dto
    @Builder
    @ApiModel("WdccHomeCareBreachPromiseAmtInterfaceDto-FindReq")
    public record FindReq(
        @NotBlank
        @JsonProperty("CNTR_NO")
        String cntrNo,
        @JsonProperty("CNTR_SN")
        int cntrSn
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 홈케어멤버십위약금조회 Result Dto
    @Builder
    @ApiModel("WdccHomeCareBreachPromiseAmtInterfaceDto-FindRes")
    public record FindRes(
        @JsonProperty("CNTR_NO")
        String cntrNo,
        @JsonProperty("CNTR_SN")
        int cntrSn,
        @JsonProperty("PD_NM")
        String pdNm,
        @JsonProperty("SELL_DSC_TP_CD")
        String sellDscTpCd,
        @JsonProperty("FNL_AMT")
        int fnlAmt,
        @JsonProperty("RGST_COST_DSC_AMT")
        int rgstCostDscAmt,
        @JsonProperty("STPL_RES_PTRM_N")
        int stplResPtrmN,
        @JsonProperty("RES_AMT")
        int resAmt,
        @JsonProperty("RES_AMT10")
        int resAmt10,
        @JsonProperty("DSC_RTRN_AMT")
        int dscRtrnAmt,
        @JsonProperty("BOR_AMT")
        int borAmt,
        @JsonProperty("STAT_NM")
        String statNm
    ) {}
}
