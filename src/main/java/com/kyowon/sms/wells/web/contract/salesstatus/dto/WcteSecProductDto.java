package com.kyowon.sms.wells.web.contract.salesstatus.dto;

import com.sds.sflex.system.config.validation.validator.ValidDate;
import io.swagger.annotations.ApiModel;
import lombok.Builder;

import javax.validation.constraints.NotBlank;

public class WcteSecProductDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // 삼성전자 주문 정보 예약일 관리 Search Request Dto
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
    // 삼성전자 주문 정보 관리 Search Result Dto
    @ApiModel("WcteSecProductDto-SearchReservationRes")
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
        description= "삼성전자 주문 정보 확정일 Response Dto"
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
}
