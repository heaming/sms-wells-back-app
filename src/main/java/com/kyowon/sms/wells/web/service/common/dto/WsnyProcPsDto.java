package com.kyowon.sms.wells.web.service.common.dto;

import io.swagger.annotations.ApiModel;

import javax.validation.constraints.NotBlank;

/**
 * <pre>
 * [W-SV-U-1301M01] 홈카드 - 처리 현황 DTO
 * <pre>
 *
 * @author 김동엽
 * @since 2023.11.16
 */
public class WsnyProcPsDto {
    /**
     * 홈카드 - 처리 현황 조회 요청 (Request)
     * @param rsbCd
     * @param searchType
     */
    @ApiModel(value = "WsnyProcPsDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String rsbCd,       // 직책코드
        @NotBlank
        String searchType   // 조회조건[D:일간/M:월간]
    ) {}

    /**
     * 홈카드 - 처리 현황 조회 결과 (Result)
     * @param procsRt
     * @param totCnt
     * @param wkCnt
     * @param istTotCnt
     * @param istWkCnt
     * @param bsTotCnt
     * @param bsWkCnt
     * @param asTotCnt
     * @param asWkCnt
     * @param hsTotCnt
     * @param hsWkCnt
     */
    @ApiModel(value = "WsnyProcPsDto-SearchRes")
    public record SearchRes(
        String procsRt,  // 처리율
        int totCnt,      // 전체건수
        int wkCnt,       // 처리건수
        int istTotCnt,   // 설치전체건수
        int istWkCnt,    // 설치처리건수
        int bsTotCnt,    // BS전체건수
        int bsWkCnt,     // BS처리건수
        int asTotCnt,    // AS전체건수
        int asWkCnt,     // AS처리건수
        int hsTotCnt,    // 홈케어전체건수
        int hsWkCnt      // 홈케어처리건수
    ) {}

    /**
     * 홈카드 오늘/내일 건수 조회
     * @param todayCnt
     * @param todayIstCnt
     * @param todayBsCnt
     * @param todayAsCnt
     * @param tommrCnt
     * @param tommrIstCnt
     * @param tommrBsCnt
     * @param tommrAsCnt
     */
    @ApiModel(value = "WsnyProcPsDto-SearchCnt")
    public record SearchCnt(
        int todayCnt,       // 오늘전체건수
        int todayIstCnt,    // 오늘설치건수
        int todayBsCnt,     // 오늘BS건수
        int todayAsCnt,     // 오늘AS건수
        int tommrCnt,       // 내일전체건수
        int tommrIstCnt,    // 내일설치건수
        int tommrBsCnt,     // 내일BS건수
        int tommrAsCnt      // 내일AS건수
    ) {}
}
