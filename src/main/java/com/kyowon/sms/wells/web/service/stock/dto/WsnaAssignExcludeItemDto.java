package com.kyowon.sms.wells.web.service.stock.dto;

import javax.validation.constraints.NotBlank;

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
        @NotBlank
        String asnExcdDvCd,
        String itmKndCd,
        String wareNo
    ) {}

    @ApiModel(value = "WsnaAssignExcludeItemDto-SearchRes")
    public record SearchRes(
        String chk,
        String asnExcdDvCd,
        String asnExcdDvNm,
        String itmPdCd,
        String itmPdNm,
        String itmKndCd,
        String itmKndNm,
        String strWareNo,
        String sapMatCd,
        String orgChk
    ) {}

    @ApiModel(value = "WsnaAssignExcludeItemDto-RemoveReq")
    public record RemoveReq(
        @NotBlank
        String asnExcdDvCd,
        @NotBlank
        String strWareNo,
        @NotBlank
        String itmPdCd
    ) {}

    @ApiModel(value = "WsnaAssignExcludeItemDto-CreateReq")
    public record CreateReq(
        @NotBlank
        String asnExcdDvCd,
        @NotBlank
        String strWareNo,
        @NotBlank
        String itmPdCd,
        String itmKndCd
    ) {}
}
