package com.kyowon.sms.wells.web.service.allocate.dto;

import io.swagger.annotations.ApiModel;

import javax.validation.constraints.NotBlank;

public class WsncManagementCstRglvlDto {
    @ApiModel(value = "WsncManagementCstRglvlDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String manageYm,
        String rcgvpDiv,
        String exceptWellsManagerYn,
        String addressZipFrom,
        String addressZipTo,
        @NotBlank
        String localGroupCd,
        String branchOfficeCd,
        String partnerNo
    ) {}

    @ApiModel(value = "WsncManagementCstRglvlDto-SavePartnerReq")
    public record SavePartnerReq(
        @NotBlank
        String cntrNo,
        @NotBlank
        String cntrSn,
        String mngtPrtnrNo,
        String mngStdMngerRglvlDvCd,
        @NotBlank
        String cstSvAsnNo,
        String asnPsicPrtnrNo,
        String cnfmPsicPrtnrNo,
        String curMnthAlctnMngerRglvlDvCd
    ) {}
    @ApiModel(value = "WsncManagementCstRglvlDto-SearchRes")
    public record SearchRes(
        String cntrNo,
        String cntrSn,
        String rcgvpKnm,
        String svpdSapCd,
        String pdctPdCd,
        String svpdNmAbbr1,
        String istDt,
        String newAdrZip,
        String adr,
        String adrEmd,
        String rcgvpDiv,
        String fix,
        String vstDuedt,
        String mngStdDgr2LevlOgCd,
        String mngStdDgr3LevlOgCd,
        String mngStdPrtnrKnm,
        String mngStdMngerRglvlDvCd,
        String curMnthAlctnDgr2LevlOgCd,
        String curMnthAlctnDgr3LevlOgCd,
        String curMnthAlctnPrtnrKnm,
        String bfchIchrBrchOgId,
        String bfchIchrPrtnrOgTpCd,
        String bfchIchrPrtnrNo,
        String curMnthAlctnMngerRglvlDvCd,
        String cstSvAsnNo,
        String mngerRglvlDvCd
    ) {}

    @ApiModel(value = "WsncManagementCstRglvlDto-OrganizationRes")
    public record OrganizationRes(
        String dgr1LevlOgId,
        String dgr2LevlOgId,
        String dgr3LevlOgId
    ) {}
}
