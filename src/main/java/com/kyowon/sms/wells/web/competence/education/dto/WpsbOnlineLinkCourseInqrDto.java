package com.kyowon.sms.wells.web.competence.education.dto;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

import javax.validation.constraints.NotBlank;

public class WpsbOnlineLinkCourseInqrDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // 온라인 연계 과정 조회 Search Request Dto
    @Builder
    @ApiModel("WpsbOnlineLinkCourseInqrDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String ogTpCd,
        String ogLevlDvCd1,
        String ogLevlDvCd2,
        @NotBlank
        String educSchdYm,
        String educDvCd,
        String educCrseNo,
        String prtnrNo,
        String educCpcAckmtYn
    ) {
    }

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 온라인 연계 과정 조회 Search Result Dto
    @ApiModel("WpsbOnlineLinkCourseInqrDto-SearchRes")
    public record SearchRes(
        String ogCd,
        String prtnrNo,
        String prtnrKnm,
        String dgr2LevlOgNm,
        String dgr3LevlOgNm,
        String rsbDvNm,
        String topmrPlarStmnt,
        String ackmtCt,
        String offlTcnt1,
        String offlTcnt2,
        String offlTcnt3,
        String onlineTcnt,
        String fnlCpcYn,
        String fshBsAcc

    ) {
    }
}
