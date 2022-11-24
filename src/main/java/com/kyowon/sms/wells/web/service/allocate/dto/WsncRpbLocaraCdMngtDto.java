package com.kyowon.sms.wells.web.service.allocate.dto;

import io.swagger.annotations.ApiModel;

public class WsncRpbLocaraCdMngtDto {

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
        String newAdrZip, /* 신주소우편번호 */
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
        String dtaDlYn, /* 데이터삭제여부 */
        String applyDate
    ) {}
}
