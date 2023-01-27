package com.kyowon.sms.wells.web.contract.ordermgmt.dto;

import javax.validation.constraints.NotBlank;

import com.sds.sflex.system.config.validation.validator.ValidDate;

import io.swagger.annotations.ApiModel;

public class WctaContractDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // 고위험 파트너 Remove Request Dto
    @ApiModel("WctaConfirmApprovalDto-RemoveReq")
    public record RemoveReq(
        @NotBlank
        String cntrAprAkDvPk,
        @NotBlank
        @ValidDate
        String vlStartDtmPk
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 고위험 파트너 Search Result Dto
    @ApiModel("WctaConfirmApprovalDto-SearchRes")
    public record SearchRes(
        String cntrAprAkDvCd,
        String cntrAprAkDvPk,
        String cntrAprAkDvCdNm,
        String cntrAprAkDvNm,
        String cntrAprAkMsgCn,
        String cntrAprCanMsgCn,
        String cntrAprConfMsgCn,
        String vlStrtDtm,
        String vlStrtDtmPk,
        String vlEndDtm
    ) {}
}
