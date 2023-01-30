package com.kyowon.sms.wells.web.contract.ordermgmt.dto;

import javax.validation.constraints.NotBlank;

import com.sds.sflex.system.config.validation.validator.ValidDate;

import io.swagger.annotations.ApiModel;

public class WctaContractDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // 고위험 파트너 Remove Request Dto
    @ApiModel("WctaContractDto-RemoveReq")
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
    @ApiModel("WctaContractDto-SearchRes")
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

    // 확정승인 요청내역 - 확정 승인 요청 내역 Search Result Dto
    @ApiModel("WctaContractDto-SearchConfirmAprPsicAksRes")
    public record SearchConfirmAprPsicAksRes(
        String cntrAprFwId,
        String cntrAprAkDvCd,
        String akPrtnrNo,
        String rqrNm,
        String cntrAprFwDvCd,
        String cntrAprFwDvNm,
        String rcvUsrNm,
        String sendDttm,
        String arpvYn,
        String arpvId,
        String aprvNm,
        String aprvDttm,
        String cancYn,
        String ackdReqNm
    ) {}

    //확정승인 요청내역 - 확정 승인 구매 내역 Search Result Dto
    @ApiModel("WctaContractDto-SearchConfirmAprPsicPrchssRes")
    public record SearchConfirmAprPsicPrchssRes(
        String cntrNo,
        String cstKnm,
        String cstGdNm,
        String rcgvpKnm,
        String adr,
        String pdNm,
        String istDt,
        String useDiv,
        String apyTn,
        String dlqInfo
    ) {}
}
