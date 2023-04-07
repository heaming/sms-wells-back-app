package com.kyowon.sms.wells.web.contract.common.dto;

import io.swagger.annotations.ApiModel;

public class WctzPartnerDto {
    // *********************************************************
    // Request Dto
    // *********************************************************

    // *********************************************************
    // Result Dto
    // *********************************************************
    //  Search Result Dto
    @ApiModel("WctzPartnerDto-SearchEntrepreneurBasesRes")
    public record SearchEntrepreneurBaseRes(
        String bzrno,
        String dlpnrNm,
        String dlgpsNm,
        String bryyMmdd
    ) {}

    //  Search Result Dto
    @ApiModel("WctzPartnerDto-SearchGeneralDivisionsRes")
    public record SearchGeneralDivisionsRes(
        String dgr1LevlOgCd,
        String dgr1LevlOgNm,
        String dgr1LevlDgPrtnrNo,
        String dgr1LevlOgId,
        String ogTpCd
    ) {}
    //  Search Result Dto
    @ApiModel("WctzPartnerDto-SearchRegionalDivisionsRes")
    public record SearchRegionalDivisionsRes(
        String dgr2LevlOgCd,
        String dgr2LevlOgNm,
        String dgr2LevlDgPrtnrNo,
        String dgr2LevlOgId,
        String ogTpCd,
        String dgr1LevlOgCd
    ) {}

    //  Search Result Dto
    @ApiModel("WctzPartnerDto-SearchBranchDivisionsRes")
    public record SearchBranchDivisionsRes(
        String dgr3LevlOgCd,
        String dgr3LevlOgNm,
        String dgr3LevlDgPrtnrNo,
        String dgr3LevlOgId,
        String ogTpCd,
        String dgr1LevlOgCd,
        String dgr2LevlOgCd
    ) {}
}
