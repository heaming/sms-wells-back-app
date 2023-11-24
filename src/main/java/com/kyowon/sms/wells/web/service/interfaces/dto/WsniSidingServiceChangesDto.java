package com.kyowon.sms.wells.web.service.interfaces.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;

import javax.validation.constraints.NotBlank;

import jdk.jfr.Label;

public class WsniSidingServiceChangesDto {

    @ApiModel(value = "WsniSidingServiceChangesDto-SaveReq")
    public record SaveReq(

        @NotBlank
        @JsonProperty(value = "CNTR_NO")
        String cntrNo,

        @NotBlank
        @JsonProperty(value = "CNTR_SN")
        String cntrSn,

        //AS요청구분코드 (1:패키지변경, 4:차월 방문 중지)
        @NotBlank
        @JsonProperty(value = "AS_AK_DV_CD")
        String asAkDvCd,

        //요청변경일자
        @JsonProperty(value = "AK_CHDT")
        String akChdt,

        //변경전상품코드
        @JsonProperty(value = "BFCH_PD_CD")
        String bfchPdCd,

        //변경후상품코드
        @JsonProperty(value = "AFCH_PD_CD")
        String afchPdCd,

        //자료처리상태코드 (1:신규, 2:변경, 3:취소)
        @NotBlank
        @JsonProperty(value = "MTR_PROCS_STAT_CD")
        String mtrProcsStatCd,

        // 자유선택인 경우 제품상품코드목록 (제품상품코드,수량|제품상품코드,수량| ....)
        @JsonProperty(value = "CHO_CAPSL_CN")
        String choCapslCn
    ) {}

    @ApiModel(value = "WsniSidingServiceChangesDto-SaveRes")
    public record SaveRes(
        @NotBlank
        String rsCd, // 결과코드(S:성공, F:실패)
        String rsMsg
    ) {}

}
