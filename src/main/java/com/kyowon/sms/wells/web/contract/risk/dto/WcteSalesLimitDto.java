package com.kyowon.sms.wells.web.contract.risk.dto;

import javax.validation.constraints.NotBlank;

import com.sds.sflex.system.config.validation.validator.ValidDate;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WcteSalesLimitDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // wells 사업자 가입제한 대상 관리 Search Request Dto
    @Builder
    @ApiModel("WcteSalesLimitDto-SearchEntrpJLmOjReq")
    public record SearchEntrpJLmOjReq(

        String dlpnrNm, //상호명
        @NotBlank
        String sellLmBzrno, //사업자번호

        @ValidDate
        String sellLmOcStm, //시작일자
        @ValidDate
        String sellLmOcDtm //종료일자
    ) {}

    @Builder
    @ApiModel("WcteSalesLimitDto-SaveEntrpJLmOjReq")
    public record SaveEntrpJLmOjReq(
        @NotBlank
        String sellLmBzrno, //사업자번호
        @NotBlank
        String sellLmDv,
        @NotBlank
        String rowState, //Row의 상태
        String sellLmId,

        String sellLmRsonCd,
        String sellLmRsonCn,
        String sellLmRlsCn,

        String sellLmCntrSn,
        String rownum,
        String sellLmOcDtm,
        String sellLmRlsDtm,
        String sellRson,
        String sellLmPsic,
        String sellLmRlsPsic,
        String sellLmPsicNm,
        String sellLmRlsPsicNm,
        String dtaDlYn
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // wells 사업자 가입제한 대상 관리 Search Result Dto
    @ApiModel("WcteSalesLimitDto-SearchEntrpJLmOjRes")
    public record SearchEntrpJLmOjRes(
        String sellLmDv,
        String sellLmBzrno,
        String dlpnrNm,
        String dlgpsNm,
        String bryyMmdd,

        String rownum,

        String sellLmRsonCd,
        String sellLmOcDtm,
        String sellLmRlsDtm,
        String sellRson,
        String sellLmPsic,
        String sellLmRlsPsic,
        String sellLmPsicNm,
        String sellLmRlsPsicNm
    ) {}
}
