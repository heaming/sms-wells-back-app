package com.kyowon.sms.wells.web.competence.evaluate.dto;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

import javax.validation.constraints.NotBlank;

public class WpsdExcellentDivisionBaseMgtDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // 우수사업부 기준관리 - 상품기준 Search Request Dto
    @Builder
    @ApiModel("WpsdExcellentDivisionBaseMgtDto-PdSearchReq")
    public record PdSearchReq(
        @NotBlank
        String baseYm,
        @NotBlank
        String evlPdDvCd,
        @NotBlank
        String evlOgTpCd
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 우수사업부 기준관리 - 상품기준 Search Result Dto
    @ApiModel("WpsdExcellentDivisionBaseMgtDto-PdSearchRes")
    public record PdSearchRes(
        String baseYm, /* 기준년월 */
        String evlOgTpCd, /* 평가조직유형코드 */
        String evlPdDvCd, /* 평가상품구분코드 */
        String pdCd, /* 상품코드 */
        String cvtPc, /* 환산점수 */
        String dtaDlYn /* 데이터삭제여부 */
    ) {}

    // *********************************************************
    // Reqeust Dto
    // *********************************************************
    // 우수사업부 기준관리 - 상품기준 Save Reqeust Dto
    @ApiModel("WpsdExcellentDivisionBaseMgtDto-PdSaveReq")
    public record PdSaveReq(
        String baseYm, /* 기준년월 */
        String evlOgTpCd, /* 평가조직유형코드 */
        String evlPdDvCd, /* 평가상품구분코드 */
        String pdCd, /* 상품코드 */
        Long cvtPc, /* 환산점수 */
        String rowState
    ) {

    }

    // *********************************************************
    // Request Dto
    // *********************************************************
    // 우수사업부 기준관리 - 평가기준 Search Request Dto
    @Builder
    @ApiModel("WpsdExcellentDivisionBaseMgtDto-EvlSearchReq")
    public record EvlSearchReq(
        @NotBlank
        String baseYm,
        @NotBlank
        String evlOgTpCd,
        String evlDvCd
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 우수사업부 기준관리 - 평가기준 Search Result Dto
    @ApiModel("WpsdExcellentDivisionBaseMgtDto-EvlSearchRes")
    public record EvlSearchRes(
        String baseYm,
        String evlOgTpCd,
        String evlOgTpNm,
        String evlDvCd,
        String evlDvNm,
        String evlRsbRelId,
        String ctstGrpUseYn,
        String evlAtcCn,
        String dtaDlYn,
        String rsbDvCds,
        String rsbDvNms,
        String qlfDvNm,
        String qlfDvCd
    ) {}

    // *********************************************************
    // Request Dto
    // *********************************************************
    // 우수사업부 기준관리 - 평가기준 Save Request Dto
    @ApiModel("WpsdExcellentDivisionBaseMgtDto-EvlSaveReq")
    public record EvlSaveReq(
        String baseYm,
        String evlOgTpCd,
        String evlOgTpNm,
        String evlDvCd,
        String evlDvNm,
        String evlRsbRelId,
        String ctstGrpUseYn,
        String evlAtcCn,
        String dtaDlYn,
        String calfCn,
        String cexpCn,
        String rsbDvCds,
        String rsbDvNms,
        String[] rsbDvCdList,
        String qlfDvNm,
        String qlfDvCd,

        String rowState
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 우수사업부 기준관리 - 평가기준 Search Result Dto
    @ApiModel("WpsdExcellentDivisionBaseMgtDto-EvlDetailSearchRes")
    public record EvlDetailSearchRes(
        String baseYm, /* 기준년월 */
        String evlOgTpCd, /* 평가조직유형코드 */
        String evlDvCd, /* 평가구분코드 */
        String evlDvNm, /* 평가구분코드 */
        String evlAtcDvCd, /* 평가항목구분코드 */
        String evlPdDvCd, /* 평가상품구분코드 */
        Integer evlBaseUnitN, /* 평가기준단위수 */
        Long cvtBasePc, /* 환산기준점수 */
        Long trgBasePc, /* 목표기준점수 */
        String dtaDlYn /* 데이터삭제여부 */
    ) {}

    // *********************************************************
    // Request Dto
    // *********************************************************
    // 우수사업부 기준관리 - 상세 평가기준 Save Request Dto
    @ApiModel("WpsdExcellentDivisionBaseMgtDto-EvlDetailSaveReq")
    public record EvlDetailSaveReq(
        String baseYm, /* 기준년월 */
        String evlOgTpCd, /* 평가조직유형코드 */
        String evlDvCd, /* 평가구분코드 */
        String evlAtcDvCd, /* 평가항목구분코드 */
        String evlPdDvCd, /* 평가상품구분코드 */
        double evlBaseUnitN, /* 평가기준단위수 */
        Long cvtBasePc, /* 환산기준점수 */
        Long trgBasePc, /* 목표기준점수 */
        String dtaDlYn,
        String rowState
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 우수사업부 기준관리 - 평가기준항목 Search Result Dto
    public record EvlArticlesSearchRes(
        String evlOgTpCd, /* 평가조직유형코드 */
        String evlAtcDvCd, /* 평가항목구분코드 */
        String evlAtcDvNm,
        String trgUseYn, /* 목표사용여부 */
        String calfCn, /* 계산식내용 */
        String cexpCn, /* 조건식내용 */
        String evlAtcCn, /* 평가항목내용 */
        String dtaDlYn /* 데이터삭제여부 */
    ) {}

    // *********************************************************
    // Request Dto
    // *********************************************************
    // 우수사업부 기준관리 - 상세 평가기준 Delete Request Dto
    @ApiModel("WpsdExcellentDivisionBaseMgtDto-EvlDetailDeleteReq")
    public record EvlDetailDeleteReq(
        String baseYm,
        String evlOgTpCd, /* 평가조직유형코드 */
        String evlDvCd, /* 평가구분코드 */
        String evlAtcDvCd /* 평가항목구분코드 */
    ) {}

    // *********************************************************
    // Request Dto
    // *********************************************************
    // 우수사업부 기준관리 - 목표기준 Search Request Dto
    @Builder
    @ApiModel("WpsdExcellentDivisionBaseMgtDto-TrgSearchReq")
    public record TrgSearchReq(
        String baseYm,
        @NotBlank
        String evlOgTpCd,
        String evlDvCd
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 우수사업부 기준관리 - 목표기준 Search Result Dto
    @ApiModel("WpsdExcellentDivisionBaseMgtDto-TrgSearchRes")
    public record TrgSearchRes(
        String baseYm,
        String trgUseYn,
        String evlDvNm,
        String evlOgTpCd,
        String evlDvCd,
        String evlAtcDvCd,
        String evlAtcDvNm,
        String evlPdDvCd,
        String evlBaseUnitN,
        String cvtBasePc,
        String trgBasePc,
        String dtaDlYn
    ) {}
    // *********************************************************
    // Result Dto
    // *********************************************************
    // 우수사업부 기준관리 - 목표기준 Save Result Dto
    @ApiModel("WpsdExcellentDivisionBaseMgtDto-TrgSaveReq")
    public record TrgSaveReq(
        String baseYm,
        String trgUseYn,
        String evlDvNm,
        String evlOgTpCd,
        String evlDvCd,
        String evlAtcDvCd,
        String evlAtcDvNm,
        String evlPdDvCd,
        String evlBaseUnitN,
        String cvtBasePc,
        String trgBasePc,
        String ctstGrpCd,
        String dtaDlYn
    ) {}
    @ApiModel("WpsdExcellentDivisionBaseMgtDto-DeadlineSearchReq")
    public record DeadlineSearchReq(
        @NotBlank
        String basYrmn,
        @NotBlank
        String ogTpCd
    ) {}

    @ApiModel("WpsdExcellentDivisionBaseMgtDto-DeadlineSearchRes")
    public record DeadlineSearchRes(
        String ddlnId,
        String ogTpCd,
        String basYrmn,
        String startDt,
        String startHm,
        String finsDt,
        String finsHm
    ) {}

    @ApiModel("WpsdExcellentDivisionBaseMgtDto-DeadlineSaveReq")
    public record DeadlineSaveReq(
        String ddlnId,
        String basYrmn,
        String ogTpCd,
        String startDt,
        String startHm,
        String finsDt,
        String finsHm
    ) {}

}
