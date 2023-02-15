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
        String cntrAprAkDvCd,
        @NotBlank
        @ValidDate
        String vlStrtDtm
    ) {}

    //wells 확정 승인 기준 관리 - 확정 승인 기준 관리 조회 Search Request Dto
    @ApiModel("WctaContractDto-SearchConfirmApprovalBaseReq")
    public record SearchConfirmApprovalBaseReq(
        String cntrAprAkDvCd,
        String standardDt
    ) {}

    //wells 확정 승인 기준 관리 - 확정 승인 기준 관리 저장 Save Result Dto
    @ApiModel("WctaContractDto-SaveConfirmApprovalBaseReq")
    public record SaveConfirmApprovalBaseReq(
        @NotBlank
        String rowState,
        String checkType,
        String cntrAprBaseId,
        @NotBlank
        String cntrAprSellDvCd,
        @NotBlank
        String cntrAprAkDvCd,
        @NotBlank
        String cntrAprChnlDvVal,
        @NotBlank
        String cntrAprIchrDvCd,
        @NotBlank
        String ichrUsrId,
        @NotBlank
        String psicNm,
        @NotBlank
        String vlStrtDtm,
        @NotBlank
        String vlEndDtm,
        @NotBlank
        String cntrAprAkDvCdNm,

        @NotBlank
        String dtaDlYn,
        String notyFwOjYn
    ) {}

    //wells 확정 승인 기준 관리 - 확정 승인 기준 관리 삭제 Remove Result Dto
    @ApiModel("WctaContractDto-RemoveConfirmApprovalBaseReq")
    public record RemoveConfirmApprovalBaseReq(
        @NotBlank
        String cntrAprBaseId,
        String cntrAprAkDvCd,
        String cntrAprSellDvCd,
        String cntrAprChnlDvVal,
        String cntrAprIchrDvCd,
        String ichrUsrId,
        String psicNm,
        String vlStrtDtm
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
        String akUsrId,
        String rqrNm,
        String cntrAprFwDvCd,
        String cntrAprFwDvNm,
        String rcvUsrNm,
        String sendDttm,
        String aprvYn,
        String aprvId,
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

    //wells 확정 승인 기준 관리 - 확정 승인 기준 관리 조회 Search Result Dto
    @ApiModel("WctaContractDto-SearchConfirmApprovalBaseRes")
    public record SearchConfirmApprovalBaseRes(

        String cntrAprBaseId,

        String cntrAprSellDvCd,
        String cntrAprAkDvCd,
        String cntrAprChnlDvVal,
        String cntrAprIchrDvCd,
        String ichrUsrId,
        String psicNm,
        String vlStrtDtm,
        String vlEndDtm,
        String notyFwOjYn
    ) {}
}
