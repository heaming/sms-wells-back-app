package com.kyowon.sms.wells.web.fee.calculation.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiModel;

/**
 * <pre>
 * 홈마스터 등급관리
 * </pre>
 *
 * @author gs.piit150
 * @since 2023.03.09
 */
public class WfebHomeMasterGradeDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // 홈마스터 등급관리 Search Request Dto
    @ApiModel(value = "WfebHomeMasterGradeDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String mngtYm,
        String emplNm,
        String prtnrNo
    ) {}

    // 홈마스터 등급관리 상세 Search Request Dto
    @ApiModel(value = "WfebHomeMasterGradeDto-SearchDetailReq")
    public record SearchDetailReq(
        @NotBlank
        String mngtYm,
        String emplNm,
        String prtnrNo
    ) {}

    // 홈마스터 등급 저장  Save Request Dto
    @ApiModel(value = "WfebHomeMasterGradeDto-SaveReq")
    public record SaveReq(
        String prtnrNo,
        String mngtYm,
        String clDvCd
    ) {}

    // 홈마스터 포인트 저장 Save Request Dto
    @ApiModel(value = "WfebHomeMasterGradeDto-SavePointReq")
    public record SavePointReq(
        String prtnrNo,
        String mngtYm,
        String sellPVal,
        String svPVal,
        String educPVal,
        String etcPVal1,
        String etcPVal2,
        String etcPVal3,
        String clDvCd
    ) {}

    // 홈마스터 등급 포인트 삭제  remove Request Dto
    @ApiModel(value = "WfebHomeMasterGradeDto-RemoveReq")
    public record RemoveReq(
        String prtnrNo,
        String mngtYm
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 홈마스터 등급관리 Search Result Dto
    @ApiModel(value = "WfebHomeMasterGradeDto-SearchRes")
    public record SearchRes(
        String mngtYm,
        String belong,
        String emplNm,
        String prtnrNo,
        String rgstYm,
        String brmgrEmplNm,
        String brmgrPrtnrNo,
        String clDvCd,
        String save,
        String pointHistory
    ) {}

    @ApiModel(value = "WfebHomeMasterGradeDto-SearchDetailRes")
    public record SearchDetailRes(
        String emplNm,
        String prtnrNo,
        String mngtYm,
        String sellPVal,
        String svPVal,
        String educPVal,
        String etcPVal1,
        String etcPVal2,
        String etcPVal3,
        String totSum,
        String clDvCd,
        String save,
        String newYn
    ) {}
}
