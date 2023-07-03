package com.kyowon.sms.wells.web.service.stock.dto;

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
        Integer qty1,
        String sding2,
        Integer qty2,
        String sding3,
        Integer qty3,
        String sding4,
        Integer qty4,
        String sding5,
        Integer qty5,
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
        String svBizHclSfCd,
        String svBizDclsfCd,
        int sppPlanSn
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

}
