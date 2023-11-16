package com.kyowon.sms.wells.web.fee.calculation.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WfebOutcomeAllowanceDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // WELLS 성과수당현황 Search Request Dto
    @Builder
    @ApiModel("WfebOutcomeAllowanceDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String perfYm,
        String rsbDvCd,
        String ogTpCd,
        String prtnrNo,
        Boolean thmInqr
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // WELLS M조직 성과수당현황 Search Result Dto
    @ApiModel("WfebOutcomeAllowanceDto-SearchManagerRes")
    public record SearchManagerRes(
        String ogCd,
        String ogNm,
        String prtnrKnm,
        String prtnrNo,
        Integer dtrcN,
        Integer trgCt,
        Integer baseDtrcN,
        Double perfCt,
        Double trgAchvRt,
        Integer nwSellAccCt,
        Integer purSprAccCt,
        Integer accNincCt,
        Double feeAckmtCt,
        Long elhmExcpPerfAmt,
        Integer elhmExcpCt,
        Integer redfCt,
        Long perfAggAmt,
        Integer mngrDangGd,
        Long trgAchvAwAmt,
        Integer brchNincCt,
        Double wmAclActiCt,
        Double bfsvcAclActiCt,
        Double aclActiBrchAvCt,
        Integer actvBrchN,
        Long ogAwAmt,
        Long dsbAmt
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // WELLS P조직 성과수당현황 Search Result Dto
    @ApiModel("WfebOutcomeAllowanceDto-SearchPlannerRes")
    public record SearchPlannerRes(
        String ogNm,
        String prtnrKnm,
        String prtnrNo,
        Integer trgCt,
        Double perfCt,
        Double trgAchvRt,
        Long trgAchvAwAmt,
        Integer thm1OptnTrgCt,
        Integer thm1OptnAchvCt,
        Double thm1OptnAchvRt,
        Long thm1OptnAwAmt,
        Integer aclActiTrgCt,
        Integer aclActiAchvCt,
        Double aclActiAchvRt,
        Long aclActiAwAmt,
        Long ogAchvAwSumAmt,
        Long ejtAwAmt,
        Long outcAwSumAmt
    ) {}
}
