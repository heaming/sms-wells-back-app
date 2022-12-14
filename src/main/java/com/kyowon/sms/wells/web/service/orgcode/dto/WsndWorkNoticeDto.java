package com.kyowon.sms.wells.web.service.orgcode.dto;

import javax.validation.constraints.NotBlank;

import com.sds.sflex.system.config.validation.validator.ValidDate;

import io.swagger.annotations.ApiModel;

public class WsndWorkNoticeDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // 작업공지 Search Request Dto
    @ApiModel(value = "WsndWorkNoticeDto-SearchReq")
    public record SearchReq(
        String stRgstDt,
        String edRgstDt,
        String mngrDvCd,
        String ntccnTitNm
    ) {}

    // 작업공지 Find Request Dto
    @ApiModel(value = "WsndWorkNoticeDto-FindReq")
    public record FindReq(
        String mngtYm,
        String ntcId,
        String ntcSn
    ) {}

    // 작업공지 Create Request Dto
    @ApiModel(value = "WsndWorkNoticeDto-CreateReq")
    public record CreateReq(
        @NotBlank
        String pdGrpCd,
        String pdCd,
        String istWkYn,
        String asWkYn,
        String bfsvcWkYn,
        @NotBlank
        String mngrDvCd,
        String ntccnTitNm,
        String ntccnCn,
        @ValidDate
        String vlStrtdt,
        @ValidDate
        String vlEnddt
    ) {}

    // 작업공지 Edit Request Dto
    @ApiModel(value = "WsndWorkNoticeDto-EditReq")
    public record EditReq(
        @NotBlank
        String mngtYm,
        @NotBlank
        String ntcId,
        @NotBlank
        String ntcSn,
        @NotBlank
        String pdGrpCd,
        String pdCd,
        String istWkYn,
        String asWkYn,
        String bfsvcWkYn,
        @NotBlank
        String mngrDvCd,
        String ntccnTitNm,
        String ntccnCn,
        @ValidDate
        String vlStrtdt,
        @ValidDate
        String vlEnddt
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 작업공지 Search Result Dto
    @ApiModel(value = "WsndWorkNoticeDto-SearchRes")
    public record SearchRes(
        String mngtYm,
        String ntcId,
        String ntcSn,
        String pdGrpCd,
        String pdCd,
        String pdNm,
        String istWkYn,
        String asWkYn,
        String bfsvcWkYn,
        String mngrDvCd,
        String ntccnTitNm,
        String ntccnCn,
        String vlStrtdt,
        String vlEnddt,
        String fstRgstDtm,
        String fstRgstUsrId,
        String fstRgstUsrNm
    ) {}

    // 작업공지 Find Result Dto
    @ApiModel(value = "WsndWorkNoticeDto-FindRes")
    public record FindRes(
        String mngtYm,
        String ntcId,
        String ntcSn,
        String pdGrpCd,
        String pdCd,
        String istWkYn,
        String asWkYn,
        String bfsvcWkYn,
        String mngrDvCd,
        String ntccnTitNm,
        String ntccnCn,
        String vlStrtdt,
        String vlEnddt
    ) {}

    // 상품코드 Search Result Dto
    @ApiModel(value = "WsndWorkNoticeDto-SearchProductRes")
    public record SearchProductRes(
        String pdCd,
        String pdNm
    ) {}

}
