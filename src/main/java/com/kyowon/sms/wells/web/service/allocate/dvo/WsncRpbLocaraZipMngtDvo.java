/*
 ****************************************************************************************************
 * 프로그램 개요
 ****************************************************************************************************
 1. 모듈 : SNC (배정관리)
 2. 프로그램 ID : W-SV-U-0036M01 AS 책임지역 우편번호 관리
 3. 작성자 : gs.piit130
 4. 작성일 : 2022.11.17
 ****************************************************************************************************
 * 프로그램 설명
 ****************************************************************************************************
 - 책임지역 우편번호 관리 (http://localhost:3000/#/service/wwsnc-responsibility-local-area-zip-mgt)
 ****************************************************************************************************
 */
package com.kyowon.sms.wells.web.service.allocate.dvo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class WsncRpbLocaraZipMngtDvo {

    String newAdrZip; /* 신주소우편번호 */
    String mgtCnt; /* 지역별 서비스 계정 수 */
    String wrkCnt; /* 월별 수임 건수 (조회월 이전 3개월 평균) */
    String ctpvNm; /* 시도명 */
    String ctctyNm; /* 시군구명 */
    String lawcEmdNm; /* 법정읍면동명 */
    String amtdNm; /* 행정동명 */
    String mngtAmtd; /* 관리행정동명 */
    String rpbLocaraCd; /* 책임지역코드 */
    String rpbLocaraGrpCd; /* 책임지역그룹코드 */
    String ogNm; /* 조직명 */
    String ichrPrtnrNo; /* 담당파트너번호 */
    String prtnrKnm; /* 파트너한글명 */
    String vstDowVal; /* 방문요일값 */
    String apyStrtdt; /* 적용시작일자 */
    String apyEnddt; /* 적용종료일자 */
    String dtaDlYn; /* 데이터삭제여부 */
    String applyDate;

}
