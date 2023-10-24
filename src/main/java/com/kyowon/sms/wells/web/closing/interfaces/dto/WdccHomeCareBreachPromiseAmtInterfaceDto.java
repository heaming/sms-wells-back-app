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
        String cntrNo, /*계약번호*/
        @JsonProperty("CNTR_SN")
        int cntrSn /*계약일련번호*/
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 홈케어멤버십위약금조회 Result Dto
    @Builder
    @ApiModel("WdccHomeCareBreachPromiseAmtInterfaceDto-FindRes")
    public record FindRes(
        @JsonProperty("CNTR_NO")
        String cntrNo, /*계약번호*/
        @JsonProperty("CNTR_SN")
        int cntrSn, /*계약일련번호*/
        @JsonProperty("PD_NM")
        String pdNm, /*상품명*/
        @JsonProperty("SELL_DSC_TP_CD")
        String sellDscTpCd, /*판매할인유형코드*/
        @JsonProperty("FNL_AMT")
        int fnlAmt, /*최종금액*/
        @JsonProperty("RGST_COST_DSC_AMT")
        int rgstCostDscAmt, /*등록비할인금액*/
        @JsonProperty("STPL_RES_PTRM_N")
        int stplResPtrmN, /*약정잔여기간수*/
        @JsonProperty("RES_AMT")
        int resAmt, /*잔여금액*/
        @JsonProperty("RES_AMT10")
        int resAmt10, /*잔여금액10%*/
        @JsonProperty("DSC_RTRN_AMT")
        int dscRtrnAmt, /*할인반환금액*/
        @JsonProperty("BOR_AMT")
        int borAmt, /*위약금액*/
        @JsonProperty("STAT_NM")
        String statNm /*상태명*/
    ) {}
}
