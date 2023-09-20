package com.kyowon.sms.wells.web.service.visit.dto;

import io.swagger.annotations.ApiModel;

import javax.validation.constraints.NotBlank;

/**======================================
 *
 * <pre>
 * K-W-SV-U-0281M01 - 실적_설치1년누적AS율
 * </pre>
 *
 * @author gs.piit231
 * @since 2023.09.07
**======================================*/
public class WsnbInstallationYOneAcuAfSvRtDto {
    @ApiModel(value = "WsnbInstallationYOneAcuAfSvRtDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String baseY,
        String svType,
        String badDivide,
        String pdGrp,
        String pdCd
    ) {}

    @ApiModel(value = "WsnbInstallationYOneAcuAfSvRtDto-SearchRes")
    public record SearchRes(
        String nm,
        String m01,
        String m02,
        String m03,
        String m04,
        String m05,
        String m06,
        String m07,
        String m08,
        String m09,
        String m10,
        String m11,
        String m12,
        String tcnt,
        String maxVal,
        String minVal,
        String avgVal
    ) {}
}
