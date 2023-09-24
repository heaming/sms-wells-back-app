package com.kyowon.sms.wells.web.service.allocate.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;

public class WsncMonthBsExpcCustAgrgStateDto {
    @ApiModel(value = "WsncMonthBsExpcCustAgrgStateDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String mngrDvCd,
        String baseYm,
        String dgr1LevlOgId,
        String dgr2LevlOgId,
        String dgr3LevlOgId,
        String ogId,
        String prtnrNo

    ) {}

    @ApiModel(value = "WsncMonthBsExpcCustAgrgStateDto-SearchRes")
    public record SearchRes(
        String prtnrNo,
        String ogNm,
        String prtnrKnm,
        String pstnDvCd,
        String bldNm,
        String mngrCustCnt,
        String g42,
        String g42Mini,
        String g411,
        String g412,
        String g43,
        String g44,
        String cof,
        String chr,
        String fWide,
        String fSlim,
        String farm,
        String dryer,
        String aircon,
        String airdresser,
        String washer,
        String mj,
        String tot

    ) {}
}
