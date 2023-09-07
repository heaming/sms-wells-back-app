package com.kyowon.sms.wells.web.competence.lecture.dto;

import javax.validation.constraints.NotBlank;

import com.sds.sflex.system.config.validation.validator.ValidDate;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WpscLectureSpptAplcPtrmDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // 강의지원 신청기간등록 Search Request Dto
    @Builder
    @ApiModel("WpscLectureSpptAplcPtrmDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String lectrSpptOgTpCd,
        String lectrYm
    ) {
    }

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 강의지원 신청기간등록 Search Result Dto
    @ApiModel("WpscLectureSpptAplcPtrmDto-SearchRes")
    public record SearchRes(
        String lectrSpptOgTpCd,    /* 강의지원조직유형코드 */
        String lectrYm,            /* 강의년월 */
        String aplcStrtdt,         /* 신청시작일자 */
        String aplcStrtHm,         /* 신청시작시분 */
        String aplcEnddt,          /* 신청종료일자 */
        String aplcEndHm,          /* 신청종료시분 */
        String dtaDlYn            /* 데이터삭제여부 */
    ) {
    }

    // *********************************************************
    // Save Request Dto
    // *********************************************************
    // 강의지원 신청기간등록 SaveReq Request Dto
    @Builder
    @ApiModel("WpscLectureSpptAplcPtrmDto-SaveReq")
    public record SaveReq(
        @NotBlank
        String lectrSpptOgTpCd,    /* 강의지원조직유형코드 */
        @NotBlank
        String lectrYm,            /* 강의년월 */
        @NotBlank
        String aplcStrtdt,         /* 신청시작일자 */
        @NotBlank
        String aplcStrtHm,         /* 신청시작시분 */
        @NotBlank
        String aplcEnddt,          /* 신청종료일자 */
        @NotBlank
        String aplcEndHm          /* 신청종료시분 */
    ) {
    }

     @Builder
     @ApiModel("WpscLectureSpptAplcPtrmDto-deleteReq")
     public record deleteReq(
        @NotBlank
        String lectrSpptOgTpCd,    /* 강의지원조직유형코드 */
        @NotBlank
        String lectrYm            /* 강의년월 */
    ){}

}
