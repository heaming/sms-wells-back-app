package com.kyowon.sms.wells.web.fee.confirm.dto;

import javax.validation.constraints.NotBlank;

import com.sds.sflex.system.config.validation.validator.ValidDate;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WfeeFeeSpecificationDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // EDU 수수료 지급명세서 Search Request Dto
    @Builder
    @ApiModel("WfeeFeeSpecificationDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String perfDt, //실적년월
        String ogTpCd, //조직유형
        String rsbDvCd, //직책유형
        String feeCalcUnitTpCd, // 수수료 계산단위 유형코드
        String prtnrNo,
        String ogLevel1,
        String ogLevel2,
        String ogLevel3
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // EDU 수수료 지급명세서  - 수수료 코드 값 가져옴
    @ApiModel("WfeeFeeSpecificationDto-SearchFeeCdRes")
    public record SearchFeeCdRes(
        String feeCd,
        String feeNm,
        String feeCalcUnitTpCd

    ) {}
}
