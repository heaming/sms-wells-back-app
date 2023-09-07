package com.kyowon.sms.wells.web.service.visit.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;

/**
 *
 * <pre>
 * K-W-SV-U-0100M01 A/S 재방문율 조회
 * </pre>
 *
 * @author 37758 이재훈
 * @since 2023.07.28
 */
public class WsnbAsRevisitRateListDto {
    @ApiModel(value = "WsnbAsRevisitRateListDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String baseDateFrom,
        @NotBlank
        String baseDateTo,

        String ogId
    ) {}

    @ApiModel(value = "WsnbAsRevisitRateListDto-SearchRes")
    public record SearchRes(
        String ogNm,
        String prtnrNo,
        String prtnrNm,
        String dupCnt,
        String asCnt,
        String dupPer,
        String score
    ) {}
}
