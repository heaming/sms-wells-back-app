package com.kyowon.sms.wells.web.contract.risk.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WctcSalesLimitsDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // 접수제한 관리-블랙리스트 Search Request Dto
    @Builder
    @ApiModel("WctcSalesLimitsDto-SearchBlacklistReq")
    public record SearchBlacklistReq(
        String cntrCstNo,
        String cntrNo,
        String cstKnm,
        String adr,
        String tno,
        String selrInf
    ) {}

    // 접수제한 관리-블랙리스트 Save Request Dto
    @Builder
    @ApiModel("WctcSalesLimitsDto-SaveBlacklistReq")
    public record SaveBlacklistReq(
        @NotBlank
        String rowState,
        @NotBlank
        String sellLmId,
        String sellLmCntrNo,
        int sellLmCntrSn,
        String sellLmRsonCn,
        String dtaDlYn,
        String orglDtaDlYn,
        String fnlMdfcDtm
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 접수제한 관리-블랙리스트 Search Result Dto
    @ApiModel("WctcSalesLimitsDto-SearchBlacklistInfoRes")
    public record SearchBlacklistInfoRes(
        boolean isIndv,
        String sellLmId,
        String dtaDlYn,
        String orglDtaDlYn,
        String sellTpCd,
        String sellLmCntrNo,
        int sellLmCntrSn,
        String sellLmRsonCn,
        String cntrCstNo,
        String cstKnm,
        String bryyMmdd,
        String bzrno,
        String ogNm,
        String prtnrKnm,
        String prtnrNo,
        String fstRgstDtm,
        String fstRgstUsrId,
        String fnlMdfcDtm,
        String fnlMdfcUsrId,
        String orglFnlMdfcDtm
    ) {}

    @ApiModel("WctcSalesLimitsDto-SearchBlacklistRes")
    public record SearchBlacklistRes(
        boolean isIndv,
        String sellLmId,
        String dtaDlYn,
        String orglDtaDlYn,
        String sellTpCd,
        String sellLmCntrNo,
        int sellLmCntrSn,
        String sellLmRsonCn,
        String cntrCstNo,
        String cstKnm,
        String bryyMmdd,
        String bzrno,
        String cntrMpno,
        String cntrTno,
        String cntrZip,
        String cntrAdr,
        String istllKnm,
        String istllMpno,
        String istllTno,
        String istllZip,
        String istllAdr,
        String ogNm,
        String prtnrKnm,
        String prtnrNo,
        String prtnrMpno,
        String fstRgstDtm,
        String fstRgstUsrId,
        String fnlMdfcDtm,
        String fnlMdfcUsrId,
        String orglFnlMdfcDtm
    ) {}
}
