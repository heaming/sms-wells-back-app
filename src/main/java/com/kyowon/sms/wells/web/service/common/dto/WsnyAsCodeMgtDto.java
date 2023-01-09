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

    @ApiModel(value = "WsnyAfterServiceCodeDto-SearchReq")
    public record SearchReq(
        String pdGrpCd,
        String pdCd,
        String svTpCd,
        String asLctCd,
        String applyDate,
        String apyChk,
        String asPhnCd
    ) {

    }

    @ApiModel(value = "WsnyAfterServiceCodeDto-SearchRes")
    public record SearchRes(
        String svTpCd,
        String asLctCd,
        String asLctNm,
        String asPhnCd,
        String asPhnNm,
        String asCausCd,
        String asCausNm,
        String siteAwAtcCd,
        String siteAwAtcNm,
        String fuleyAwAmt,
        String svAnaHclsfCd,
        String svAnaHclsfNm,
        String pdGrpCd,
        String pdCd,
        String siteAwAtcDsnDt,
        String svAnaMclsfCd,
        String svAnaLclsfCd,
        String svAnaDsnDt,
        String apyStrtdt,
        String apyEnddt,
        String dtaDlYn,
        String applyDate,
        String chk,
        String asCode,
        String chkYn,
        String fstRgstDtm,
        String flag,
        String cd,
        String cdNm
    ) {

    }
}
