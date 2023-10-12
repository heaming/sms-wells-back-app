package com.kyowon.sms.wells.web.service.stock.dto;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.sds.sflex.system.config.validation.validator.ValidDate;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

/**
 * <pre>
 * W-SV-U-0192M01 개인창고출고관리
 * </pre>
 *
 * @author songTaeSung
 * @since 2023.02.16
 */
public class WsnaIndividualWareOstrDto {

    @Builder
    @ApiModel("WsnaIndividualWareOstrDto-SearchWareReq")
    public record SearchWareReq(
        // 기준년월
        @NotBlank
        String apyYm,
        // 배정년월
        @NotBlank
        String asnOjYm,
        // 출고창고
        @NotBlank
        String ostrWareNo,
        // 창고구분
        @NotBlank
        String wareDvCd,
        // 창고상세구분
        @NotBlank
        String wareDtlDvCd,
        // 상위구분
        @NotBlank
        String hgrDvCd,
        // 회차
        @Positive
        int cnt,
        // 상위입고창고번호
        String hgrStrWareNo

    ) {}

    @Builder
    @ApiModel("WsnaIndividualWareOstrDto-SearchPdRes")
    public record SearchPdRes(
        // 품목코드
        String pdCd,
        // 품목명
        String pdNm,
        // 품목종류
        String itmKndCd
    ) {}

    @Builder
    @ApiModel("WsnaIndividualWareOstrDto-SearchReq")
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
        // 품목코드 리스트
        List<String> itmPdCds,
        // 확정 후 재고
        @Max(999999999999L)
        BigDecimal totOutQty,
        // 상위입고창고번호
        @NotBlank
        String hgrStrWareNo,
        // 입고창고
        String strWareNo,
        // 품목코드
        String itmPdCd,
        // 시작SAP코드
        String strtSapCd,
        // 종료SAP코드
        String endSapCd
    ) {}

    @Builder
    @ApiModel("WsnaIndividualWareOstrDto-SaveReq")
    public record SaveReq(
        // 품목코드
        @NotBlank
        String itmPdCd,
        // 관리단위

        String mngtUnit,
        // 자재등급
        String matGdCd,
        // 박스단위수량
        BigDecimal boxUnitQty,
        // 생성수량
        @Positive
        @Max(999999999999L)
        BigDecimal outQty,
        // 품목물량배정번호
        @NotBlank
        String itmQomAsnNo,
        // 배정년월
        @NotBlank
        String asnOjYm,
        // 출고창고
        @NotBlank
        String ostrWareNo,
        // 입고창고
        @NotBlank
        String strWareNo,
        // 창고관리파트너번호
        @NotBlank
        String wareMngtPrtnrNo,
        // 조직유형코드
        @NotBlank
        String ogTpCd,
        // 비고
        @Size(max = 4000)
        String rmkCn,
        // 배정회차수
        @Positive
        BigDecimal asnTnN,
        // 창고구분
        @NotBlank
        String wareDvCd,
        // 출고요청번호
        String ostrAkNo,
        // 출고요청일련번호
        @Positive
        Integer ostrAkSn,
        // 출고일자
        @NotBlank
        @ValidDate
        String ostrDt,
        // 출고창고구분
        String ostrWareDvCd,
        // 출고창고파트너번호
        String ostrPrtnrNo,
        // 출고창고파트너조직유형
        String ostrPrtnrOgTpCd

    ) {}

    @Builder
    @ApiModel("WsnaIndividualWareOstrDto-CreateReq")
    public record CreateReq(
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
        String wareDtlDvCd

    ) {}
}
