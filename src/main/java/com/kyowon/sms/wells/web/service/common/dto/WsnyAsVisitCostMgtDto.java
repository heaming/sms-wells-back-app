package com.kyowon.sms.wells.web.service.common.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

/**
 *
 * <pre>
 * W-SV-U-0101M01 유상 AS 출장비 관리
 * </pre>
 *
 * @author gs.piit122 김동엽
 * @since 2022.11.18
 */
public class WsnyAsVisitCostMgtDto {

    @ApiModel(value = "WsnyAsVisitCostMgtDto-SearchReq")
    public record SearchReq(
        String pdCd,
        String izSn,
        String bstrCsAmt,
        String apyStrtdt,
        String apyEnddt,
        String rmkCn,
        String apyMtrChk
    ) {}

    @ApiModel(value = "WsnyAsVisitCostMgtDto-SearchRes")
    public record SearchRes(
        String pdCd,
        String izSn,
        String bstrCsAmt,
        String apyStrtdt,
        String apyEnddt,
        String rmkCn,
        String sapMatCd,
        String pdNm
    ) {}

    @Builder
    @ApiModel(value = "WsnyAsVisitCostMgtDto-SaveReq")
    public record SaveReq(
        @NotBlank
        String rowState,
        @NotBlank
        String pdCd,
        String izSn,
        @NotBlank
        String bstrCsAmt,
        @NotBlank
        String apyStrtdt,
        @NotBlank
        String apyEnddt,
        String rmkCn,
        String oldApyStrtdt,
        String oldApyEnddt,
        String oldRmkCn,
        String prevIzSn,
        String nextIzSn
    ) {}

}
