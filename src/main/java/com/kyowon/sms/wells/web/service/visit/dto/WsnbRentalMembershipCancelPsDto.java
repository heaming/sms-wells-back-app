package com.kyowon.sms.wells.web.service.visit.dto;

import io.swagger.annotations.ApiModel;

public class WsnbRentalMembershipCancelPsDto {

    @ApiModel("WsnbRentalMembershipCancelPsDto-SearchReq")
    public record SearchReq(

        String baseYm,

        String sellTpCd,

        String mngrDvCd,

        String ogId,

        String prtnrNo,

        String dgr1LevlOgId,

        String dgr2LevlOgId,

        String dgr3LevlOgId
    ) {}

    @ApiModel("WsnbRentalMembershipCancelPsDto-SearchRes")
    public record SearchRes() {}
}
