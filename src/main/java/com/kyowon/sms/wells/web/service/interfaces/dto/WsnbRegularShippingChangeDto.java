package com.kyowon.sms.wells.web.service.interfaces.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;

import javax.validation.constraints.NotBlank;

public class WsnbRegularShippingChangeDto {

    @ApiModel(value = "WsnbRegularShippingChDto-SaveReq")
    public record SaveReq(
        @NotBlank
        @JsonProperty(value = "CNTR_NO")
        String cntrNo,
        @JsonProperty(value = "CNTR_SN")
        String cntrSn,
        @JsonProperty(value = "CSMR_SER")
        String csmrSer,
        @JsonProperty(value = "REQ_GB")
        String reqGb,
        @JsonProperty(value = "REQ_DT")
        String reqDt,
        @JsonProperty(value = "BASE_PD_CD")
        String basePdCd,
        @JsonProperty(value = "REQ_BASE_PD_CD")
        String reqBasePdCd,
        @JsonProperty(value = "PART_LIST")
        String partList,
        @JsonProperty(value = "MTR_PROCS_STAT_CD")
        String mtrProcsStatCd,
        @JsonProperty(value = "USER_ID")
        String userId
    ) {}

    @ApiModel(value = "WsnbRegularShippingChDto-SaveRes")
    public record SaveRes(
        @NotBlank
        String result,
        String msg
    ) {}

    /**
    * 배송변경접수변경이력(TB_SSSO_SPP_CH_RCCH_HIST)
    *      - 배송:Spp, 변경: Change, 접수: Receipt, 이력: Hist
    */
    @ApiModel(value = "WsnbRegularShippingChDto-SaveRegularShippingChangeHistReq")
    public record SaveRegularShippingChangeHistReq(
        String cntrNo,
        String cntrSn,
        String dataStus,
        String reqGb, // P_REQ_GB = LCGUBN
        String reqDt // P_REQ_DT = LCCHGT
    ) {}

    /**
    * 배송변경접수변경상세(TB_SSSO_SPP_CH_RCP_DTL)
    *      - 배송:Spp, 변경: Ch, 접수: Rcp, 상세: Dtl
    */
    @ApiModel(value = "WsnbRegularShippingChDto-SaveRegularShippingChangeDtlReq")
    public record SaveRegularShippingChangeDtlReq(
        @NotBlank
        String cntrNo,
        String cntrSn,
        String reqGb, // P_REQ_GB = LCGUBN
        String reqDt, // P_REQ_DT = LCCHGT
        int sppChSn

    ) {}

    /**
    * 배송변경접수기본(TB_SSSO_SPP_CH_RCP_BAS)
    *      - 배송:Spp, 변경: Ch, 접수: Rcp, 기본: Bas
    */
    @ApiModel(value = "WsnbRegularShippingChDto-SearchRegularShippingChangeBaseReq")
    public record SearchRegularShippingChangeBaseReq(
        @NotBlank
        String cntrNo,
        String cntrSn,
        String reqGb, // P_REQ_GB = LCGUBN
        String reqDt // P_REQ_DT = LCCHGT
    ) {}

    /**
    * 배송변경접수기본(TB_SSSO_SPP_CH_RCP_BAS)
    *      - 배송:Spp, 변경: Ch, 접수: Rcp, 기본: Bas
    */
    @ApiModel(value = "WsnbRegularShippingChDto-SaveRegularShippingChangeBaseReq")
    public record SaveRegularShippingChangeBaseReq(
        @NotBlank
        String cntrNo,
        String cntrSn,
        String reqGb, // P_REQ_GB = LCGUBN
        String reqDt // P_REQ_DT = LCCHGT
    ) {}
}
