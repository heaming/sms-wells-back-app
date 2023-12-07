package com.kyowon.sms.wells.web.competence.evaluate.dto;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

import javax.validation.constraints.NotBlank;

public class WpsdExcellentDivisionDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    //  Search Request Dto
    @Builder
    @ApiModel("WpsdExcellentDivisionDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String baseYm,              /* 관리년월 */
        @NotBlank
        String evlOgTpCd,           /* 조직유형코드 */
        @NotBlank
        String evlDvCd,             /* 평가구분코드 */
        String cntrPerfDvCd,        /* 실적구분 */
        String ctstGrpCd            /* 당월그룹 */
    ) {
    }

    // *********************************************************
    // Request Dto
    // *********************************************************
    // SearchContestRes Request Dto
    @ApiModel("WpsdExcellentDivisionDto-SearchContestReq")
    public record SearchContestReq(
        String evlOgTpCd,           /* 조직유형코드 */
        String evlDvCd,           /* 평가구분코드 */
        String ctstGrpCd
    ){}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // SearchContestRes Result Dto
    @ApiModel("WpsdExcellentDivisionDto-SearchContestRes")
    public record SearchContestRes(
        String evlOgTpCd,           /* 조직유형코드 */
        String evlDvCd,
        String evlDvNm,
        String ctstGrpCd,        /* 경진그룹코드 */
        String ctstGrpNm,
        String vlStrtdt,         /* 유효시작일자 */
        String vlEnddt          /* 유효종료일자 */
    ){}

    // *********************************************************
    // Request Dto
    // *********************************************************
    // SearchGnrdvRes Request Dto
    @ApiModel("WpsdExcellentDivisionDto-SearchEvlRsbReq")
    public record SearchEvlRsbReq(
        @NotBlank
        String evlOgTpCd,              /* 관리년월 */
        String evlDvCd           /* 조직유형코드 */
    ){}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // SearchGnrdvRes Result Dto
    @ApiModel("WpsdExcellentDivisionDto-SearchEvlRsbRes")
    public record SearchEvlRsbRes(
        String evlOgTpCd,
        String evlRsbDvCd,
        String evlRsbDvNm
    ){}


    // *********************************************************
    // Request Dto
    // *********************************************************
    // SearchContestRes Request Dto
    @ApiModel("WpsdExcellentDivisionDto-SearchContestRsbReq")
    public record SearchContestRsbReq(
        @NotBlank
        String baseYm,
        @NotBlank
        String evlOgTpCd,           /* 조직유형코드 */
        @NotBlank
        String evlDvCd,           /* 평가구분코드 */
        String evlRsbDvCd,
        String ctstGrpCd
    ){}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // SearchGnrdvRes Result Dto
    @ApiModel("WpsdExcellentDivisionDto-SearchContestRsbRes")
    public record SearchContestRsbRes(
        String baseYm,
        String evlOgTpCd,
        String evlDvCd,
        String evlDvNm,
        String ogId,
        String ogNm,
        String evlRsbDvCd,
        String evlRsbDvNm,
        String ctstGrpCd
    ){}

    // *********************************************************
    // Reqeust Dto
    // *********************************************************
    // Save Request Dto
    @ApiModel("WpsdExcellentDivisionDto")
    public record SaveReq(
        String baseYm,
        String evlOgTpCd,
        String evlDvCd,
        String ogId,
        String evlAtcDvCd,
        String prtnrNo,

        Long trgBasePc

    ){}

}
