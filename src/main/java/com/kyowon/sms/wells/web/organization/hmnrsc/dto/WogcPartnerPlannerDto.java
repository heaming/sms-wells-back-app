package com.kyowon.sms.wells.web.organization.hmnrsc.dto;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WogcPartnerPlannerDto {

    @ApiModel(value = "WogcPartnerPlannerDto-SearchLicenseReq")
    public record SearchLicenseReq(
        String ogTpCd,
        String prtnrKnm,
        String prtnrNo,
        String olfDvCd
    ) {}

    @ApiModel(value = "WogcPartnerPlannerDto-SearchLicenseRes")
    public record SearchLicenseRes(
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

    @ApiModel(value = "WogcPartnerPlannerDto-SearchReq")
    @Builder
    public record SearchReq(
        String ogTpCd,
        String prtnrKnm,
        String prtnrNo,
        String mOgYn,
        String mngtYm,
        String ogId,
        String olfDvCd
    ) {}

    @ApiModel(value = "WogcPartnerPlannerDto-SearchRes")
    public record SearchRes(
        String dgr1LevlOgCd,
        String dgr1LevlOgNm,
        String dgr2LevlOgCd,
        String dgr2LevlOgNm,
        String dgr3LevlOgCd,
        String dgr3LevlOgNm,
        String ogCd,
        String mpiPrtnrKnm,
        String prtnrNo,
        String qlfDvCd,
        String rcmdrPrtnrNo,
        String pbPrtnrKnm,
        String cntrDt,
        String fnlCltnDt,
        String rcntrDt
    ) {}

    @ApiModel(value = "WogcPartnerPlannerDto-DeleteReq")
    @Builder
    public record DeleteReq(
        String ogTpCd,
        String prtnrKnm,
        String prtnrNo,
        String aplcSn
    ) {}

    @ApiModel(value = "WogcPartnerPlannerDto-SaveReq")
    @Builder
    public record SaveReq(
        String mngtYm,
        String ogTpCd,
        String prtnrKnm,
        String prtnrNo,

        String qlfDvCd
    ) {}

    @ApiModel(value = "WogcPartnerPlannerDto-SearchCountMmPartnerReq")
    @Builder
    public record SearchCountMmPartnerReq(
        String ogTpCd,
        String prtnrKnm,
        String prtnrNo,
        String olfDvCd
    ) {}

    @ApiModel(value = "WogcPartnerPlannerDto-SearchCountPlarPartnerReq")
    @Builder
    public record SearchCountPlarPartnerReq(
        String ogTpCd,
        String prtnrKnm,
        String prtnrNo,
        String olfDvCd
    ) {}

    @ApiModel(value = "WogcPartnerPlannerDto-FindRes")
    public record FindRes(
        String mngtYm,
        String prtnrNo,
        String prtnrKnm,
        String upgrYm,
        String dmtnYm,
        String qlfDvCd,
        String rfdt,
        String fstRgstDtm,
        String fstRgstUsrId,
        String pb2PrtnrNo,
        String pb2PrtnrKnm,
        String fnlMdfcDtm,
        String fnlMdfcUsrId,
        String pb3PrtnrNo,
        String pb3PrtnrKnm
    ) {}

    @ApiModel(value = "WogcPartnerPlannerDto-EditReq")
    @Builder
    public record EditReq(
        String ogTpCd,
        String prtnrKnm,
        String prtnrNo,
        String olfDvCd
    ) {}

}
