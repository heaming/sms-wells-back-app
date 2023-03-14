package com.kyowon.sms.wells.web.service.visit.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;

/**
 * <pre>
 * W-SV-U-0031M01 상품별 서비스 처리 집계 현황
 * </pre>
 *
 * @author hyewon.kim 김혜원
 * @since 2022.12.30
 */
public class WsnbAsVisitPsDto {

    @ApiModel(value = "WsnbBpdSvProcsAgrgPsDto-SearchReq")
    public record SearchReq(
        String ogId, // 서비스센터
        @NotBlank
        String wkExcnDtFrom, // 처리일자 From
        @NotBlank
        String wkExcnDtTo, // 처리일자 To
        String refriDvCd // 유/무상 구분
    ) {}

    @ApiModel(value = "WsnbBpdSvProcsAgrgPsDto-SearchRes")
    public record SearchRes(
        String sapMatCd, // SAP코드
        String pdCd, // 상품코드
        String pdNm, // 상품약어명
        String siteAwPdGrpCd, // 현장수당상품그룹코드
        String cnt1110, // 신규설치
        String cnt1111, // 설치철거
        String cnt1121, // 자사회수
        String cnt1122, // 자사미회수
        String cnt1123, // 타사회수
        String cnt1124, // 타사미회수
        String cnt1125, // 자사분리
        String cnt1390, // 필터판매
        String cnt1100, // 설치소계
        String cnt2110, // BS
        String cnt2111, // BS
        String cnt2120, // BS
        String cnt2131, // BS
        String cnt2132, // BS
        String cnt2133, // BS
        String cnt2134, // BS
        String cnt2135, // BS
        String cnt2136, // BS
        String cnt2137, // BS
        String cnt2138, // BS
        String cnt2139, // BS
        String cnt2210, // BS
        String cnt2220, // BS
        String cnt2230, // BS
        String cnt2141, // BS
        String cnt2142, // BS
        String cnt2143, // BS
        String cnt2144, // BS
        String cnt2149, // BS
        String cnt2150, // BS
        String cnt2151, // BS
        String cnt2153, // BS
        String cnt2154, // BS
        String cnt2158, // BS
        String cnt2159, // BS
        String cnt2160, // BS
        String cnt2100, // B/S 소계
        String cnt3420, // 매출취소
        String cnt3420R, // 매출취소
        String cnt3410, // 상품취소
        String cnt3410R, // 상품취소
        String cnt3310, // 이전분리
        String cnt3320, // 이전재설치
        String cnt3210, // 제품원인
        String cnt3230, // 고객원인
        String cnt3110, // 제품A/S
        String cnt3112, // 특별A/S
        String cnt3121, // 필터B/S
        String cnt3122, // 필터판매
        String cnt3130, // 환경점검
        String cnt3440, // 회사설치
        String cnt3460, // 택배반품
        String cnt3199, // 기타
        String cnt3100, // A/S
        String cntt // 총계
    ) {}

}
