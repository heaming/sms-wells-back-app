package com.kyowon.sms.wells.closing.expense.dto;

import io.swagger.annotations.ApiModel;

import javax.validation.constraints.NotBlank;

public class WOpcsRgstMngtDto {

    @ApiModel(value = "WOpcsRgstMngtDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String useYearMonth,
        String registration
    ) {
    }
}
