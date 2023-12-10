package com.kyowon.sms.wells.web.service.common.dto;

import io.swagger.annotations.ApiModel;

import javax.validation.constraints.NotBlank;

public class WsnyCalendarDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    @ApiModel(value = "WsnyCalendarDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String baseYm, /* 기준년월 */
        String serviceCenterCd, /* 서비스센터CD */
        String serviceCenterOgId /* 서비스센터조직ID */
    ) {}

    @ApiModel(value = "WsnyCalendarDto-FineRegReq")
    public record FineRegReq(
        @NotBlank
        String svCnrOgId, /* 서비스센터조직ID */
        @NotBlank
        String baseY, /* 기준년 */
        @NotBlank
        String baseMm, /* 기준월 */
        @NotBlank
        String baseD /* 기준일 */
    ) {}

    @ApiModel(value = "WsnyCalendarDto-SaveRegReq")
    public record SaveRegReq(
        @NotBlank
        String svCnrOgId, /* 서비스센터조직ID */
        @NotBlank
        String baseY, /* 기준년 */
        @NotBlank
        String baseMm, /* 기준월 */
        @NotBlank
        String baseD, /* 기준일 */
        String dfYn, /* 휴무여부 */
        String rmkCn, /* 비고 */
//        String bndtWrkPsicNo,
        String bndtWrkPsicNoPrtnrNo, /* 당직자파트너번호 */
        String bndtWrkPsicNoOgTpCd /* 당직자조직유형코드 */
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    @ApiModel(value = "WsnyCalendarDto-SearchRes")
    public record SearchRes(
        String baseY, /* 기준년 */
        String baseMm, /* 기준월 */
        String baseD, /* 기준일 */
        String dowDvCd, /* 요일구분코드 */
        String dfYn, /* 휴무여부 */
        String phldYn, /* 공휴일여부 */
        String rmkCn, /* 비고 */
        String wrkStrtHh, /* 근무시작시간 */
        String wrkEndHh, /* 근무종료시간 */
        String restHh, /* 휴식시간 */
        String wrkHh, /* 근무시간 */
        String ogTpCd, /* 조직유형코드 */
        String bndtWrkPsicNo, /* 당직자파트너번호 */
        String bndtWrkPsicNm, /* 당직자파트너명 */
        String svCnrOgId, /* 서비스센터조직ID */
        String svCnrOgCd /* 서비스센터조직CD */
    ) {}

    @ApiModel(value = "WsnyCalendarDto-FindRegRes")
    public record FindRegRes(
        String baseY, /* 기준년 */
        String baseMm, /* 기준월 */
        String baseD, /* 기준일 */
        String dfYn, /* 휴무여부 */
        String rmkCn, /* 비고 */
        String svCnrOgId, /* 서비스센터조직ID */
        String bndtWrkPsicNo /* 당직자파트너번호 */
    ) {}
}
