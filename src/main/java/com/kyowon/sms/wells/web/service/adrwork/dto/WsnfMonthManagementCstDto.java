package com.kyowon.sms.wells.web.service.adrwork.dto;

import io.swagger.annotations.ApiModel;

import javax.validation.constraints.NotBlank;

public class WsnfMonthManagementCstDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    @ApiModel(value = "WsnfMonthManagementCstDto-CreateReq")
    public record CreateReq(
        @NotBlank
        String mngtYm,          /* 관리년월 */
        @NotBlank
        String createTarget     /* 생성대상 */
    ) {}

    @ApiModel(value = "WsnfMonthManagementCstDto-RemoveReq")
    public record RemoveReq(
        @NotBlank
        String mngtYm,          /* 관리년월 */
        @NotBlank
        String createTarget     /* 생성대상 */
    ) {}

    @ApiModel(value = "WsnfMonthManagementCstDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String mngtYm,          /* 관리년월 */
        @NotBlank
        String createTarget     /* 생성대상 */
    ) {}
}
