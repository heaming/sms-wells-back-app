package com.kyowon.sms.wells.web.organization.hmnrsc.dto;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WogcPartnerPlannerDto {

    @ApiModel(value = "WogcPartnerPlannerDto-SearchPlannerLicenseReq")
    @Builder
    public record SearchPlannerLicenseReq(
        String ogTpCd,
        String prtnrKnm,
        String prtnrNo,
        String olfDvCd
    ) {}

    @ApiModel(value = "WogcPartnerPlannerDto-SearchPlannerLicenseRes")
    public record SearchPlannerLicenseRes(
        String level2Nm,
        String level3Nm,
        String level4Nm,
        String ogCd,
        String bldNm,
        String prntrNo,
        String prntrKnm,
        String telno,
        String rrnoFrpsnVal,
        String a,
        String b,
        String ogId
    ) {}

}
