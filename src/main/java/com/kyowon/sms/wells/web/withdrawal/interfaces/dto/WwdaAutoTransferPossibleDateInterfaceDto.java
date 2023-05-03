package com.kyowon.sms.wells.web.withdrawal.interfaces.dto;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;

public class WwdaAutoTransferPossibleDateInterfaceDto {

    /*
        wells 자동이체 가능일자 조회 Req Dto
     */
    @ApiModel("WwdaAutoTransferPossibleDateInterfaceDto-SearchReq")
    public record SearchReq(
        @NotBlank
        @JsonProperty("BIL_YM")
        String bilYm // 청구년월
    ) {}

    /*
        wells 자동이체 가능일자 조회 Res Dto
     */
    @ApiModel("WwdaAutoTransferPossibleDateInterfaceDto-SearchRes")
    public record SearchRes(
        @JsonProperty("RGL_FNT_D")
        String rglFntD, // 정규이체일
        @JsonProperty("BIL_TP_CD_NM")
        String bilTpCdNm // 청구유형코드명
    ) {}
}
