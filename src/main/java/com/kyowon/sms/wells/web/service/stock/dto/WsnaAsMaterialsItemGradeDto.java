package com.kyowon.sms.wells.web.service.stock.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

/**
 * <pre>
 * W-SV-U-0115M01 AS자재 품목등급관리 dto
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-06-15
 */

public class WsnaAsMaterialsItemGradeDto {

    @Builder
    @ApiModel("WsnaAsMaterialsItemGradeDto-SearchReq")
    public record SearchReq(

        // 기준년월
        @NotBlank
        String baseYm,

        // 창고구분코드
        @NotBlank
        String wareDvCd,

        // 창고세부구분코드
        String wareDtlDvCd,

        // 창고번호
        String wareNo,

        // 품목관리등급
        String itmMngtGdCd,

        // 사용여부
        String useYn,

        // 품목구분
        @NotBlank
        String itmKndCd,

        // 품목코드
        String itmPdCd,
        // 자재구분
        String matUtlzDvCd

    ) {}

    @Builder
    @ApiModel("WsnaAsMaterialsItemGradeDto-SearchRes")
    public record SearchRes(

        // SAP코드
        String sapCd,
        // 품목상품코드
        String itmPdCd,
        // 상품명
        String itmPdNm,

        // 직전 3개월 출고량 합계
        BigDecimal jbfMms3OstrQty,
        // 월 평균
        BigDecimal mlmnOstrQty,
        // 일 평균
        BigDecimal dAvOstrQty,

        // 품목관리등급
        String itmMngtGdCd,
        // 조정관리등급
        String ctrItmMngtGdCd,
        // 비고내용 - 조정사유
        String rmkCn,
        // 관리년월
        String mngtYm

    ) {}

    @Builder
    @ApiModel("WsnaAsMaterialsItemGradeDto-CreateReq")
    public record CreateReq(
        // 기준년월
        @NotBlank
        String baseYm,
        // 품목종류코드
        @NotBlank
        String itmKndCd
    ) {}

    @Builder
    @ApiModel("WsnaAsMaterialsItemGradeDto-SaveReq")
    public record SaveReq(
        // 관리년월
        @NotBlank
        String mngtYm,
        // 품목상품코드
        @NotBlank
        String itmPdCd,
        // 조정품목관리등급코드
        @NotBlank
        String ctrItmMngtGdCd,
        // 비고내용 - 조정사유
        String rmkCn
    ) {}

}
