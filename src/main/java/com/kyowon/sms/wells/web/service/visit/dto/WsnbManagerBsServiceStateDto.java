package com.kyowon.sms.wells.web.service.visit.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;

public class WsnbManagerBsServiceStateDto {

    @ApiModel(value = "WsnbManagerBsServiceStateDto-SearchReq")
    public record SearchReq(

        @NotBlank
        String baseYy,
        String cnfmPsicDvCd,
        String vstPrgsStatCd,
        String pdGrpCd,
        String pdCd

    ) {}

    @ApiModel(value = "WsnbManagerBsServiceStateDto-SearchRes")
    public record SearchRes(
        String yyyy,
        String dgBizTpNm,
        String acol1,
        String acol2,
        String acol3,
        String acol4,
        String acol5,
        String acol6,
        String acol7,
        String acol8,
        String acol9,
        String acol10,
        String acol11,
        String acol12,
        String tcnt,
        String per

    ) {}
}
