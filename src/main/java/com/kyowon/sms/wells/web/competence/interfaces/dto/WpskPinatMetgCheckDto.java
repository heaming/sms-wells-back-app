package com.kyowon.sms.wells.web.competence.interfaces.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Builder;

import javax.validation.constraints.NotBlank;

public class WpskPinatMetgCheckDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // 핀앳) 출결앱 미팅 체크 Create Request Dto
    @Builder
    @ApiModel("WpskPinatMetgCheckDto-CreateReq")
    public record CreateReq(
        @JsonProperty("PRTNR_NOS")
        @NotBlank
        String prtnrNos,    /* 파트너 번호 */
        @JsonProperty("METG_PRSC_DT")
        @NotBlank
        String metgPrscDt   /* 미팅참석일 */
    ) {
    }

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 핀앳) 출결앱 미팅 체크 Create Result Dto
    @ApiModel("WpskPinatMetgCheckDto-CreateRes")
    public record CreateRes(
        @JsonProperty("RSP_CD")
        String rspCd, /* 응답코드 */
        @JsonProperty("RSP_MSG")
        String rspMsg /* 응답메시지 */

    ) {
    }
}
