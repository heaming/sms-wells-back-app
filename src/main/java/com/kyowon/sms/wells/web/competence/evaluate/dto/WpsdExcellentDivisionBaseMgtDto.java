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
        String awdEvlId
    ) {}
    // *********************************************************
    // Result Dto
    // *********************************************************
    // 우수사업부 기준관리 - 시상구분 Search Result Dto
    @ApiModel("WpsdExcellentDivisionBaseMgtDto-EvlAwardRes")
    public record EvlAwardRes(
        String baseYm,
        String evlOgTpCd,
        String awdEvlId,
        String awdEvlNm,
        String evlRsbRelId,
        String evlAtcCn,
        String ctstGrpUseYn,
        String evlWtcfUseYn,
        String awdEvlGdUseYn,
        String dtaDlYn
    ){

    }
    // *********************************************************
    // Result Dto
    // *********************************************************
    // 우수사업부 기준관리 - 평가기준 Search Result Dto
    @ApiModel("WpsdExcellentDivisionBaseMgtDto-EvlSearchRes")
    public record EvlSearchRes(
        String baseYm,           /* 기준년월 */
        String evlOgTpCd,        /* 평가조직유형코드 */
        String awdEvlId,         /* 시상평가ID */
        String awdEvlNm,         /* 시상평가명 */
        String evlRsbRelId,      /* 평가직책관계ID */
        String evlAtcCn,         /* 평가항목내용 */
        String ctstGrpUseYn,     /* 경진그룹사용여부 */
        String evlWtcfUseYn,     /* 평가가중치사용여부 */
        String awdEvlGdUseYn,    /* 시상평가등급사용여부 */
        String evlOgTpNm,
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
        String baseYm,           /* 기준년월 */
        String evlOgTpCd,        /* 평가조직유형코드 */
        String awdEvlId,         /* 시상평가ID */
        String awdEvlNm,         /* 시상평가명 */
        String evlRsbRelId,      /* 평가직책관계ID */
        String evlAtcCn,         /* 평가항목내용 */
        String ctstGrpUseYn,     /* 경진그룹사용여부 */
        String dtaDlYn,
        String rsbDvCds,
        String rsbDvNms,
        String[] rsbDvCdList,
        String qlfDvNm,
        String qlfDvCd,
        String rowState
    ) {}

        // *********************************************************
    // Request Dto
    // *********************************************************
    // 우수사업부 기준관리 - 평가기준 Save Request Dto
    @ApiModel("WpsdExcellentDivisionBaseMgtDto-EvlDeleteReq")
    public record EvlDeleteReq(
        String awdEvlId         /* 시상평가ID */
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 우수사업부 기준관리 - 평가기준 Search Result Dto
    @ApiModel("WpsdExcellentDivisionBaseMgtDto-EvlDetailSearchRes")
    public record EvlDetailSearchRes(
        String baseYm,          /* 기준년월 */
        String evlOgTpCd,       /* 평가조직유형코드 */
        String awdEvlId,        /* 평가구분코드 */
        String awdEvlNm,        /* 시상평가명 */
        String evlRsbRelId,      /* 평가직책관계ID */
        String evlAtcDvCd,      /* 평가항목구분코드 */
        String evlAtcDvNm,      /* 평가항목구분명*/
        String evlPdDvCd,       /* 평가상품구분코드 */
        int sortOdr,            /* 정렬순서 */
        double evlBaseUnitN,    /* 평가기준단위수 */
        double cvtBasePc,       /* 환산기준점수 */
        String dtaDlYn         /* 데이터삭제여부 */
    ) {}

    // *********************************************************
    // Request Dto
    // *********************************************************
    // 우수사업부 기준관리 - 상세 평가기준 Save Request Dto
    @ApiModel("WpsdExcellentDivisionBaseMgtDto-EvlDetailSaveReq")
    public record EvlDetailSaveReq(
        String baseYm,      /* 기준년월 */
        String evlOgTpCd,   /* 평가조직유형코드 */
        String awdEvlId,    /* 시상평가ID */
        String evlRsbRelId, /* 평가직책관계ID */
        String evlAtcDvCd,  /* 평가항목구분코드 */
        String evlAtcDvNm,  /* 평가항목구분명*/
        String evlPdDvCd,   /* 평가상품구분코드 */
        double evlBaseUnitN,/* 평가기준단위수 */
        double cvtBasePc,   /* 환산기준점수 */
        double trgBasePc,   /* 목표기준점수 */
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
        @NotBlank
        String awdEvlId, /* 평가구분코드 */
        String evlAtcDvCd /* 평가항목구분코드 */
    ) {}

    // *********************************************************
    // Request Dto
    // *********************************************************
    // 우수사업부 기준관리 - 목표기준 Search Request Dto
    @Builder
    @ApiModel("WpsdExcellentDivisionBaseMgtDto-TrgSearchReq")
    public record TrgSearchReq(
        @NotBlank
        String baseYm,
        @NotBlank
        String evlOgTpCd,
        @NotBlank
        String awdEvlId,
        @NotBlank
        String evlAtcDvCd
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 우수사업부 기준관리 - 목표기준 Search Result Dto
    @ApiModel("WpsdExcellentDivisionBaseMgtDto-TrgSearchRes")
    public record TrgSearchRes(
        String baseYm,
        String awdEvlId,
        String awdEvlNm,
        String evlRsbRelId,
        String evlAtcCn,
        String ctstGrpUseYn,
        String evlWtcfUseYn,
        String awdEvlGdUseYn,
        double evlBaseUnitN,
        double cvtBasePc,
        int sortOdr,
        Integer absltEvlBasePc,
        Integer ptyEvlMaxPc,
        Integer ptyEvlMinPc,
        String awdPtyEvlDvCd,
        Integer ptyEvlItrvPc,
        String evlOgTpCd,
        String evlAtcDvCd,
        String evlAtcDvNm,
        String evlPdDvCd,
        String trgUseYn,
        String prtnrNo,
        double trgBasePc,
        String ctstGrpCd,
        String prtnrKnm
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 우수사업부 기준관리 - 목표기준 Save Request Dto
    @ApiModel("WpsdExcellentDivisionBaseMgtDto-TrgSaveReq")
    public record TrgSaveReq(
        String baseYm,
        String awdEvlId,
        String awdEvlNm,
        String evlRsbRelId,
        String evlAtcCn,
        String ctstGrpUseYn,
        String evlWtcfUseYn,
        String awdEvlGdUseYn,
        double evlBaseUnitN,
        double cvtBasePc,
        int sortOdr,
        Integer absltEvlBasePc,
        Integer ptyEvlMaxPc,
        Integer ptyEvlMinPc,
        String awdPtyEvlDvCd,
        Integer ptyEvlItrvPc,
        String evlOgTpCd,
        String evlAtcDvCd,
        String evlAtcDvNm,
        String evlPdDvCd,
        String trgUseYn,
        String prtnrNo,
        double trgBasePc,
        String ctstGrpCd,
        String prtnrKnm
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
