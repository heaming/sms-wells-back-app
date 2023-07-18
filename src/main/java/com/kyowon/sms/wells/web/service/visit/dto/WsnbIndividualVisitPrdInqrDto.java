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
        String fxnPrtnrDvCd,
        String fxnPrtnrNm,
        String fxnPrtnrNo,
        String mngerRglvlDvCd,
        String mngerRglvlDvNm,
        String svHshdNo,
        String exHsHdYn,
        String hshdExcdCnfmYn,
        String hshdExcdCd,
        String ogTpCd
    ) {}
}
