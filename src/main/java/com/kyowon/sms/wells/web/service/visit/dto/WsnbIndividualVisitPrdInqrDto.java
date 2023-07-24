package com.kyowon.sms.wells.web.service.visit.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;

public class WsnbIndividualVisitPrdInqrDto {

    @ApiModel("WsnbIndividualVisitPrdInqrDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String cntrNo,
        @NotBlank
        String cntrSn
    ) {}
    @ApiModel("WsnbIndividualVisitPrdInqrDto-SearchCustomerVisitIzRes")
    public record SearchCustomerVisitIzRes(
        String vstNmnN,
        String istNmnN,
        String vstDuedt,
        String svBizDclsfCd,
        String svBizDclsfNm,
        String filtChngLvCd,
        String pdCd,
        String pdNm,
        String wkDt,
        String mtrCanDt
    ) {}

    @ApiModel("WsnbIndividualVisitPrdInqrDto-SearchManagementCstInqrRes")
    public record SearchManagementCstInqrRes(
        String mngtYm,
        String mngrDvNm,
        String prtnrKnm,
        String prtnrNo,
        String mngerRglvlDvCd,
        String svHshdNo,
        String ogTpCd,
        String vstPrdNm
    ) {}
}
