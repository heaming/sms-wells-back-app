package com.kyowon.sms.wells.web.service.stock.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import com.sds.sflex.system.config.validation.validator.ValidDate;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

/**
 * <pre>
 * W-SV-U-0128M01 모종 출고 예정리스트 dto
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-07-03
 */

public class WsnaSeedReleaseScheduleDto {

    @Builder
    @ApiModel("WsnaSeedReleaseScheduleDto-SearchReq")
    public record SearchReq(
        String svBizHclsfCd,
        @NotBlank
        String dtTpCd,
        String dayOfWeek,
        @NotBlank
        @ValidDate
        String strtDt,
        @NotBlank
        @ValidDate
        String endDt,

        String refriDivCd,
        String sppDvCd,
        String fshProcsCd,
        String pkgDvCd,
        String ostrYn
    ) {}

    @Builder
    @ApiModel("WsnaSeedReleaseScheduleDto-SearchRes")
    public record SearchRes(
        String dpYn,
        String ostrYn,
        String refriDiv,
        String shipDiv,
        String receiptDiv,
        String cntrNo,
        String cstNm,
        String sppOrdNo,
        String mchnModel,
        String mchnCstNo,
        String mchnCstNm,
        String ctrlPkg,
        String shipPkg,
        String sding1,
        BigDecimal qty1,
        String sding2,
        BigDecimal qty2,
        String sding3,
        BigDecimal qty3,
        String sding4,
        BigDecimal qty4,
        String sding5,
        BigDecimal qty5,
        String mchnDemDt,
        String receiptDt,
        String vstDt,
        String ostrScheDt,
        String bsFshDt,
        String dpDt,
        String ostrCnfmDt,
        String sdingRcgWareNm,
        String vstCeter,
        String vstIchr,
        String ichrCtnt,
        String cstCtnt,
        String cstZip,
        String cstAdr,
        String refriDvCd,
        int cntrSn,
        String svBizHclsfCd,
        String svBizDclsfCd,
        int sppPlanSn,
        String sdingPdCd1,
        String sowDt1,
        String sdingPdCd2,
        String sowDt2,
        String sdingPdCd3,
        String sowDt3,
        String sdingPdCd4,
        String sowDt4,
        String sdingPdCd5,
        String sowDt5,
        String sdingPkgPdCd,
        String mngrDvCd,
        String dpEpttNm,
        String ogTpCd,
        String prtnrNo,
        String cntrAdrpcId,
        BigDecimal recapCsAmt,
        String sdingMcnrPdCd,
        String sppDvCd,
        String cstSvAsnNo,
        String cntrDtlNo,
        String mchnCstDtlNo
    ) {}

    @Builder
    @ApiModel("WsnaSeedReleaseScheduleDto-EditReq")
    public record EditReq(

        @NotBlank
        String cntrNo,
        @Positive
        int cntrSn,
        @NotBlank
        String svBizHclsfCd,
        @NotBlank
        String svBizDclsfCd,
        @NotBlank
        String sppOrdNo,
        @Positive
        int sppPlanSn,
        @NotBlank
        @ValidDate
        String dpDt

    ) {}

    @Builder
    @ApiModel("WsnaSeedReleaseScheduleDto-CreateReq")
    public record CreateReq(

        @NotBlank
        String cntrNo,
        @Positive
        int cntrSn,
        @NotBlank
        String svBizHclsfCd,
        @NotBlank
        String svBizDclsfCd,
        @NotBlank
        String sppOrdNo,
        @Positive
        int sppPlanSn,

        String cstNm,
        String mchnCstNo,
        String sdingMcnrPdCd,
        String sdingPkgPdCd,

        String sdingPdCd1,
        BigDecimal qty1,
        String sowDt1,
        String sdingPdCd2,
        BigDecimal qty2,
        String sowDt2,
        String sdingPdCd3,
        BigDecimal qty3,
        String sowDt3,
        String sdingPdCd4,
        BigDecimal qty4,
        String sowDt4,
        String sdingPdCd5,
        BigDecimal qty5,
        String sowDt5,

        @ValidDate
        String receiptDt,
        @ValidDate
        String vstDt,
        @ValidDate
        String ostrScheDt,
        @ValidDate
        String ostrCnfmDt,

        String mngrDvCd,
        String ogTpCd,
        String prtnrNo,
        String cntrAdrpcId,
        String refriDvCd,
        BigDecimal recapCsAmt,
        String dpEpttNm,
        String dpDt,
        String sppDvCd,
        String cstSvAsnNo
    ) {}

}
