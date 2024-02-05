package com.kyowon.sms.wells.web.service.visit.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;

public class WsnbIndividualVisitPrdInqrDto {

    @ApiModel("WsnbIndividualVisitPrdInqrDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String cntrNo,           // 계약번호
        @NotBlank
        String cntrSn            // 계약일련번호
    ) {}
    @ApiModel("WsnbIndividualVisitPrdInqrDto-SearchCustomerVisitIzRes")
    public record SearchCustomerVisitIzRes(
        String vstNmnN,          // 방문차월
        String istNmnN,          // 설치차월
        String vstDuedt,         // 예정일자
        String svBizDclsfCd,     // 작업구분코드
        String svBizDclsfNm,     // 작업구분명
        String filtChngLvCd,     // 단계
        String pdCd,             // 품목코드
        String pdNm,             // 필터명
        String wkDt,             // 방문일자
        String mtrCanDt          // 중지일자
    ) {}

    @ApiModel("WsnbIndividualVisitPrdInqrDto-SearchManagementCstInqrRes")
    public record SearchManagementCstInqrRes(
        String mngtYm,           // 관리년월
        String mngrDvNm,         // 구분
        String prtnrKnm,         // 성명
        String prtnrNo,          // 사번
        String mngerRglvlDvCd,   // 매니저급지구분코드
        String svHshdNo,         // 가구화번호
        String ogTpCd,           // 조직유형코드
        String ogCd,             // 소속
        String vstPrdNm          // 방문주기
    ) {}
}
