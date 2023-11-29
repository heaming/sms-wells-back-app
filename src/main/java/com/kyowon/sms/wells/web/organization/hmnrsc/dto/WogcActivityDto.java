package com.kyowon.sms.wells.web.organization.hmnrsc.dto;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WogcActivityDto {
    /**
     * 월별 활동 현황 정보조회 Request Dto
     *
     * @param baseYm      관리년월
     * @param ogTpCd      조직유형
     * @param rsbDvCd     직책구분
     * @param ogLevlDvCd1 1차레벨조직ID
     * @param ogLevlDvCd2 2차레벨조직ID
     * @param ogLevlDvCd3 3차레벨조직ID
     * @param qlfDvCd     자격구분
     */
    @ApiModel(value = "WogcActivityDto-SearchMonthlyActivityReq")
    @Builder
    public record SearchMonthlyActivityReq(
        String baseYm, /* 관리년월 */
        String ogTpCd, /* 조직유형 */
        String rsbDvCd, /* 직책구분 */
        String ogLevlDvCd1,
        String ogLevlDvCd2,
        String ogLevlDvCd3,
        String qlfDvCd, /* 자격구분 */
        String feamFlag /*순주문 관련 조회Flag */
    ) {
    }

    @ApiModel(value = "WogcActivityDto-SearchMonthlyActivityRes")
    public record SearchMonthlyActivityRes(
        String baseYm,
        String ogCd,
        String prtnrKnm,
        String prtnrNo,
        String pstnDvCd,
        String sexDvCd,
        String bldCd,
        String bldNm,
        String cntrDt,
        String prfmtDt,
        String fnlCltnDt,
        String hooPrtnrNo,
        String qlfDvCd,
        String akcdq0Sunju,
        String akcdq0,
        String akcdq0Sulchi,
        String akcdq0Sub,
        String akcdq1Sunju,
        String akcdq1,
        String akcdq1Sulchi,
        String lccnt0,
        String lccnt1,
        String metgPrscDc,
        String metgPrscDcYn,
        String edu11Yn,
        String edu17Yn,
        String lcvcnt,
        String fnlCltnDtYn,
        String lccn47

    ) {
    }

    /**
     * 누적 활동 현황 정보조회 Request Dto
     *
     * @param baseYm      관리년월
     * @param ogTpCd      조직유형
     * @param qlfDvCd     자격구분
     * @param ogLevlDvCd1 1차레벨조직ID
     * @param ogLevlDvCd2 2차레벨조직ID
     * @param ogLevlDvCd3 3차레벨조직ID
     * @param prtnrNo     파트너번호
     * @param prtnrKnm     파트너이름
     * @param perfCd     실적구분
     */
    @ApiModel(value = "WogcActivityDto-SearchAccureActivityReq")
    @Builder
    public record SearchAccureActivityReq(
        String baseYm, /* 관리년월 */
        String ogTpCd, /* 조직유형 */
        String qlfDvCd, /* 자격구분 */
        String ogLevlDvCd1,
        String ogLevlDvCd2,
        String ogLevlDvCd3,
        String prtnrNo, /* 파트너번호 */
        String prtnrKnm, /* 파트너이름 */
        String perfCd /* 실적구분 */
    ) {
    }

    /**
     * 누적 활동 현황 Response Dto
     *
     * @param mngtYm       관리년월
     * @param ojOgTpCd     배출조직유형
     * @param ojOgTpNm     배출조직유형명
     * @param ojPrtnrNo    피배출번호
     * @param ojPrtnrKnm   피배출자성명
     * @param ojPstnDvCd   피배출자직급
     * @param ojOgCd       피배출자소속
     * @param basePrtnrNo  배출번호
     * @param basePrtnrKnm 배출자성명
     * @param basePstnDvCd 배출자직급
     * @param baseOgCd     배출자소속
     * @param ejtYm        배출년월
     * @param ejtNmnN      배출차월
     * @param ackmtYn      인정여부
     * @param ogEjtGdCd    배출등급
     */
    @ApiModel(value = "WogcActivityDto-SearchAccureActivityRes")
    public record SearchAccureActivityRes(
        String mngtYm,
        String ojOgTpCd,
        String ojOgTpNm,
        String ojPrtnrNo,
        String ojPrtnrKnm,
        String ojPstnDvCd,
        String ojOgCd,
        String basePrtnrNo,
        String basePrtnrKnm,
        String basePstnDvCd,
        String baseOgCd,
        String ejtYm,
        String ejtNmnN,
        String ackmtYn,
        String ogEjtGdCd
    ) {
    }
}
