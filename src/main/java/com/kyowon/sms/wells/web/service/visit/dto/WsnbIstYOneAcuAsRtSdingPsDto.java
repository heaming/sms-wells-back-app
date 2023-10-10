package com.kyowon.sms.wells.web.service.visit.dto;

import io.swagger.annotations.ApiModel;

public class WsnbIstYOneAcuAsRtSdingPsDto {
    @ApiModel(value = "WsnbIstYOneAcuAsRtSdingPsDto-SearchReq")
    public record SearchReq(
        String baseY,           // 기준년도
        String svType,          // 서비스유형
        String badDivide,       // 불량구분
        String sdingPkgGrpCd,   // 모종패키지
        String sdingPkgCd       // 모종
    ){}

    @ApiModel(value = "WsnbIstYOneAcuAsRtSdingPsDto-SearchRes")
    public record SearchRes(
        String atcNm,
        String m01,
        String m02,
        String m03,
        String m04,
        String m05,
        String m06,
        String m07,
        String m08,
        String m09,
        String m10,
        String m11,
        String m12,
        String totalCnt
    ){}
}
