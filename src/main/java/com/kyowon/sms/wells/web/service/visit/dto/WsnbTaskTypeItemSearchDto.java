package com.kyowon.sms.wells.web.service.visit.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;

/**
 *
 * <pre>
 * K-W-SV-U-0075M01 업무유형별 품목 현황
 * </pre>
 *
 * @author 37758 이재훈
 * @since 2023.08.10
 */
public class WsnbTaskTypeItemSearchDto {

    @ApiModel(value = "WsnbTaskTypeItemSearchDto-SearchReq")
    public record SearchReq(
        String taskType,
        @NotBlank
        String inqrBase,
        @NotBlank
        String baseDateFrom,
        @NotBlank
        String baseDateTo,
        @NotBlank
        String wkSts,
        String istBase,
        String useYn
    ) {}

    @ApiModel(value = "WsnbTaskTypeItemSearchDto-SearchRes")
    public record SearchRes(
        String sapMatCd,
        String pdCd,
        String pdNm,
        String saleCd,
        int qty100002,
        int qty200000,
        int qty299999,
        int qty999999,
        int assignPerQty,
        int totalCount,
        int s71350,
        int s71352,
        int s71355,
        int s71356,
        int s71357,
        int s71360,
        int s71361,
        int s71363,
        int s71364,
        int s71365,
        int s71367,
        int s71359,
        int s71366,
        int s71387,
        int s71784,
        int s71917,
        int s71918,
        int s71919,
        int s71937,
        int s99020,
        int s99010
    ) {}
}
