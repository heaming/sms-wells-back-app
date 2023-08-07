package com.kyowon.sms.wells.web.competence.lecture.dto;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

import javax.validation.constraints.NotBlank;

public class WpscLectureSpptLectureDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // 강의지원 강의관리 Search Request Dto
    @Builder
    @ApiModel("WpscLectureSpptLectureDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String ogTpCd,

        String lectrNm
    ) {
    }

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 강의지원 강의관리 Search Result Dto
    @ApiModel("WpscLectureSpptLectureDto-SearchRes")
    public record SearchRes(
        String lectrSpptOgTpCd,
        String lectrSpptLectrCd,
        String lectrNm,
        String useYn
    ) {
    }

    // *********************************************************
    // Save Request Dto
    // *********************************************************
    // 강의지원 강의관리 Save Request Dto
    @ApiModel("WpscLectureSpptLectureDto-SaveReq")
    public record SaveReq(
        @NotBlank
        String lectrSpptOgTpCd,
        String lectrSpptLectrCd,
        @NotBlank
        String lectrNm,
        String useYn,
        String rowState
    ){}
}
