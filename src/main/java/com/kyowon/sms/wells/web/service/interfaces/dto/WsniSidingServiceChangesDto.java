package com.kyowon.sms.wells.web.service.interfaces.dto;

import io.swagger.annotations.ApiModel;

import javax.validation.constraints.NotBlank;

public class WsniSidingServiceChangesDto {

    @ApiModel(value = "WsniSidingServiceChangesDto-SaveReq")
    public record SaveReq(
        @NotBlank
        String cntrNo,
        String cntrSn,
        String akSn, // 요청일련번호
        String asAkDvCd, // AS요청구분코드 (1:패키지변경, 4:차월 방문 중지)
        String akChdt, // 요청변경일자
        String bfchPdCd, // 변경전상품코드
        String afchPdCd, // 변경후상품코드
        String mtrProcsStatCd, // 자료처리상태코드 (1:신규, 2:변경, 3:취소)
        String userId
    ) {}

    @ApiModel(value = "WsniSidingServiceChangesDto-SaveRes")
    public record SaveRes(
        String svPrd,
        String pdPrpVal01,
        String sellTpCd,
        String istDt,
        String bsMths,
        String basePdCd,
        String userId
    ) {}

}
