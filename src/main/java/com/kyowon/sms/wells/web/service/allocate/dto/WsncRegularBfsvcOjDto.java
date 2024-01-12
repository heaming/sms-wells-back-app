package com.kyowon.sms.wells.web.service.allocate.dto;

import io.swagger.annotations.ApiModel;

import javax.validation.constraints.NotBlank;

public class WsncRegularBfsvcOjDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    @ApiModel(value = "WsncRegularBfsvcOjDto-CreateReq")
    public record CreateReq(
        @NotBlank
        String asnOjYm, /* 배정년월 */
        @NotBlank
        String createTarget /* 생성대상 */
    ) {}

    @ApiModel(value = "WsncRegularBfsvcOjDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String asnOjYm /* 배정년월 */
    ) {}
}
