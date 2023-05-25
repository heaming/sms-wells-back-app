package com.kyowon.sms.wells.web.organization.hmnrsc.dto;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WogcPartnerSellerDto {

    @ApiModel(value = "WogcPartnerSellerDto-SearchInformationConfirmReq")
    @Builder
    public record SearchInformationConfirmReq(
        String ogTpCd,
        String prtnrKnm,
        String bryyMmdd,
        String sexDvCd,
        String ymd
    ) {}

    @ApiModel(value = "WogcPartnerSellerDto-SearchInformationConfirmRes")
    @Builder
    public record SearchInformationConfirmRes(
        String prtnrNo,
        String pstnDvCd,
        String chk
    ) {}
}
