package com.kyowon.sms.wells.web.service.stock.dto;

import java.math.BigDecimal;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import com.sds.sflex.system.config.validation.validator.ValidDate;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

/**
 * <pre>
 * W-SV-U-0298P01 모종패키지 조정수량 등록 dto
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-07-25
 */
public class WsnaSeedingPackageCtrQtyRegDto {

    @Builder
    @ApiModel("WsnaSeedingPackageCtrQtyRegDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String dgGgLctCd,
        @NotBlank
        @ValidDate
        String ostrDt
    ) {}

    @Builder
    @ApiModel("WsnaSeedingPackageCtrQtyRegDto-SearchRes")
    public record SearchRes(

        String sdingPkgNm,
        String sdingPkgCd,
        BigDecimal ostrQty,
        BigDecimal excdQty,
        BigDecimal spmtQty

    ) {}

    @Builder
    @ApiModel("WsnaSeedingPackageCtrQtyRegDto-SaveReq")
    public record SaveReq(
        @NotBlank
        String dgGgLctCd,
        @NotBlank
        String sdingPkgCd,
        @NotBlank
        @ValidDate
        String ostrDuedt,
        @Positive
        @Max(999999999999L)
        BigDecimal excdQty,
        @Positive
        @Max(999999999999L)
        BigDecimal spmtQty
    ) {}

}
