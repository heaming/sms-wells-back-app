package com.kyowon.sms.wells.web.service.allocate.dto;

import io.swagger.annotations.ApiModel;

import javax.validation.constraints.NotBlank;

public class WsncContactHistoryDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    @ApiModel(value = "WsncContactHistoryDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String cstSvAsnNo /* 고객서비스배정번호 */
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    @ApiModel(value = "WsncContactHistoryDto-SearchRes")
    public record SearchRes(
        String ogNm, /* 조직명 */
        String prtnrNo, /* 파트너번호 */
        String prtnrKnm, /* 파트너한글명 */
        String cntcDt, /* 접촉일자 */
        String cntcHh, /* 접촉시간 */
        String absncRsonCd, /* 부재사유코드 */
        String cntcCn /* 접촉내용 */
    ) {}
}
