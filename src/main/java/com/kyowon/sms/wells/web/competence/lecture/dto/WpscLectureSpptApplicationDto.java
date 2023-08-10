package com.kyowon.sms.wells.web.competence.lecture.dto;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

import javax.validation.constraints.NotBlank;

public class WpscLectureSpptApplicationDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // 강의지원 신청 Search Request Dto
    @Builder
    @ApiModel("WpscLectureSpptApplicationDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String lectrSpptOgTpCd,    /* 강의지원조직유형코드 */
        @NotBlank
        String lectrYm,            /* 강의년월 */
        String lectNm,
        String ogLevlDvCd1,        /* 조직레벨1 */
        String ogLevlDvCd2         /* 조직레벨2 */
    ) {
    }

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 강의지원 신청 Search Result Dto
    @ApiModel("WpscLectureSpptApplicationDto-SearchRes")
    public record SearchRes(
        String lectrSpptAplcId,      /* 강의지원신청ID */
        String lectrSpptOgTpCd,      /* 강의지원조직유형코드 */
        String lectrYm,              /* 강의년월 */
        Integer lectrTCnt,           /* 강의차수 */
        Integer lectrSpptLectCd,     /* 강의지원강사코드 */
        String dgr1LevlOgNm,
        String dgr2LevlOgNm,
        String LectNm,
        Integer lectrSpptLectrCd,    /* 강의지원강의코드 */
        String lectrNm,
        String lectrDt,              /* 강의일자 */
        String ogId,                 /* 조직ID */
        String bldCd,                /* 빌딩코드 */
        String bldNm,
        String lectrAplcUsrId,       /* 강의신청사용자ID */
        String dtaDlYn              /* 데이터삭제여부 */
    ) {
    }

    @ApiModel("WpscLectureSpptApplicationDto-SearchOgTypeRes")
    public record SearchOgTypeRes(
        String ogId,
        String lectrYm,
        String dgr1LevlOgNm,
        String dgr2LevlOgNm,
        String lectrTCnt1BldNm,
        String lectrTCnt1BldCd,
        String lectrTCnt1LectNm,
        String lectrTCnt1LectCd,
        String lectrTCnt1LectrNm,
        String lectrTCnt1LectrCd,
        String lectrTCnt1LectrDt,
        String lectrTCnt2BldNm,
        String lectrTCnt2BldCd,
        String lectrTCnt2LectCd,
        String lectrTCnt2LectNm,
        String lectrTCnt2LectrCd,
        String lectrTCnt2LectrNm,
        String lectrTCnt2LectrDt,
        String lectrTCnt3BldNm,
        String lectrTCnt3BldCd,
        String lectrTCnt3LectCd,
        String lectrTCnt3LectNm,
        String lectrTCnt3LectrCd,
        String lectrTCnt3LectrNm,
        String lectrTCnt3LectrDt
    ) {
    }
    // *********************************************************
    // Save Request Dto
    // *********************************************************
    // 강의지원 신청 Save Request Dto
    @Builder
    @ApiModel("WpscLectureSpptApplicationDto-SaveReq")
    public record SaveReq(
        String lectrSpptAplcId,      /* 강의지원신청ID */
        String lectrSpptOgTpCd,      /* 강의지원조직유형코드 */
        String lectrYm,              /* 강의년월 */
        Integer lectrTCnt,           /* 강의차수 */
        Integer lectrSpptLectCd,     /* 강의지원강사코드 */
        Integer lectrSpptLectrCd,    /* 강의지원강의코드 */
        String lectrDt,              /* 강의일자 */
        String ogId,                 /* 조직ID */
        String bldCd,                /* 빌딩코드 */
        String lectrAplcUsrId,       /* 강의신청사용자ID */
        String dtaDlYn,              /* 데이터삭제여부 */
        String rowState
    ) {
    }

    // *********************************************************
    //  Search og Level Requset Dto
    // *********************************************************
    // 강의지원 신청 Search og Level Request Dto
    @Builder
    @ApiModel("WpscLectureSpptApplicationDto-SearchLevelReq")
    public record SearchLevelReq(
        String ogTpCd,
        String baseYm
    ) {
    }

    // *********************************************************
    // Response Dto
    // *********************************************************
    // 강의지원 신청 Search og Level Response Dto
    @ApiModel("WpscLectureSpptApplicationDto-SearchLevelRes")
    public record SearchLevelRes(
        String bldCd,
        String bldNm,
        String ogId
    ) {
    }

    // *********************************************************
    // Remove Request Dto
    // *********************************************************
    // 강의지원 신청 Remove Request Dto
    @ApiModel("WpscLectureSpptApplicationDto-RemoveRes")
    public record RemoveReq(
        String lectrSpptAplcId
    ){}
}
