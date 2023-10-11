package com.kyowon.sms.wells.web.service.visit.dto;

import io.swagger.annotations.ApiModel;

import javax.validation.constraints.NotBlank;

public class WsnbPerformanceNewPdctMThreeOdmOemDto {
    @ApiModel(value = "WsnbPerformanceNewPdctMThreeOdmOemDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String baseY,
        String serviceTypes,
        String badDvCd,
        String pdGrpCd,
        String pdCd
    ) {}

    @ApiModel(value = "WsnbPerformanceNewPdctMThreeOdmOemDto-SearchRes")
    public record SearchRes(
        String nm,
        Integer acol1,
        Integer acol2,
        Integer acol3,
        Integer acol4,
        Integer acol5,
        Integer acol6,
        Integer acol7,
        Integer acol8,
        Integer acol9,
        Integer acol10,
        Integer acol11,
        Integer acol12,
        Integer maxval,
        Integer minval,
        Integer tcnt,
        Integer avg
    ) {}
}
