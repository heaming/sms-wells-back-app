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
        String prtnrNm,      /* 성명 */
        String baseDateFrom,
        String baseDateTo
    ) {}

    @ApiModel(value = "WsncBsServicePresentStateDto-SearchResList")
    public record SearchResList(
        String asnOjYm,      /* 관리년월 */
        String ogId,      /* 관리년월 */
        String ogNm,      /* 소속 */
        String bldNm,    /* 상주빌딩 */
        String prtnrNo,     /* 사번 */
        String prtnrKnm,     /* 성명 */
        String pstnDvCdNm,    /* 직급 */
        String mngtAcc,        /* 관리 */
        String vstAcc,         /* 방문 */
        String fshAcc,         /* 완료 */
        String svcProc,     /* 처리율(%) */
        String mngtSchd   /* B/S 관리일정 */
    ) {}

    @ApiModel(value = "WsncBsServicePresentStateDto-SearchResInfo")
    public record SearchResInfo(
        String mngtAcc,
        String vstAcc,
        String fshAcc,
        String svcProc
    ) {}
}
