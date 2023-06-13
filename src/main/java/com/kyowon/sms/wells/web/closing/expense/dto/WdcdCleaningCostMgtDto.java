package com.kyowon.sms.wells.web.closing.expense.dto;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

import javax.validation.constraints.NotBlank;

public class WdcdCleaningCostMgtDto {

    // *********************************************************
    // Request Dto
    // *********************************************************
    // 청소 용품비 관리
    @Builder
    @ApiModel(value = "WdcdCleaningCostMgtDto-CodeRes")
    public record CodeRes(
        String bldCd,
        String bldNm
    ) {
    }

    // *********************************************************
    // Request Dto
    // *********************************************************
    // 청소 용품비 관리
    @Builder
    @ApiModel(value = "WdcdCleaningCostMgtDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String aplcStartDt,
        @NotBlank
        String aplcEndDt,
        String bldCd
    ) {
    }

    // *********************************************************
    // Request Dto
    // *********************************************************
    // 청소 용품비 관리
    @Builder
    @ApiModel(value = "WdcdCleaningCostMgtDto-SearchRes")
    public record SearchRes(
        String clingCostAdjRcpNo,
        String fstRgstDtm,
        String fnlMdfcDtm,
        String clingCostDvCd,
        String clingCostDvNm,
        String bldCd,
        String bldNm,
        //@MaskRequired(type = MaskingType.NAME)
        String claimNm,
        String aplcDt,
        String bilAmt,
        String clingCostSrcpApnFileId
    ) {
    }
}
