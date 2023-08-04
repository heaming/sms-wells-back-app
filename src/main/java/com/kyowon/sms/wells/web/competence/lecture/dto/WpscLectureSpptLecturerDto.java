package com.kyowon.sms.wells.web.competence.lecture.dto;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

import javax.validation.constraints.NotBlank;

public class WpscLectureSpptLecturerDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // 강의지원 강사관리 Search Request Dto
    @Builder
    @ApiModel("WpscLectureSpptLecturerDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String ogTpCd,

        String lectNm
    ) {
    }

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 강의지원 강사관리 Search Result Dto
    @ApiModel("WpscLectureSpptLecturerDto-SearchRes")
    public record SearchRes(
        String lectrSpptOgTpCd,
        String lectrSpptLectCd,
        String lectNm,
        String useYn
    ) {
    }

    // *********************************************************
    // Save Request Dto
    // *********************************************************
    // 강의지원 강사관리 Save Request Dto
    @ApiModel("WpscLectureSpptLecturerDto-SaveReq")
    public record SaveReq(
        @NotBlank
        String lectrSpptOgTpCd,
        String lectrSpptLectCd,
        @NotBlank
        String lectNm,
        String useYn,
        String rowState
    ){}
}
