package com.kyowon.sms.wells.web.deduction.adsb.dto;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WdebAwAdsbMgtDto {

    @Builder
    @ApiModel("WdebAwAdsbMgtDto-CreateReq")
    public record CreateReq(
        String baseYm, // 반영년월
        String ogTpCd, // 조직유형
        String lifeYn // 상조 여부
    ) {}

}
