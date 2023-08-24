package com.kyowon.sms.wells.web.service.stock.dto;

import javax.validation.constraints.NotBlank;

import com.sds.sflex.system.config.validation.validator.ValidDate;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

/**
 * <pre>
 * W-SV-U-0278M01 필터소요 현황(교체주기) dto
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-08-10
 */

public class WsnaFilterNeedsPsDto {

    @Builder
    @ApiModel("WsnaFilterNeedsPsDto-SearchReq")
    public record SearchReq(
        @NotBlank
        @ValidDate
        String strtDt,

        @NotBlank
        @ValidDate
        String endDt,

        @NotBlank
        String itmKndCd,
        String itmPdCd,
        String strtSapCd,
        String endSapCd,

        String b2bMngtCd
    ) {}

}
