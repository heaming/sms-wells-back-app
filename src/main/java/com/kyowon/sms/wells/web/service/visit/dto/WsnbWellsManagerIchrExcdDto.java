package com.kyowon.sms.wells.web.service.visit.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;

public class WsnbWellsManagerIchrExcdDto {
    @ApiModel(value = "WsnbWellsManagerIchrExcdDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String mngtYm,
        String fromAdrZip,
        String toAdrZip,
        String dgr1LevlOgId,
        String dgr2LevlOgId,
        String accountDivCd,
        String exceptWellsFarmYn,
        String exceptFixYn,
        String exceptEgerWkYn
    ) {}

    @ApiModel(value = "WsnbWellsManagerIchrExcdDto-SearchRes")
    public record SearchRes(
        String mngtYm,
        String dgr1LevlOgCd,
        String dgr2LevlOgCd,
        String cntrNo,
        String cntrSn,
        String cntr,
        String rcgvpKnm,
        String svpdItemGrNm,
        String svpdNmAbbr1,
        String sellTpCd,
        String vstDvCd,
        String vstPrgsStatCd,
        String adr,
        String emd,
        String ogNm,
        String prtnrKnm,
        String clsfCdSrnPrntCn,
        String egerWk,
        String fix
    ) {}
}
