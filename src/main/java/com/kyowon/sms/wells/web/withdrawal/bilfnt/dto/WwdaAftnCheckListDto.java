package com.kyowon.sms.wells.web.withdrawal.bilfnt.dto;

import io.swagger.annotations.ApiModel;

public class WwdaAftnCheckListDto {

    @ApiModel("WwdaAftnCheckListDto-SearchAftnBilNrcvListReq")
    public record SearchAftnBilNrcvListReq(
        String bilDt,
        String fntDvCd
    ) {}

    @ApiModel("WwdaAftnCheckListDto-SearchAftnBilNrcvListRes")
    public record SearchAftnBilNrcvListRes(
        String autoFntClsf,
        String bnkCd,
        String bnkNm,
        String ct
    ) {}
}
