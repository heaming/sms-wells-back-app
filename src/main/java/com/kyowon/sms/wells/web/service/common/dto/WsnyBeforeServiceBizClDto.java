package com.kyowon.sms.wells.web.service.common.dto;

import io.swagger.annotations.ApiModel;

import javax.validation.constraints.NotBlank;

public class WsnyBeforeServiceBizClDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    @ApiModel(value = "WsnyBeforeServiceBizClDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String managementYear, /* 관리년도 */
        String managementItem /* 관리항목 */
    ) {}

    @ApiModel(value = "WsnyBeforeServiceBizClDto-SaveReq")
    public record SaveReq(
        @NotBlank
        String mngtYm, /* 관리년월 */
        @NotBlank
        String mngtItm, /* 관리항목 */
        String strtdt, /* 시작일자 */
        String strtHh, /* 시작시간 */
        String enddt, /* 종료일자 */
        String endHh /* 종료시간 */
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    @ApiModel(value = "WsnyBeforeServiceBizClDto-SearchRes")
    public record SearchRes(
        String mngtYm, /* 관리년월 */
        String strtdt, /* 시작일자 */
        String strtHh, /* 시작시간 */
        String enddt, /* 종료일자 */
        String endHh, /* 종료시간 */
        String mngtItm /* 관리항목 */
    ) {}
}
