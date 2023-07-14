package com.kyowon.sms.wells.web.service.stock.dto;

import javax.validation.constraints.NotBlank;

import com.sds.sflex.system.config.validation.validator.ValidDate;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

/**
 * <pre>
 * W-SV-U-0130M01 모종 파종 예정리스트 조회 dto
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-07-06
 */

public class WsnaSeedingSowPlanDto {

    @Builder
    @ApiModel("WsnaSeedingSowPlanDto-SearchReq")
    public record SearchReq(

        @NotBlank
        @ValidDate
        String strtDt,
        @NotBlank
        @ValidDate
        String endDt,
        String cntrDtlNo
    ) {}

    @Builder
    @ApiModel("WsnaSeedingSowPlanDto-SearchRes")
    public record SearchRes(
        String baseCntrDtlNo,
        String baseCstNm,
        String basePdNm,
        String connCntrDtlNo,
        String connSdingPkgNm,
        String connSdingPdNm,
        Integer connQty,
        String vstDueDt,
        String sowDt,
        String cntrNo,
        int cntrSn
    ) {}

}
