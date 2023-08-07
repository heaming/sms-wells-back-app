package com.kyowon.sms.wells.web.service.allocate.dto;

import io.swagger.annotations.ApiModel;

/**
 *
 * <pre>
 * K-W-SV-U-0230M01 상품별 BS계정 현황
 * </pre>
 *
 * @author heymi.cho
 * @since 2023.08.07
 */
public class WsncBsAccountByProductDto {

    @ApiModel(value = "WsncBsAccountByProductDto-SearchReq")
    public record SearchReq(
        String baseYm,
        String pdGrpCd,
        String pdCd,
        String mgtDept,
        String rnglGrp,
        String branch
    ) {}

    @ApiModel(value = "WsncBsAccountByProductDto-SearchRes")
    public record SearchRes(
        String dgr1LevlOgCd,
        String dgr2LevlOgCd,
        String dgr3LevlOgCd,
        String dgr3LevlOgNm,
        String bldNm,
        String pdCd,
        String pdNm,
        String pdGrpCd,
        String pdGrpNm,
        Integer cnt
    ) {}
}
