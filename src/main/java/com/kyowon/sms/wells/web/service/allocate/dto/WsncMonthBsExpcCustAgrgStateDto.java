package com.kyowon.sms.wells.web.service.allocate.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;

public class WsncMonthBsExpcCustAgrgStateDto {
    @ApiModel(value = "WsncMonthBsExpcCustAgrgStateDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String mngrDvCd,
        @NotBlank
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
        String pstnDvNm,
        String bldNm,
        String mngrCustCnt,
        String wrfr,
        String miniWrfr,
        String bdtIndv,
        String bdtCrp,
        String arcl,
        String wtrSftnr,
        String cffMchn,
        String msgcr,
        String wellsFarmWide,
        String wellsFarmSlim,
        String wellsFarm,
        String dryr,
        String arcon,
        String ardrssr,
        String wash,
        String sding,
        String fieldSum

    ) {}
}
