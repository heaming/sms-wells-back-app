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
        String awdEvlId,             /* 평가구분코드 */
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
        @NotBlank
        String evlOgTpCd,           /* 조직유형코드 */
        String awdEvlId,
        String ctstGrpCd

    ){}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // SearchContestRes Result Dto
    @ApiModel("WpsdExcellentDivisionDto-SearchContestRes")
    public record SearchContestRes(
        String evlOgTpCd,
        String awdEvlId,
        String ctstGrpCd,
        String vlStrtdt,
        String vlEnddt,
        String ctstGrpNm,
        String unuitmCn,
        String dtaDlYn,
        String fstRgstDtm,
        String fstRgstUsrId,
        String fstRgstPrgId,
        String fstRgstDeptId,
        String fnlMdfcDtm,
        String fnlMdfcUsrId,
        String fnlMdfcPrgId,
        String fnlMdfcDeptId
    ){}

    // *********************************************************
    // Request Dto
    // *********************************************************
    // SearchGnrdvRes Request Dto
    @ApiModel("WpsdExcellentDivisionDto-SearchEvlRsbReq")
    public record SearchEvlRsbReq(
        @NotBlank
        String baseYm,
        @NotBlank
        String evlOgTpCd,
        String awdEvlId
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
    @ApiModel("WpsdExcellentDivisionDto-SearchContestPartnerReq")
    public record SearchContestPartnerReq(
        @NotBlank
        String baseYm,
        @NotBlank
        String evlOgTpCd,
        @NotBlank
        String awdEvlId,
        @NotBlank
        String evlAtcDvCd,
        String evlRsbDvCd,
        String ctstGrpCd
    ){}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // SearchGnrdvRes Result Dto
    @ApiModel("WpsdExcellentDivisionDto-SearchContestPartnerRes")
    public record SearchContestPartnerRes(
        String baseYm,
        String evlOgTpCd,
        String awdEvlId,
        String evlAtcDvCd,
        String evlAtcDvNm,
        String prtnrNo,
        String evlPdDvCd,
        String trgBasePc,
        String ctstGrpCd,
        Integer sortOdr,
        String prtnrKnm,
        String ogCd,
        String ogNm
    ){}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // SearchGnrdvRes Result Dto
    @ApiModel("WpsdExcellentDivisionDto-SaveContestPartnerReq")
    public record SaveContestPartnerReq(
        String baseYm,
        String evlOgTpCd,
        String awdEvlId,
        String evlAtcDvCd,
        String prtnrNo,
        String prtnrKnm,
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
        String awdEvlId,
        String ogId,
        String evlAtcDvCd,
        String prtnrNo,
        Long trgBasePc

    ){}

}
