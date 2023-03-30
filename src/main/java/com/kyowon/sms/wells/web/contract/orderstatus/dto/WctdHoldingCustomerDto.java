package com.kyowon.sms.wells.web.contract.orderstatus.dto;

import javax.validation.constraints.Pattern;

import com.sds.sflex.system.config.validation.validator.ValidDate;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WctdHoldingCustomerDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // 보류고객 관리 Search Request Dto
    @Builder
    @ApiModel("WctdHoldingCustomerDto-SearchReq")
    public record SearchReq(
        @ValidDate
        String StartDt,
        @ValidDate
        String endDt,
		@Pattern(regexp = "^$|[YN]")
        String cttYn,
        String cntrNo,
        Integer cntrSn
    ) {
    }

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 보류고객 관리 Search Result Dto
    @ApiModel("WctdHoldingCustomerDto-SearchRes")
    public record SearchRes(
        String cntrNo,
        String cntrSn,
        String cntrCnfmDtm, // cut of time in front
        String pdNm,
        String cttRsCd, // codes: LC_CTT_RS_CD
        String cttMoCn,
        String ogCd,
        String prtnrKnm,
        String cttYn,
        String cstKnm
    ) {
    }
}
