package com.kyowon.sms.wells.web.contract.ordermgmt.dto;

import javax.validation.constraints.NotBlank;

import com.sds.sflex.common.utils.DbEncUtil;
import com.sds.sflex.system.config.validation.validator.ValidDate;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WctaContractDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // 계약번호 Search Request Dto
    @Builder
    @ApiModel("WctaContractDto-SearchCntrNoReq")
    public record SearchCntrNoReq(
        String cntrCstKnm,
        String istCstKnm,
        String cralLocaraTno,
        String mexnoEncr,
        String cralIdvTno,
        String cntrCstNo
    ) {
        public SearchCntrNoReq {
            mexnoEncr = DbEncUtil.enc(mexnoEncr);
        }
    }

    // 홈케어 계약 Search Request Dto
    @ApiModel("WctaContractDto-SearchHomecareContractsReq")
    public record SearchHomecareContractsReq(
        @NotBlank
        String cntrNo,
        int cntrSn
    ) {}

    // 홈케어 계약 Save Request Dto
    @ApiModel("WctaContractDto-SaveHomecareContractsReq")
    public record SaveHomecareContractsReq(
        @NotBlank
        String cntrNo,
        int cntrSn,
        @ValidDate
        String candt,
        @ValidDate
        String duedt
    ) {}

    // 메일발송 Save Request Dto
    @ApiModel("WctaContractDto-SaveSendEmailsReq")
    public record SaveSendEmailsReq(
        @NotBlank
        String cntrNm,
        @NotBlank
        String cntrNo,
        @NotBlank
        String emadr
    ) {}

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
        String standardDt,
        boolean aprReqCtgValid

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

    //wells 일시불 판매금 조회 - 일시불 판매금 조회 Search Request Dto
    @ApiModel(value = "WctaContractDto-SearchSpaySlamtInqrReq")
    public record SearchSpaySlamtInqrReq(
        String pdGubn,
        String vlDtm,
        String pdCd,
        String pdClsfId,
        String vstCycl,
        String useGubn,
        String dscGubn,
        String dscType
    ) {}

    //wells 렌탈 패키지 그룹 등록 삭제 - 렌탈 패키지 그룹 등록 삭제 Remove Request Dto
    @ApiModel("WctaContractDto-RemoveRentalPackageGrpMngtsReq")
    public record RemoveRentalPackageGrpMngtsReq(
        String baseDtlCntrNo,
        String baseDtlCntrSn,
        String cntrRelDtlCd,
        String cntrCstGrpId
    ) {}

    // 재약정/멤버십 대상자 조회 Search Request Dto
    @ApiModel("WctaContractDto-SearchRnwMshCstReq")
    public record SearchRnwMshCstReq(
        String baseDtlCntrNo,
        String baseDtlCntrSn
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 계약번호 Search Result Dto
    @ApiModel("WctaContractDto-SearchCntrNoRes")
    public record SearchCntrNoRes(
        String cntrCnfmDtm,
        String cntrNo,
        String cntrSn,
        String cntrCstKnm,
        String istCstKnm,
        String pdNm
    ) {}

    // 홈케어 계약 Search Result Dto
    @ApiModel("WctaContractDto-SearchHomecareContractsRes")
    public record SearchHomecareContractsRes(
        String cntrNo,
        int cntrSn,
        String rcgvpKnm,
        String pdCd,
        String pdNm,
        String cntrCnfmDtm
    ) {}

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
        String cntrAprAkDvCdNm,
        String cntrNo
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
    //wells 렌탈료 가져오기 SearchReq
    @ApiModel("WctaContractDto-SearchRentalFeeReq")
    public record SearchRentalFeeReq(
        @NotBlank
        String pdCd,
        @NotBlank
        String svVstPrdCd,
        @NotBlank
        String svTpCd,
        @NotBlank
        String rgstFee,
        @NotBlank
        String chgMcn,
        @NotBlank
        String verSn
    ) {}

    //wells 일시불 판매금 조회 - 일시불 판매금 조회 Search Result Dto
    @ApiModel("WctaContractDto-SearchSpaySlamtInqrRes")
    public record SearchSpaySlamtInqrRes(
        String fnlVal,
        String vat,
        String AckmtRt,
        String AckmtAmt,
        String ctrVal,
        String sellFee,
        String svFee
    ) {}

    //wells 홈케어멤버십 가입고객여부 조회 - 홈케어멤버십 가입고객여부 조회 Search Result Dto
    @ApiModel("WctaContractDto-SearchHomeCareMshChecksRes")
    public record SearchHomeCareMshChecksRes(
        String hcrMmbrCnt,
        String hcrMmbrYn,
        String hcrSpayCnt
    ) {}

    //wells 렌탈 패키지 그룹 기등록 조회 - 렌탈 패키지 그룹 기등록 조회 Search Result Dto
    @ApiModel("WctaContractDto-SearchRentalPackageGrpMngtsRes")
    public record SearchRentalPackageGrpMngtsRes(
        String baseDtlCntrNo,
        String baseDtlCntrSn,
        String startYn
    ) {}

    //wells 렌탈 패키지 그룹 등록 - 렌탈 패키지 그룹 저장 Save Result Dto
    @ApiModel("WctaContractDto-SaveRentalPackageGrpMngtsReq")
    public record SaveRentalPackageGrpMngtsReq(
        String userGubn,
        String inpGubn,
        @NotBlank
        String cntrRelId,
        String vlStrtDtm,
        String vlEndDtm,
        String cntrUnitTpCd,
        String cntrRelTpCd,
        String cntrRelDtlCd,
        String baseCntrNo,
        String ojCntrNo,
        String baseDtlCntrNo,
        String baseDtlCntrSn,
        String ojDtlCntrNo,
        String ojDtlCntrSn,
        String cntrCstGrpId,
        String cntrRelDtlCn,
        String otsdLkDrmVal,
        @NotBlank
        String dtaDlYn
    ) {}

    // 재약정/멤버십 대상자 조회 Search Result Dto
    @ApiModel("WctaContractDto-SearchRnwMshCstRes")
    public record SearchRnwMshCstRes(
        String baseDtlCntrNo,
        String baseDtlCntrSn
    ) {}
}
