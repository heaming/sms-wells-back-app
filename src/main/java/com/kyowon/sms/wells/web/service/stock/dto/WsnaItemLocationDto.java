package com.kyowon.sms.wells.web.service.stock.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;

/**
 * <pre>
 * W-SV-U-0210P01 품목위치관리
 * </pre>
 *
 * @author inho.choi
 * @since 2023.04.27
 */
public class WsnaItemLocationDto {
    @ApiModel("WsnaItemLocationDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String itmPdCd,
        @NotBlank
        String wareNo

    ) {}
    @ApiModel("WsnaItemLocationDto-SearchRes")
    public record SearchRes(
        String itmPdCd,
        String pdAbbrNm,
        String sapMatCd,
        String itmKndCd,
        String wareNo,
        String wareNm,
        String stdWareUseYn,
        String pitmStocAGdQty,
        String itmLctAngleVal,
        String itmLctCofVal,
        String itmLctFlorNoVal,
        String itmLctMatGrpCd,
        String itmLctNm
    ) {}
    @ApiModel("WsnaItemLocationDto-CreateReq")
    public record CreateReq(
        @NotBlank
        String wareNo,
        @NotBlank
        String itmPdCd,
        String itmLctAngleVal,
        String itmLctCofVal,
        String itmLctFlorNoVal,
        String itmLctMatGrpCd,
        String itmKndCd

    ) {}
}
