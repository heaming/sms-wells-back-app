package com.kyowon.sms.wells.web.service.visit.dto;

import io.swagger.annotations.ApiModel;

public class WsnbIstYOneAcuAsRtSdingPsDto {
    @ApiModel(value = "WsnbIstYOneAcuAsRtSdingPsDto-SearchReq")
    public record SearchReq(
        String baseY,           // 기준년도
        String svType,          // 서비스유형
        String badDivide,       // 불량구분
        String sdingPkgGrpCd,   // 모종패키지
        String sdingCausNm,     // 모종
        String sdingPkgCd       // 모종..ex)917L 등등
    ){}

    @ApiModel(value = "WsnbIstYOneAcuAsRtSdingPsDto-SearchRes")
    public record SearchRes(
        String nm,
        Integer acol1,
        Integer acol2,
        Integer acol3,
        Integer acol4,
        Integer acol5,
        Integer acol6,
        Integer acol7,
        Integer acol8,
        Integer acol9,
        Integer acol10,
        Integer acol11,
        Integer acol12,
        Integer maxval,
        Integer minval,
        Integer tcnt,   // 총갯수
        Integer avg     // 평균
    ){}
}
