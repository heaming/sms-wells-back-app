package com.kyowon.sms.wells.web.service.allocate.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;

public class WsncVisitCustomerRglvlStateDto {
    @ApiModel(value = "WsncVisitCustomerRglvlStateDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String visitYm,
        String rcgvpDiv,
        String mngerRglvlDvCd,
        String localGroup,
        String partnerNo
    ) {}

    @ApiModel(value = "WsncVisitCustomerRglvlStateDto-SearchRes")
    public record SearchRes(
        String cntrNo,
        String cntrSn,
        String cntrCstNo,
        String rcgvpDiv,
        String rcgvpKnm,
        String basePdCd,
        String basePdNm,
        String pdctPdCd,
        String pdNm,
        String svPdCd,
        String svPdNm,
        String sellTpCd,
        String sellTpNm,
        String istNmnN,
        String vstNmnN,
        String vstPrgsStatCd,
        String vstPrgsStatNm,
        String newAdrZip,
        String addr,
        String adrDvCd,
        String adrId,
        String ogCd,
        String ogNm,
        String fxnPrtnrYn,
        String fxnPrtnrYnNm,
        String mngtPrtnrNo,
        String prtnrKnm,
        String mngrDvCd,
        String mngtPrtnrOgTpCd,
        String mngerRglvlDvCd,
        String hgrOgId,
        String dgr2LevlOgCd,
        String dgr2LevlOgNm,
        String svpdSapCd,
        String rglvlDstn,
        String cstSvAsnNo

    ) {}

}
