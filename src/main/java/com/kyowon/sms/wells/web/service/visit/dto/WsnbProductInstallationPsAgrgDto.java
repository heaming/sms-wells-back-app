package com.kyowon.sms.wells.web.service.visit.dto;

import io.swagger.annotations.ApiModel;

public class WsnbProductInstallationPsAgrgDto {

    @ApiModel(value = "WsnbProductInstallationPsDto-SearchReq")
    public record SearchReq(
        String baseYy,
        String pdGrpCd,
        String pdCd
    ) {}
    @ApiModel(value = "WsnbProductInstallationPsDto-SearchRes")
    public record SearchRes(
        String yyyy,
        String svBizMclsfCd,
        String svBizMclsfCdNm,
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
