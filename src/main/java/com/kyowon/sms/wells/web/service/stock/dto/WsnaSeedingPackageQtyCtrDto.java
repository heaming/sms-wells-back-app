package com.kyowon.sms.wells.web.service.stock.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;

import com.sds.sflex.system.config.validation.validator.ValidDate;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WsnaSeedingPackageQtyCtrDto {

    @Builder
    @ApiModel("WsnaSeedingPackageQtyCtrDto-SearchReq")
    public record SearchReq(
        @NotBlank
        @ValidDate
        String ostrDt
    ) {}

    @Builder
    @ApiModel("WsnaSeedingPackageQtyCtrDto-SearchRes")
    public record SearchRes(
        String dgGgLctCd,
        String dgGgLctNm,

        BigDecimal type1,
        BigDecimal type2,
        BigDecimal type3,
        BigDecimal type4,
        BigDecimal type5,
        BigDecimal type6,
        BigDecimal type7,
        BigDecimal type8,
        BigDecimal type9,
        BigDecimal type10,
        BigDecimal type11,
        BigDecimal type12,
        BigDecimal type13,
        BigDecimal type14,
        BigDecimal type15,
        BigDecimal type16,
        BigDecimal type17,
        BigDecimal type18,
        BigDecimal type19,
        BigDecimal type20,
        BigDecimal type21,
        BigDecimal type22,
        BigDecimal type23,
        BigDecimal type24,
        BigDecimal type25,
        BigDecimal type26,
        BigDecimal type27,
        BigDecimal type28,
        BigDecimal type29,
        BigDecimal type30,
        BigDecimal type31,
        BigDecimal type32,
        BigDecimal type33,
        BigDecimal type34,
        BigDecimal type35,
        BigDecimal type36,
        BigDecimal type37,
        BigDecimal type38,
        BigDecimal type39,
        BigDecimal type40,
        BigDecimal type41,
        BigDecimal type42

    ) {}

}
