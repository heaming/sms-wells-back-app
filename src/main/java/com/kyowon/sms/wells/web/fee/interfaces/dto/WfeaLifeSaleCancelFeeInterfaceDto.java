package com.kyowon.sms.wells.web.fee.interfaces.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Builder;

import javax.validation.constraints.NotBlank;
import java.util.List;

public class WfeaLifeSaleCancelFeeInterfaceDto {


    @Builder
    public record IfRequest(
        @NotBlank
        @JsonProperty("BASE_YM")
        String baseYm,
        @NotBlank
        @JsonProperty("ET_CNFM_DV_CD")
        String etCnfmDvCd,
        @NotBlank
        @JsonProperty("LIF_CNTR_NO")
        String lifCntrNo,
        @NotBlank
        @JsonProperty("LIF_CNTR_OC_TN")
        String lifCntrOcTn,
        @JsonProperty("LIF_CNTR_STAT_CD")
        String lifCntrStatCd,
        @JsonProperty("SELL_DV_CD")
        String sellDvCd,
        @JsonProperty("PRTNR_NO")
        String prtnrNo,
        @JsonProperty("BRMGR_PRTNR_NO")
        String brmgrPrtnrNo,
        @JsonProperty("LIF_PD_CD")
        String lifPdCd,
        @JsonProperty("LIF_PD_NM")
        String lifPdNm,
        @JsonProperty("RCPDT")
        String rcpdt,
        @JsonProperty("CNTR_DT")
        String cntrDt,
        @JsonProperty("CAN_DT")
        String canDt,
        @JsonProperty("TOT_DSB_OJ_DV_CD")
        String totDsbOjDvCd,
        @JsonProperty("SL_OC_ACU_AMT")
        Long slOcAcuAmt,
        @JsonProperty("DP_ACU_AMT")
        Long dpAcuAmt,
        @JsonProperty("FLPYM_TN")
        Integer flpymTn,
        @JsonProperty("WELS_CNTR_NO")
        String welsCntrNo,
        @JsonProperty("WELS_CNTR_SN")
        String welsCntrSn,
        @JsonProperty("FEE_DSB_YM")
        String feeDsbYm,
        @JsonProperty("FEE_REDF_YM")
        String feeRedfYm,
        @JsonProperty("CNFM_YN")
        String cnfmYn,
        @JsonProperty("DTA_DL_YN")
        String dtaDlYn,
        @JsonProperty("FST_RGST_DTM")
        String fstRgstDtm,
        @JsonProperty("FST_RGST_USR_ID")
        String fstRgstUsrId,
        @JsonProperty("FST_RGST_PRG_ID")
        String fstRgstPrgId,
        @JsonProperty("FST_RGST_DEPT_ID")
        String fstRgstDeptId,
        @JsonProperty("FNL_MDFC_DTM")
        String fnlMdfcDtm,
        @JsonProperty("FNL_MDFC_USR_ID")
        String fnlMdfcUsrId,
        @JsonProperty("FNL_MDFC_PRG_ID")
        String fnlMdfcPrgId,
        @JsonProperty("FNL_MDFC_DEPT_ID")
        String fnlMdfcDeptId
    ) {}
    @Builder
    @ApiModel("WfeaLifeSaleCancelFeeInterfaceDto-SaveReq")
    public record SaveReq(
        @JsonProperty("LIST")
        List<IfRequest> list
    ) {}

    @Builder
    @ApiModel("WfeaLifeSaleCancelFeeInterfaceDto-SaveRes")
    public record SaveRes(
        @JsonProperty("RS_CD")
        String rsCd, /*결과코드*/
        @JsonProperty("RS_MSG")
        String rsMsg /*결과메세지*/
    ) {}

}
