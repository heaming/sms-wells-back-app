package com.kyowon.sms.wells.web.service.visit.dto;

import io.swagger.annotations.ApiModel;

public class WsnbIstYOneAcuAsRtPsDto {
    @ApiModel(value = "WsnbIstYOneAcuAsRtPsDto-SearchReq")
    public record SearchReq(
        String baseY,
        String svType,      // 서비스유형
        String badDivide,   // 불량구분
        String pdGrp,       // 상품그룹
        String pdCd         // 상품명
    ){}

    @ApiModel(value = "WsnbIstYOneAcuAsRtPsDto-SearchRes")
    public record SearchRes(
        String atcNm,
        String dYear,
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
        String totalCnt,
        String maxVal,
        String minVal,
        String avgVal
    ){}
}
