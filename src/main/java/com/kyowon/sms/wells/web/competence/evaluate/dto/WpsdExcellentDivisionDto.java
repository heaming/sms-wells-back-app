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
        String evlDvCd           /* 평가구분코드 */
    ){}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // SearchContestRes Result Dto
    @ApiModel("WpsdExcellentDivisionDto-SearchContestRes")
    public record SearchContestRes(
        String evlOgTpCd,           /* 조직유형코드 */
        String ctstGrpCd,        /* 경진그룹코드 */
        String vlStrtdt,         /* 유효시작일자 */
        String vlEnddt,          /* 유효종료일자 */
        String ctstGrpNm
    ){}
}
