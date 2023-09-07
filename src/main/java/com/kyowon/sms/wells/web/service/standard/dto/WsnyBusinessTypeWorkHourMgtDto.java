package com.kyowon.sms.wells.web.service.standard.dto;

import io.swagger.annotations.ApiModel;

/**
 * <pre>
 * 업무유형별작업시간관리
 * </pre>
 *
 * @author leeminwoo
 * @since 2023.07.24
 */
public class WsnyBusinessTypeWorkHourMgtDto {

    @ApiModel(value = "WsnyBusinessTypeWorkHourMgtDto-SearchReq")
    public record SearchReq(
        String svDvCd,
        String pdGrpCd,
        String pdCd,
        String svBizDclsfCd,
        String apyDt,
        String findGb

    ) {}

    @ApiModel(value = "WsnyBusinessTypeWorkHourMgtDto-SearchRes")
    public record SearchRes(

        String sapMatCd,
        String pdCd,
        String svBizDclsfCd,
        String izSn,
        String pdNm,
        String apyStrtdt,
        String apyEnddt,
        String wkGrpCd,
        String ldtm,
        String istEgerPsno,
        String ackmtCt

    ) {}

    @ApiModel(value = "WsnyBusinessTypeWorkHourMgtDto-SaveReq")
    public record SaveReq(

        String pdCd,
        String svBizDclsfCd,
        String izSn,
        String apyStrtdt,
        String apyEnddt,
        String wkGrpCd,
        String ldtm,
        String istEgerPsno,
        String ackmtCt
    ) {}

}
