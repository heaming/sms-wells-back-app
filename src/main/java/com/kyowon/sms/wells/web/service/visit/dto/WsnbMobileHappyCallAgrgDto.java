package com.kyowon.sms.wells.web.service.visit.dto;

import io.swagger.annotations.ApiModel;

public class WsnbMobileHappyCallAgrgDto {
    @ApiModel("WsnbMobileHappyCallAgrgDto-SearchReq")
    public record SearchReq(
        String svBizHclsfCd,
        String searchDateFrom,
        String searchDateTo,
        String ogId,
        String engId
    ) {}
    @ApiModel("WsnbMobileHappyCallAgrgDto-SearchRes")
    public record SearchRes(

        String ogNm, // 서비스센터
        //        String wkcr, // 조
        String prtnrNo, // 사번
        String prtnrKnm, // 성명
        String cntrDt, // 입사일
        String svBizHclsfCd, // 서비스유형
        Integer synthAvg, // 평균
        Integer synthAvgRank, // 상위
        String synthAvgGrd, // 등급
        Integer synthAvgGrdScore, // 점수
        Integer rplyCnt, // 응답건
        Integer rplyCntRank, // [응답건]상위
        String rplyCntGrd, // [응답건]등급
        Integer rplyCntGrdScore, // [응답건]점수
        Integer rplyPer, // 응답율
        Integer rplyPerRank, // [응답율]상위
        String rplyPerGrd, // [응답율]등급
        Integer rplyPerGrdScore, // [응답율]점수
        Integer hpcallAvg, // 해피콜
        Integer hpcallAvgRank, // [해피콜] 상위
        String hpcallAvgGrd, // [해피콜] 등급
        Integer hpcallAvgGrdScore, // [해피콜] 점수
        Integer trsCnt, // 발송건
        Integer compCnt, // 처리건
        Integer hpcallScore // 해피콜 건수
        //        Integer envrElhmCnt, // 환경가전 건수
        //        Integer sdingSpcltCnt, // 모종전문 건수
        //        Integer hcrCnt, // 홈케어 건수
        //        Integer lgszElhmCnt, // 대형가전 건수
        //        Integer mdimRprCnt, // 중수리 건수
        //        Integer grdRplyCnt, // 응답건 그리드용 건수
        //        Integer acpnCnt // 동행 건수
    ) {}
}
