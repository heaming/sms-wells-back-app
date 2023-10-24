package com.kyowon.sms.wells.web.service.visit.dto;

import io.swagger.annotations.ApiModel;

import javax.validation.constraints.NotBlank;

/**======================================
 *
 * <pre>
 * K-W-SV-U-0243M01    실적_총A/S율-ODM/OEM
 * </pre>
 *
 * @author gs.piit231
 * @since 2023.09.06
**======================================*/
public class WsnbTotalAfterServiceRateOdmPerOemDto {
    @ApiModel(value = "WsnbTotalAfterServiceRateOdmPerOemDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String baseY,
        String svType,
        String badDivide,
        String pdGrp,
        String pdGrpGubun,
        String pdCd
    ) {}

    @ApiModel(value = "WsnbTotalAfterServiceRateOdmPerOemDto-SearchRes")
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
        Integer tcnt
    ) {}
}
