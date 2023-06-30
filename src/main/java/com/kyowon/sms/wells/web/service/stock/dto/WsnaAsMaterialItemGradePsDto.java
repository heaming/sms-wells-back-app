package com.kyowon.sms.wells.web.service.stock.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WsnaAsMaterialItemGradePsDto {

    @Builder
    @ApiModel("WsnaAsMaterialItemGradePsDto-SearchWareReq")
    public record SearchWareReq(
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
        // 자재구분
        String matUtlzDvCd

    ) {}
}
