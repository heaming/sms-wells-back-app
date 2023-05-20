package com.kyowon.sms.wells.web.fee.control.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WfedManagerVisitFeeDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    //  Search Request Dto
    @Builder
    @ApiModel("WfedManagerVisitFeeDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String baseYm,
        @NotBlank
        String inqrDv,

        String ogLevlDvCd1,
        String ogLevlDvCd2,
        String ogLevlDvCd3,
        String no
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    //  Search Result Dto
    @ApiModel("WfedManagerVisitFeeDto-SearchRes")
    public record SearchRes(

        String dgr1LevlOgId, /*총괄단*/
        String dgr2LevlOgId, /*지역단*/
        String dgr3LevlOgId, /*지점*/
        String prtnrNo,
        String prtnrKnm,
        String cntrNo, /*계약번호*/
        String basePdCd, /*상품코드*/
        String pdNm, /*상품명*/
        String svFeePdDvCd, /*bs상품군*/
        String svFeePdDvNm, /*bs상품군*/
        int svFeeBaseAmt, /*bs기본수수료*/
        int feeCalcAmt, /*방문수수료*/
        String vstRglvlGdCd, /*방문급지*/
        String vstRglvlGdNm, /*방문급지*/
        String wkExcnDt, /*방문일자*/
        String canYn

    ) {}
}
