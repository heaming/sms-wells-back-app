package com.kyowon.sms.wells.web.service.stock.dto;

import java.math.BigDecimal;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

/**
 * <pre>
 * W-SV-U-0190M01, W-SV-U-0191M01 개인창고, 독립창고 물량배정 dto
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-07-11
 */

public class WsnaQomAsnDto {

    @Builder
    @ApiModel("WsnaQomAsnDto-SearchWareReq")
    public record SearchWareReq(
        // 기준년월
        @NotBlank
        String apyYm,
        // 배정년월
        @NotBlank
        String asnOjYm,
        // 창고구분
        @NotBlank
        String wareDvCd,
        // 창고상세구분
        @NotBlank
        String wareDtlDvCd
    ) {}

    @Builder
    @ApiModel("WsnaQomAsnDto-SearchReq")
    public record SearchReq(
        // 기준년월
        @NotBlank
        String apyYm,
        // 배정년월
        @NotBlank
        String asnOjYm,
        // 회차
        @Positive
        @Max(999999999999L)
        BigDecimal cnt,
        // 출고창고
        @NotBlank
        String ostrWareNo,
        // 품목종류
        String itmKndCd,
        // 품목코드
        String itmPdCd,
        // 창고구분
        @NotBlank
        String wareDvCd,
        // 창고상세구분
        @NotBlank
        String wareDtlDvCd,
        // 입고창고
        String strWareNo
    ) {}

    @Builder
    @ApiModel("WsnaQomAsnDto-SearchRes")
    public record SearchRes(
        // SAP코드
        String sapCd,
        // 품목코드
        String itmPdCd,
        // 품목명
        String itmPdNm,
        // 관리단위
        String mngtUnit,
        // 자재등급
        String matGdCd,
        // 입고창고번호
        String wareNo,
        // 사번
        String prtnrNo,
        // 파트너명
        String prtnrNm,
        // 입고창고
        String wareNm,
        // 센터창고수량
        BigDecimal centerQty,
        // 일반배정수량
        BigDecimal geQty,
        // 법인배정수량
        BigDecimal crpQty,
        // 총배정수량
        BigDecimal totalQty,
        // 가중치적용수량
        BigDecimal apyQty,
        // 누적출고수량
        BigDecimal ostrQty,
        // BS완료수량
        BigDecimal bsQty,
        // 현재재고수량
        BigDecimal stocQty,
        // 금주예정수량
        BigDecimal thwkQty,
        // 차주예정수량
        BigDecimal borrQty,
        // 확정수량
        BigDecimal cnfmQty,
        // 박스수량
        BigDecimal boxQty,
        // 당월BS FULL수량
        BigDecimal bsFullQty,
        // 당월BS배정수량
        BigDecimal bsAsnQty,
        // 재고(조직)수량
        BigDecimal stockOgQty,
        // 재고(개인)수량
        BigDecimal stockIndvQty,
        // 소요수량
        BigDecimal nedQty,
        // 박스단위수량
        BigDecimal boxUnitQty,
        // 빌딩코드
        String bldCd,
        // 빌딩명
        String bldNm,
        // 전화번호
        String telNo,
        // 우편번호
        String adrZip,
        // 주소1
        String rnadr,
        // 주소2
        String rdadr

    ) {}

    @Builder
    @ApiModel("WsnaQomAsnDto-CreateReq")
    public record CreateReq(
        // 기준년월
        @NotBlank
        String apyYm,
        // 배정년월
        @NotBlank
        String asnOjYm,
        // 회차
        @Positive
        @Max(999999999999L)
        BigDecimal cnt,
        // 출고창고
        @NotBlank
        String ostrWareNo,
        // 창고구분
        @NotBlank
        String wareDvCd,
        // 창고상세구분
        @NotBlank
        String wareDtlDvCd,

        // 재생성여부
        boolean isRecreate

    ) {}
    @Builder
    @ApiModel("WsnaQomAsnDto-EditReq")
    public record EditReq(
        // 기준년월
        @NotBlank
        String apyYm,
        // 배정년월
        @NotBlank
        String asnOjYm,
        // 출고창고
        @NotBlank
        String ostrWareNo

    ) {}

}
