package com.kyowon.sms.wells.web.contract.salesstatus.dto;

import com.sds.sflex.system.config.validation.validator.ValidDate;
import io.swagger.annotations.ApiModel;
import lombok.Builder;

import javax.validation.constraints.NotBlank;

public class WcteSecProductDto {
    // *********************************************************
    // Request Dto
    // *********************************************************

    @Builder
    @ApiModel(
        value = "WcteSecProductDto-SearchNotInstalledReq",
        description = "삼성전자 주문 정보 미설치 Search Request Dto"
    )
    public record SearchNotInstalledReq(
        @NotBlank
        @ValidDate
        String strtdt,

        @NotBlank
        @ValidDate
        String enddt,

        String cntrNo,
        Integer cntrSn,
        String cntrCstKnm,
        String strtOgCd,
        String endOgCd
    ) {
    }

    @Builder
    @ApiModel(
        value = "WcteSecProductDto-SearchReservationReq",
        description = "삼성전자 주문 정보 예약일 Search Request Dto"
    )
    public record SearchReservationReq(
        @NotBlank
        @ValidDate
        String strtdt,

        @NotBlank
        @ValidDate
        String enddt,
        String sellTpCd
    ) {
    }

    @Builder
    @ApiModel(
        value = "WcteSecProductDto-SearchConfirmReq",
        description = "삼성전자 주문 정보 확정일 Search Request Dto"
    )
    public record SearchConfirmReq(
        @NotBlank
        @ValidDate
        String strtdt,

        @NotBlank
        @ValidDate
        String enddt,
        String sellTpCd,
        String sppBzsOrdId,
        String pdctIdno
    ) {
    }

    @Builder
    @ApiModel(
        value = "WcteSecProductDto-CreateConfirmReq",
        description = "삼성전자 주문 정보 확정일 Request Dto"
    )
    public record CreateConfirmReq(
        @NotBlank
        String cntrNo,
        int cntrSn,
        @NotBlank
        @ValidDate
        String sppFshDt,
        @NotBlank
        String pdctIdno,
        @NotBlank
        String sppBzsModelId,
        @NotBlank
        String sppBzsOrdId
    ) {
    }

    // *********************************************************
    // Result Dto
    // *********************************************************

    @ApiModel(
        value = "WcteSecProductDto-SearchNotInstalledRes",
        description = "삼성전자 주문 정보 미설치 Response Dto"
    )
    public record SearchNotInstalledRes(
        String cntrNo,
        int cntrSn,
        String cntrCstKnm,
        String sppBzsOrdId,
        String pdHclsfNm,
        String pdMclsfNm,
        String cntrCnfmDtm,
        String duedt,
        String prtnrNo,
        String prtnrKnm,
        String prtnrOgCd,
        String canRson
    ) {
    }

    @ApiModel(
        value = "WcteSecProductDto-SearchReservationRes",
        description = "삼성전자 주문 정보 예약일 Response Dto"
    )
    public record SearchReservationRes(
        String ogCd,
        String hooPrtnrNo,
        String sellPrtnrNo,
        String prtnrKnm,
        String cntrNo,
        String cntrSn,
        String rcgvpKnm,
        String pdNm,
        String pdCd,
        String sppBzsOrdId,
        String sellTpCd,
        String resDt,
        String stocStrDt,
        String fstRgstDtm
    ) {
    }

    @ApiModel(
        value = "WcteSecProductDto-SearchConfirmRes",
        description = "삼성전자 주문 정보 확정일 Response Dto"
    )
    public record SearchConfirmRes(
        String cntrNo,
        int cntrSn,
        String sellTpCd,
        String pdHclsfId,
        String pdMclsfId,
        String rcgvpKnm,
        String sppBzsOrdId,
        String sppFshDt,
        String pdCd,
        String pdNm,
        String sppFshRgstDtm,
        String batWkFshDtm,
        String canDt,
        String cttRsCd,
        String pdctIdno,
        String sppBzsModelId,
        String rgstFeeFlpymYn
    ) {
    }

    // Search Sec Pd Bycf Res Dto
    @Builder
    @ApiModel("WcteSecProductDto-SearchSecPdBycfRes")
    public record SearchSecPdBycfRes(
        String pdMclsfId,
        String pdMclsfNm,
        String pdCd,
        String pdNm
    ) {
    }
}
