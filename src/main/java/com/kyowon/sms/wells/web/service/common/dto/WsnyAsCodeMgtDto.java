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
    ) {

    }

    @ApiModel(value = "WsnyAsCodeMgtDto-SearchRes")
    public record SearchRes(

        String pdGrpCd,
        String pdCd,
        String svDvCd,
        String asLctCd,
        String asPhnCd,
        String asCausCd,
        String siteAwAtcDsnDt,
        String svAnaHclsfCd,
        String siteAwAtcCd,
        String fuleyAwAmt,
        String svAnaMclsfCd,
        String svAnaLclsfCd,
        String svAnaDsnDt,
        String apyStrtdt,
        String apyEnddt,


        String svTpCd,
        String svTpNm,
        String asLctNm,
        String asPhnNm,
        String asCausNm,
        String siteAwAtcNm,
        String svAnaHclsfNm,
        String dtaDlYn,
        String applyDate,
        String chk,
        String asCode,
        String chkYn,
        String fstRgstDtm,
        String flag,
        String cd,
        String cdNm,
        String pdNm,
        String pdGrpNm
    ) {

    }
}
