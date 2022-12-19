/*
 ****************************************************************************************************
 * 프로그램 개요
 ****************************************************************************************************
 1. 모듈 : SNY (기준정보)
 2. 프로그램 ID : W-SV-U-0016M01 AS 코드관리
 3. 작성자 : gs.piit122
 4. 작성일 : 2022.11.08
 ****************************************************************************************************
 * 프로그램 설명
 ****************************************************************************************************
 - AS코드관리 (http://localhost:3000/#/service/wwsny-after-service-code-mgt)
 ****************************************************************************************************
 */
package com.kyowon.sms.wells.web.service.common.dto;

import io.swagger.annotations.ApiModel;

public class WsnyAfterServiceCodeDto {

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
