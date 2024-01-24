package com.kyowon.sms.wells.web.service.allocate.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;

public class WsncBsManagerScheduleDto {
    // *********************************************************
    // Request Dto
    // 2023.06.12  BS관리일정조회(일자별) 홍세기 생성
    // *********************************************************
    @ApiModel(value = "WsncBsManagerScheduleDto-SearchReq")
    public record SearchReq(

        String fxnPrtnrNo,

        @NotBlank
        String baseDateFrom,

        @NotBlank
        String baseDateTo
    ) {}

    @ApiModel(value = "WsncBsManagerScheduleDto-WeekReq")
    public record Weekreq(
        @NotBlank
        String managementYear
    ) {}

    @ApiModel(value = "WsncBsManagerScheduleDto-WeekRes")
    public record Weekres(

        String weekNum,
        String codeId,
        String codeName,

        String dayS1,

        String dayS2,

        String dayS3,

        String dayS4,

        String dayS5,

        String dayS6,

        String dayS7,

        String dayC1,

        String dayC2,

        String dayC3,

        String dayC4,

        String dayC5,

        String dayC6,

        String dayC7

    ) {}

    @ApiModel(value = "WsncBsManagerScheduleDto-DetailRes")
    public record Detailres(

        String weekGubun,

        String bsWeekDay1,

        String bsWeekDay2,

        String bsWeekDay3,

        String bsWeekDay4,

        String bsWeekDay5,

        String bsWeekDay6,

        String bsWeekDay7
    ) {}

    @ApiModel(value = "WsncBsManagerScheduleDto-DetailReq")
    public record Detailreq(
        @NotBlank
        String fxnPrtnrNo,
        @NotBlank
        String weekDay1,

        String weekDay2,

        String weekDay3,

        String weekDay4,

        String weekDay5,

        String weekDay6,

        String weekDay7

    ) {}

    @ApiModel(value = "WsncBsManagerScheduleDto-Aggregates")
    public record Aggregates(

        String cntrDt, // 계약일자

        String ogId, // 조직ID

        String prtnrNo, // 파트너사번

        String recntrDt, // 재계약일자

        String mngtAcc, // 관리계정

        String vstAcc, // 방문계정

        String fshAcc, // 완료계정

        String svcProc // 서비스처리율

    ) {}

    @ApiModel(value = "WsncBsManagerScheduleDto-SearchRes")
    public record SearchRes(
        String vstDt, // 방문일자

        String vstTm, // 방문시간

        String cstNm, // 고객명

        String cntrNo, // 계약번호

        String pdctCD, // 제품명

        String goodsNm, // 제품명

        String mpNo, // 휴대전화번호

        String puPart1, // 투입부품1

        String puPart2, // 투입부품2

        String puPart3, // 투입부품3

        String puPart4, // 투입부품4

        String puPart5, // 투입부품5

        String puPart6, // 투입부품6
        String istYm, // 설치년월
        Integer cntrNmnN, // 사용차월
        Integer stplPtrm, // 약정기간
        Long fnlAmt, // 렌탈료
        Integer svHshdCnt // 사용계정수

    ) {}
}
