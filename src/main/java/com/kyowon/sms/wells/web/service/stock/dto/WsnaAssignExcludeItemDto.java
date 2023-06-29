package com.kyowon.sms.wells.web.service.stock.dto;

import io.swagger.annotations.ApiModel;

/**
 * <pre>
 * W-SV-U-0189P01 배정제외품목 등록
 * </pre>
 *
 * @author inho.choi
 * @since 2023-04-20
 */
public class WsnaAssignExcludeItemDto {

    @ApiModel(value = "WsnaAssignExcludeItemDto-SearchReq")
    public record SearchReq(
        String asnExcdDvCd,
        String itmKndCd,
        String wareNo,
        String baseYm
    ) {}

    @ApiModel(value = "WsnaAssignExcludeItemDto-SearchRes")
    public record SearchRes(
        String chk,
        String asnExcdDvCd,
        String itmPdCd,
        String itmPdNm,
        String itmKndCd,
        String strWareNo,
        String strWareNm,
        String flag,
        String sapMaptCd,
        String knd
    ) {}

    @ApiModel(value = "WsnaAssignExcludeItemDto-WareRes")
    public record WareRes(
      String codeId,
      String codeName
    ){}
    @ApiModel(value = "WsnaAssignExcludeItemDto-SaveReq")
    public record SaveReq(
        String asnExcdDvCd,
        String strWareNo,
        String itmPdCd,
        String itmKndCd
    ) {}
}
