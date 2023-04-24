package com.kyowon.sms.wells.web.closing.performance.dto;

import io.swagger.annotations.ApiModel;

import javax.validation.constraints.NotBlank;

public class WdccOverduePenaltyDto {

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 매출채권/선수금 현황 - 영업선수금 CodeReq
    @ApiModel(value = "WdccOverdueQenaltyDto-FindCodeRes")
    public record FindCodeRes(
        String sapPdDvCd,
        String sapPdDvNm
    ) {
    }

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 매출채권/선수금 현황 - 영업선수금 searchReq
    @ApiModel(value = "WdccOverdueQenaltyDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String slClYm,
        String perfDt,
        String agrgDv,
        String sellTpCd,
        String sellTpDtlCd,
        String sellChnlDtl,
        String cntr,
        String mlgBtdPrpdAmt
    ) {
    }

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 매출채권/선수금 현황 - 영업선수금 mainGrid
    @ApiModel(value = "WdccOverdueQenaltyDto-SearchMainGridRes")
    public record SearchMainGridRes(
        String slClYm,
        String sellTpNm,
        String sellTpDtlNm,
        String sapPdDvNm,
        String w1Am01,
        String w1Am02,
        String w1Am03,
        String w1Am04,
        String w1Am05,
        String w1Am06,
        String w1Am07,
        String w1Am08,
        String w1Am09
    ) {
    }

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 매출채권/선수금 현황 - 영업선수금 subGrid
    @ApiModel(value = "WdccOverdueQenaltyDto-SearchSubGridRes")
    public record SearchSubGridRes(
        String slClYm,   /* 실적년월*/
        String sellTpCd,     /* 판매유형*/
        String sellTpDtlCd,  /* 판매유형상세*/
        String cntrNo,   /* 계약상세번호*/
        String cstKnm,   /* 고객명*/
        String pdCd,     /* 상품코드*/
        String pdNm,      /* 상품코드명*/
        String sapPdDvCd, /* SAP상품구분코드명*/
        String w1Am01,   /* 기초영업선수금*/
        String w1Am02,   /* 영업선수입금*/
        String w1Am03,   /* 선수환불*/
        String w1Am04,   /* 합계*/
        String w1Am05,   /* 매출대사*/
        String w1Am06,   /* 위약금*/
        String w1Am07,   /* 매출조정금액*/
        String w1Am08,    /* 잡이익*/
        String w1Am09   /*기말영업선수금*/
    ) {
    }

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 매출채권/선수금 현황 - 영업선수금 SearchFourthGridRes
    @ApiModel(value = "WdccOverdueQenaltyDto-SearchThirdGridRes")
    public record SearchThirdGridRes(
        String slclym, /* 실적년월*/
        String selltpcd, /* 판매유형*/
        String sellTpDtlCd, /* 판매유형상세*/
        String sappddvcd, /* SAP상품구분코드명*/
        String wpAm01, /* 선수기초금액*/
        String wpAm02, /* 선수입금*/
        String wpAm03, /* 선수환불*/
        String wpAm04, /* 합계*/
        String wpAm05, /* 매출*/
        String wpAm06 /* 기말잔액*/
    ) {
    }

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 매출채권/선수금 현황 - 영업선수금 SearchFourthGridRes
    @ApiModel(value = "WdccOverdueQenaltyDto-SearchFourthGridRes")
    public record SearchFourthGridRes(
        String slClYm,/* 실적년월*/
        String sellTpCd,/* 판매유형*/
        String sellTpDtlCd,/* 판매유형상세*/
        String cntr,   /* 계약상세번호*/
        String cstKnm,/* 고객명*/
        String sapPdDvCd,/* SAP상품구분코드명*/
        String slRcogDt,/* 매출인식일자*/
        String mlgBtdPrpdAmt,/*기초금액*/
        String lciam1,/*유상입금*/
        String lciam2,/*무상입금*/
        String lciam3,/*기타입금*/
        String lcsam1,/*유상매출*/
        String lcsam2,/*무상매출*/
        String lcsam3, /*기타매출*/
        String mlgEotPrpdAmt /* 기말잔액*/
    ) {
    }
}
