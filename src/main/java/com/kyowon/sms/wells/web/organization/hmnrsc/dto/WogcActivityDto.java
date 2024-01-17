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
        String lccn47,
        String prfmtMon,
        String ejtPersonCt

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
     * @param dgr1LevlOgNm          총괄단
     * @param dgr2LevlOgNm          지역단
     * @param ogCd                  소속
     * @param bldNm                 빌딩명
     * @param prtnrKnm              성명
     * @param prtnrNo               번호
     * @param qlfDvNm               자격
     * @param fstCntrDt             업무등록현황 - 최초등록
     * @param fnlCltnDt             업무등록현황 - 최종해약
     * @param rcntrDt               업무등록현황 - 재등록
     * @param minStrtdtM            웰스매니저 현황 - 최초개시
     * @param maxEnddtM             웰스매니저 현황 - 최종해약
     * @param maxStrtdtM            웰스매니저 현황 - 재개시
     * @param minUpgrYmP            수석플래너 현황 - 최초개시
     * @param fnlCltnDtP            수석플래너 현황 - 최종해약
     * @param maxDmtnYmP            수석플래너 현황 - 강등
     * @param cltnDtP               수석플래너 현황 - 해약
     * @param rgrPrtnrNo            수석플래너 신청자 - 번호
     * @param rgrPrtnrNm            수석플래너 신청자 - 성명
     * @param totSum                환경가전 실적 - 누적건수
     * @param m03Avg                환경가전 실적 - 직전 3개월 (평균)
     * @param m03Sum                환경가전 실적 - 직전 3개월
     * @param curSum                환경가전 실적 - 당월
     * @param ablSum                환경가전 실적 - 승급건수
     * @param edu96                 교육현황 - 스타트업(M)
     * @param edu14                 교육현황 - 플래너스타트업(P)
     * @param rcmdrCnt              채용현황 - 인원
     * @param preCnt                채용현황 - 전월실동
     * @param curCnt                채용현황 - 당월실동
     */
    @ApiModel(value = "WogcActivityDto-SearchAccureActivityRes")
    public record SearchAccureActivityRes(
        String dgr1LevlOgNm,
        String dgr2LevlOgNm,
        String ogCd,
        String bldNm,
        String prtnrKnm,
        String prtnrNo,
        String qlfDvNm,
        String fstCntrDt,
        String fnlCltnDt,
        String rcntrDt,
        String minStrtdtM,
        String maxEnddtM,
        String maxStrtdtM,
        String minUpgrYmP,
        String fnlCltnDtP,
        String maxDmtnYmP,
        String cltnDtP,
        String rgrPrtnrNo,
        String rgrPrtnrNm,
        String totSum,
        String m03Avg,
        String m03Sum,
        String curSum,
        String ablSum,
        String edu96,
        String edu14,
        String rcmdrCnt,
        String preCnt,
        String curCnt
    ) {
    }
}
