package com.kyowon.sms.wells.web.service.stock.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

/**
 * <pre>
 *  K-W-SV-U-0176P01 입고 미승인상세현황
 * </pre>
 *
 * @author heymi.cho
 * @since 2023.06.13
 */

public class WsnaStoreNaprvStateDtlDto {

    @ApiModel(value = "WsnaStoreNaprvStateDtlDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String strWareNo,
        @NotBlank
        String itmPdCd,
        String strTpCd, /* 입고유형 */
        String startDate,
        String endDate,
        String ostrWareDvCd /* 출고창고구분 */
    ) {}

    @ApiModel(value = "WsnaStoreNaprvStateDtlDto-SearchRes")
    public record SearchRes(
        String strRgstDt, // 입고등록일자
        String strWareNm, // 입고유형
        String strTpCd, // 입고창고이름
        String itmStrNo, // 품목입고번호
        Integer strSn, // 입고일련번호
        String itmOstrNo, // 품목출고번호
        Integer ostrSn, // 출고일련번호
        String ostrWareNm, // 출고창고이름
        Integer strQty, // 입고수량
        String itmGdCd, // 품목등급코드
        String itmPdCd // 품목상품코드
    ) {}

    @ApiModel(value = "WsanStoreNaprvStateDtlDto-SaveReq")
    @Builder
    public record SaveReq(
        @NotBlank
        String strWareNo,
        @NotBlank
        String itmPdCd,
        @NotBlank
        String itmOstrNo, // 품목출고번호
        @NotNull
        Integer ostrSn, // 출고일련번호
        @NotBlank
        String itmStrNo, // 품목입고번호
        @NotNull
        Integer strSn, // 입고일련번호
        @NotBlank
        String itmGdCd,
        @NotNull
        Integer strQty, // 입고수량
        String userId //사용자ID
    ) {}
}
