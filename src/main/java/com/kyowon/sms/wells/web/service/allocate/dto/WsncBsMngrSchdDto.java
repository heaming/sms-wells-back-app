package com.kyowon.sms.wells.web.service.allocate.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;

public class WsncBsMngrSchdDto {
    // *********************************************************
    // Request Dto
    // 2023.06.12  BS관리일정조회 홍세기 생성
    // *********************************************************
    @ApiModel(value = "WsncBsMngrSchdDto-SearchReq")
    public record SearchReq(

        String fxnPrtnrNo,

        @NotBlank
        String baseDateFrom,

        @NotBlank
        String baseDateTo
    ) {}

    @ApiModel(value = "WsncBsMngrSchdDto-SearchRes")
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

        String curntDt, // 최초계약일

        String cntrDt, // 계약일자

        String ogId, // 조직ID

        String prtnrNo, // 파트너사번

        String recntrDt, // 재계약일자

        String mngtAcc, // 관리계정

        String vstAcc, // 방문계정

        String fshAcc, // 완료계정

        String svcProc // 서비스처리율

    ) {}
}
