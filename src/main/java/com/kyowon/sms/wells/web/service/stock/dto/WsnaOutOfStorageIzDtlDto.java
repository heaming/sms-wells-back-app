package com.kyowon.sms.wells.web.service.stock.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;

/**
 * <pre>
 * W-SV-U-0144M01 출고내역상세 관리
 * </pre>
 *
 * @author inho.choi
 * @since 2023-02-14
 */
public class WsnaOutOfStorageIzDtlDto {
    @ApiModel(value = "WsnaOutOfStorageIzDtlDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String apyYm,
        @NotBlank
        String stOstrDt,
        @NotBlank
        String edOstrDt,
        String ostrWareNo,
        String ostrTpCd,
        String strWareNo,
        String itmGdCd,
        String itmkndCd,
        String itmCd,
        String useYn
    ) {}

    @ApiModel(value = "WsnaOutOfStorageIzDtlDto-SearchRes")
    public record SearchRes(
        String strWareNo,
        String ostrWareNo,
        String inWareNm,
        String outWareNm,
        String wareMngtPrtnrNo,
        String ostrDt,
        String sapMatCd,
        String itmCd,
        String pdNm,
        String ostrTpCd,
        String mngtUnitCd,
        String itmGdCd,
        String ostrQty,
        String boxUnitQty,
        String boxQty,
        String didyDvCd,
        String strConfDt,
        String ostrAkNo,
        String ostrNo,
        String itmStrNo,
        String useYn
    ) {}
}
