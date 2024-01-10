package com.kyowon.sms.wells.web.service.common.dto;

import io.swagger.annotations.ApiModel;

/**
 *
 * <pre>
 * W-SV-U-0016M01 AS 코드관리
 * </pre>
 *
 * @author gs.piit122 김동엽
 * @since 2022-11-08
 */
public class WsnyAsCodeMgtDto {

    @ApiModel(value = "WsnyAsCodeMgtDto-SearchReq")
    public record SearchReq(
        String pdGrpCd,
        String pdCd,
        String svTpCd,
        String asLctCd,
        String applyDate,
        String apyChk,
        String asPhnCd,
        String siteAwAtcCd
    ) {}

}
