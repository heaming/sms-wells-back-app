package com.kyowon.sms.wells.web.contract.salesstatus.dto;

import com.sds.sflex.system.config.validation.validator.ValidDate;
import io.swagger.annotations.ApiModel;
import lombok.Builder;

import javax.validation.constraints.NotBlank;

public class WcteSecProductDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // 삼성전자 주문 정보 관리 Search Request Dto
    @Builder
    @ApiModel("WcteSecProductDto-SearchReq")
    public record SearchReq(
        @NotBlank
        @ValidDate
        String strtdt,

        @NotBlank
        @ValidDate
        String enddt,
        String sellTpCd
    ) {
    }

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 삼성전자 주문 정보 관리 Search Result Dto
    @ApiModel("WcteSecProductDto-SearchRes")
    public record SearchRes(
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
}
