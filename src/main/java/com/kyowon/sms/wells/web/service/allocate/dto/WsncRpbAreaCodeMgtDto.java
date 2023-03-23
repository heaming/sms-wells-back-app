package com.kyowon.sms.wells.web.service.allocate.dto;

import io.swagger.annotations.ApiModel;

import javax.validation.constraints.NotBlank;

/**
 * <pre>
 * W-SV-U-0035M01 책임지역 지역코드 관리
 * </pre>
 *
 * @author gs.piit129 천영화
 * @since 2022.11.22
 */
public class WsncRpbAreaCodeMgtDto {

    @ApiModel(value = "WsncRpbLocaraCdMngtDto-SearchReq")
    public record SearchReq(
        String zipFrom, /* 우편번호 From */
        String zipTo, /* 우편번호 To */
        String fr2pLgldCd, /* 법정동코드 앞2자리 */
        String ctpvNm, /* 시도명 */
        String ctctyNm, /* 시군구명 */
        String ogId, /*서비스센터ID */
        String wrkGrpCd, /* 작업그룹코드 */
        String applyDate, /* 적용일자 */
        String locaraCdFrom, /* 지역코드 From */
        String locaraCdTo /* 지역코드 To */
    ) {}

    @ApiModel(value = "WsncRpbLocaraCdMngtDto-SearchRes")
    public record SearchRes(
        String fr2pLgldCd, /* 법정동코드 앞2자리 */
        String zipList, /* 우편번호 List */
        String mgtCnt, /* 지역별 서비스 계정 수 */
        String wrkCnt, /* 월별 수임 건수 (조회월 이전 3개월 평균) */
        String ctpvNm, /* 시도명 */
        String ctctyNm, /* 시군구명 */
        String lawcEmdNm, /* 법정읍면동명 */
        String amtdNm, /* 행정동명 */
        String rpbLocaraCd, /* 책임지역코드 */
        String rpbLocaraGrpCd, /* 책임지역그룹코드 */
        String ogNm, /* 조직명 */
        String ichrPrtnrNo, /* 담당파트너번호 */
        String prtnrKnm, /* 파트너한글명 */
        String vstDowVal, /* 방문요일값 */
        String apyStrtdt, /* 적용시작일자 */
        String apyEnddt, /* 적용종료일자 */
        String fnlMdfcDtm /* 최종수정일시 */
    ) {}

    @ApiModel(value = "WsncRpbLocaraCdMngtDto-SaveReq")
    public record SaveReq(
        @NotBlank
        String chLocaraCd, /* 변경 책임지역코드 */
        String fr2pLgldCd, /* 법정동코드 앞2자리 */
        String ctpvNm, /* 시도명 */
        String ctctyNm, /* 시군구명 */
        String lawcEmdNm, /* 법정읍면동명 */
        String amtdNm, /* 행정동명 */
        @NotBlank
        String apyStrtdt, /* 적용시작일자 */
        @NotBlank
        String apyEnddt /* 적용종료일자 */

    ) {}
}
