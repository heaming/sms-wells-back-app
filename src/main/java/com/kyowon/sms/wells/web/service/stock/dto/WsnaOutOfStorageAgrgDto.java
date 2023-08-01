package com.kyowon.sms.wells.web.service.stock.dto;

import io.swagger.annotations.ApiModel;

/**
 * <pre>
 *  출고 집계 현황
 * </pre>
 *
 * @author junggheejin
 * @since 2023.07.13
 */
public class WsnaOutOfStorageAgrgDto {

    @ApiModel(value = "WsnaOutOfStorageAgrgDto-SearchReq")
    public record SearchReq(
        String startDt,
        String endDt,
        String ostrTpCd,
        String sapMatCdFrom,
        String sapMatCdTo,
        String itmCdFrom,
        String itmCdTo,
        String itmGdCd,
        String itmKndCd,
        String itmPdCd,
        String matUtlzDvCd,
        String useYn
    ) {}
    @ApiModel(value = "WsnaOutOfStorageAgrgDto-FindItemRes")
    public record FindItemRes(
        String codeId,
        String codeName
    ) {}
}
