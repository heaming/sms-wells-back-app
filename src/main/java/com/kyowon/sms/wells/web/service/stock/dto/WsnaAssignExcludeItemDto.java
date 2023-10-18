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
        // 배정제외구분코드
        @NotBlank
        String asnExcdDvCd,
        // 품목종류코드
        String itmKndCd,
        // 창고번호
        String wareNo,
        // 품목상품코드
        String itmPdCd,
        // 시작SAP코드
        String strtSapCd,
        // 종료SAP코드
        String endSapCd
    ) {}

    @ApiModel(value = "WsnaAssignExcludeItemDto-SearchRes")
    public record SearchRes(
        // row 체크여부
        String chk,
        // 배정제외구분코드
        String asnExcdDvCd,
        // 배정제외구분
        String asnExcdDvNm,
        // 품목코드
        String itmPdCd,
        // 품목명
        String itmPdNm,
        // 품목종류코드
        String itmKndCd,
        // 품목종류
        String itmKndNm,
        // 입고창고번호
        String strWareNo,
        // SAP코드
        String sapMatCd,
        // row 체크여부 - 삭제체크용도
        String orgChk
    ) {}

    @ApiModel(value = "WsnaAssignExcludeItemDto-RemoveReq")
    public record RemoveReq(
        // 배정제외구분코드
        @NotBlank
        String asnExcdDvCd,
        // 입고창고번호
        @NotBlank
        String strWareNo,
        // 품목상품코드
        @NotBlank
        String itmPdCd
    ) {}

    @ApiModel(value = "WsnaAssignExcludeItemDto-CreateReq")
    public record CreateReq(
        // 배정제외구분코드
        @NotBlank
        String asnExcdDvCd,
        // 입고창고번호
        @NotBlank
        String strWareNo,
        // 품목상품코드
        @NotBlank
        String itmPdCd,
        // 품목종류코드
        String itmKndCd
    ) {}
}
