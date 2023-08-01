package com.kyowon.sms.wells.web.deduction.redf.dto;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WdeaAllowanceDelinquentRedemptionFeeDto {

    @Builder
    @ApiModel("WdeaAllowanceDelinquentRedemptionFeeDto-SearchReq")
    public record SearchReq(
        String prtnrNo,
        String ogTpCd,

        String perfDvCd,
        String whtxRepDvCd
    ) {}

    @ApiModel("WdeaAllowanceDelinquentRedemptionFeeDto-SearchRes")
    public record SearchRes(
        String redfAdsbOcYm,
        String perfDvCd,
        String perfDvNm,
        String whtxRepDvNm,
        String cntrNo,
        String prtnrNo,
        String prtnrKnm,
        String cstKnm,
        String ogTpCd,
        String coCd,
        String perfYm,
        String pdNm,
        String envYn,
        String cntrCanRsonCd,
        String cntrPrgsStatMoCn,
        String mchnChYn,
        String mchnChTpCd,
        String feeCpsnRedfYn,
        String redfAdsbTpNm,
        int redfAdsbOcAmt
    ) {}

}
