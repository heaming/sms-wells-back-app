package com.kyowon.sms.wells.web.service.allocate.dto;

import com.sds.sflex.common.utils.DbEncUtil;
import io.swagger.annotations.ApiModel;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

public class WsncManagementCstRglvlDto {
    @ApiModel(value = "WsncManagementCstRglvlDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String manageYm,
        String rcgvpDiv,
        String selectDiv,
        String exceptWellsManagerYn,
        @PositiveOrZero
        String addressZipFrom,
        @PositiveOrZero
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
        @NotBlank
        String mngtPrtnrOgTpCd,
        @NotBlank
        String mngtPrtnrNo,
        @NotBlank
        String mngStdMngerRglvlDvCd,
        @NotBlank
        String cstSvAsnNo,
        @NotBlank
        String asnPsicPrtnrOgTpCd,
        @NotBlank
        String asnPsicPrtnrNo,
        @NotBlank
        String cnfmPsicPrtnrOgTpCd,
        @NotBlank
        String cnfmPsicPrtnrNo,
        @NotBlank
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
        String curMnthAlctnMngerRglvlDvCd,
        String cstSvAsnNo

    ) {}

    @ApiModel(value = "WsncManagementCstRglvlDto-OrganizationRes")
    public record OrganizationRes(
        String dgr1LevlOgId,
        String dgr2LevlOgId,
        String dgr3LevlOgId
    ) {}
}
