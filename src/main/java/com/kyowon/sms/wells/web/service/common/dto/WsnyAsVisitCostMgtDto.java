package com.kyowon.sms.wells.web.service.common.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;

import com.sds.sflex.system.config.validation.validator.ValidDate;

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
        String pdGrpCd, /* 상품그룹 */
        String pdCd, /* 상품코드 */
        String izSn,
        String bstrCsAmt,
        String apyStrtdt,
        String apyEnddt,
        String rmkCn,
        String apyMtrChk
    ) {}

    @Builder
    @ApiModel(value = "WsnyAsVisitCostMgtDto-RemoveReq")
    public record RemoveReq(
        @NotBlank
        String pdCd,
        int izSn
    ) {}

    @Builder
    @ApiModel(value = "WsnyAsVisitCostMgtDto-SaveReq")
    public record SaveReq(
        @NotBlank
        String rowState,
        @NotBlank
        String pdCd,
        Integer izSn,
        BigDecimal bstrCsAmt,
        @NotBlank
        @ValidDate
        String apyStrtdt,
        @NotBlank
        @ValidDate
        String apyEnddt,
        String rmkCn
    ) {}

}
