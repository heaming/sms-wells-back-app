package com.kyowon.sms.wells.web.service.orgcode.dto;

import io.swagger.annotations.ApiModel;

public class WsndServiceRegionLevelPsDto {
    @ApiModel("WsndServiceRegionLevelPsDto-SearchReq")
    public record SearchReq(
        String svBizHclsfNm,    // 서비스유형 : 01.전체, 02.설치, 03.A/S, 04.홈케어
        String ogCd,            // 서비스센터
        String prtnrNo,         // 담당자
        String searchDateType,  // 조회기준 : 01.접수일자, 02.예정일자, 03.처리일자, 04.방문확정일
        String fromDate,        // 조회기준에 따른 시작일자
        String toDate           // 조회기준에 따른 종료일자
    ) {}

    @ApiModel("WsndServiceRegionLevelPsDto-SearchRes")
    public record SearchRes(
        String pdCd,
        String hgrPdCd,
        String pdTpCd,
        String bzHdqDvCd
    ) {}
}
