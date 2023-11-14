package com.kyowon.sms.wells.web.service.visit.dto;

import io.swagger.annotations.ApiModel;

public class WsnbTotAsRtSdingPsDto {
    @ApiModel(value = "WsnbTotAsRtSdingPsDto-SearchReq")
    public record SearchReq(
        String baseY,           // 기준년도
        String svType,          // 서비스유형
        String badDivide,       // 불량구분
        String sdingPkgGrpCd,   // 모종패키지
        String sdingCausNm,       // 모종
        String sdingPkgCd       // 모종
    ){}

    @ApiModel(value = "WsnbTotAsRtSdingPsDto-SearchRes")
    public record SearchRes(
        String atcNm,
        String nm,
        String currentYear,
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
        Integer tcnt,
        Integer maxval,
        Integer minval,
        Integer avg
    ){}

    @ApiModel(value = "WsnbTotAsRtSdingPsDto-SdingPackageRes")
    public record SdingPackageRes(
        String pkgCode,
        String pkgCodeName
    ){}

    @ApiModel(value = "WsnbTotAsRtSdingPsDto-SdingDtlReq")
    public record SdingDtlReq(
        String pkgCode
    ){}

    @ApiModel(value = "WsnbTotAsRtSdingPsDto-SdingDtlRes")
    public record SdingDtlRes(
        String code,
        String codeName
    ){}
}
