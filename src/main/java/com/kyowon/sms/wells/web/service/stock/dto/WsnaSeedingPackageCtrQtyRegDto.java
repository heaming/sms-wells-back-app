package com.kyowon.sms.wells.web.service.stock.dto;

import java.math.BigDecimal;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

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
        // 서비스센터
        @NotBlank
        String dgGgLctCd,
        // 출고일자
        @NotBlank
        @ValidDate
        String ostrDt
    ) {}

    @Builder
    @ApiModel("WsnaSeedingPackageCtrQtyRegDto-SearchRes")
    public record SearchRes(
        // 품목명
        String sdingPkgNm,
        // 품목코드
        String sdingPkgCd,
        // 출고수량
        BigDecimal ostrQty,
        // 제외수량
        BigDecimal excdQty,
        // 추가수량
        BigDecimal spmtQty

    ) {}

    @Builder
    @ApiModel("WsnaSeedingPackageCtrQtyRegDto-SaveReq")
    public record SaveReq(
        // 서비스센터
        @NotBlank
        String dgGgLctCd,
        // 품목코드
        @NotBlank
        String sdingPkgCd,
        // 출고예정일자
        @NotBlank
        @ValidDate
        String ostrDuedt,
        // 제외수량
        @Min(0)
        @Max(999999999999L)
        BigDecimal excdQty,
        // 추가수량
        @Min(0)
        @Max(999999999999L)
        BigDecimal spmtQty
    ) {}

}
