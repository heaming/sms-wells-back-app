package com.kyowon.sms.wells.web.service.stock.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;

import java.util.List;

/**
 *
 * <pre>
 * K-W-SV-U-0265M01 입고 집계
 * </pre>
 *
 * @author 37758 이재훈
 * @since 2023.08.07
 */
public class WsnaInAggregateDto {
    @ApiModel("WsnaInAggregateDto-SearchReq")
    public record SearchReq(
        String strTpCd,
        String pdCdFrom,
        String pdCdTo,
        String sapCdFrom,
        String sapCdTo,
        @NotBlank
        String baseDateFrom,
        @NotBlank
        String baseDateTo,
        @NotBlank
        String itmKndCd,
        String itmGrpCd,
        List<String> itmPdCds,
        String pdCd,
        String pdGdCd,
        String wareDvCd,
        String useYn
    ) {}
}
