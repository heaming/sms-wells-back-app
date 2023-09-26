package com.kyowon.sms.wells.web.service.allocate.dto;

import io.swagger.annotations.ApiModel;

public class WsncBsServicePresentStateDto {
    @ApiModel(value = "WsncBsServicePresentStateDto-SearchReq")
    public record SearchReq(
        String mgtYnm,      /* 관리년월 */
        String mgtDept,     /* 총괄단 */
        String rgnlGrp,     /* 지역단 */
        String branch,      /* 지점 */
        String pstnDvCd,    /* 직급 */
        String prtnrNo,     /* 사번 */
        String prtnrNm      /* 성명 */
    ) {}

    @ApiModel(value = "WsncBsServicePresentStateDto-SearchResList")
    public record SearchResList(
        String asnOjYm,      /* 관리년월 */
        String dgr3LevlOgCd,      /* 소속 */
        String dgr3LevlOgNm,    /* 상주빌딩 */
        String prtnrNo,     /* 사번 */
        String prtnrKnm,     /* 성명 */
        String qlfDvCd,    /* 직급 */
        String pdctPdCd,        /* 관리 */
        String vstDuedt,         /* 방문 */
        String cntcDt,         /* 완료 */
        String compRate,     /* 처리율(%) */
        String npPtrm   /* B/S 관리일정 */
    ) {}

    @ApiModel(value = "WsncBsServicePresentStateDto-SearchResInfo")
    public record SearchResInfo(
        String ogCd,
        String ogNm,
        String prtnrNo,
        String sapDlpnrCd
    ) {}
}
