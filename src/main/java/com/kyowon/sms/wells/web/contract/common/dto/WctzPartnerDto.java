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
        String dlpnrCd,
        String dlpnrNm,
        String dlgpsNm,
        String bryyMmdd
    ) {}
}
