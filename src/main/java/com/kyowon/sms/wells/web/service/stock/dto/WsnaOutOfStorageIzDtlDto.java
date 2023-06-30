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
        String ostrTpCd,
        String strTpCd,
        String ostrWareDvCd,
        String ostrWareDtlDvCd,
        String ostrWareNo,
        String strWareDvCd,
        String strWareDtlDvCd,
        String strWareNo,
        String itmPdCd,
        String itmGdCd,
        String itmKndCd,
        String useYn
    ) {}

    @ApiModel(value = "WsnaOutOfStorageIzDtlDto-SearchRes")
    public record SearchRes(
        String strWareNo,
        String ostrWareNo,
        String inWareNm,
        String inWareNmSub,
        String outWareNm,
        String inWareMngtPrtnrNo,
        String ostrDt,
        String sapMatCd,
        String itmPdCd,
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
        String itmOstrNo,
        String itmStrNo,
        String useYn
    ) {}
}
