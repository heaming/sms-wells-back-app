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
        String bsdt,
        String dgr1LevlOgNm,
        String dgr1LevlOgCd,
        String dgr1LevlOgId,
        String ogCd,
        String ogNm,
        String ogId,
        String ichrPrtnrNo,
        String rglvlSn,
        String prtnrKnm,
        String ac025EmpOr,
        String ogTpCd,
        String cntrNo,
        String cntrSn,
        String custCd,
        String rcgvpKnm,
        String procStusNm,
        String newAdrZip,
        String ctpvNm,
        String ctctyNm,
        String ac112EmdKorNm,
        String amtdNm,
        String co410FeeGb,
        String itemNm,
        String arrDttm,
        String wkFshDt,
        String wkFshHh,
        String timeStand,
        String al170BasePdlvNo,
        String dptuPdlvNo,
        String arvPdlvNo,
        String mvSisock,
        String al170MvDistance,
        String al170MvTime,
        String al170MvFee,
        String orgShpNm,
        String orgShpAdd,
        String chuljangCd,
        String chuljangAdd,
        String strShpNm,
        String strShpAdd,
        String endShpNm,
        String endShpAdd,
        String mvDistance,
        String mvGrd,
        String mvGrdAmt,
        String mvTime,
        String wrkGrd,
        String wrkGrdAmt,
        String strIslandYn,
        String endIslandYn
    ) {}
}
