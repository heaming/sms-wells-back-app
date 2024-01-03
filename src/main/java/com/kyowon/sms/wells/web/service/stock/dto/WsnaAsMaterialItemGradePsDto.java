package com.kyowon.sms.wells.web.service.stock.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WsnaAsMaterialItemGradePsDto {

    @Builder
    @ApiModel("WsnaAsMaterialItemGradePsDto-SearchWareReq")
    public record SearchWareReq(
        // 기준년월
        @NotBlank
        String baseYm
    ) {}

    @Builder
    @ApiModel("WsnaAsMaterialItemGradePsDto-SearchReq")
    public record SearchReq(

        // 기준년월
        @NotBlank
        String baseYm,
        // 사용여부
        String useYn,
        // 중수리자재여부
        String commGb,
        // 기초자재여부
        String baseGb,
        // 회전율대상여부
        String turnoverGb

    ) {}
}
