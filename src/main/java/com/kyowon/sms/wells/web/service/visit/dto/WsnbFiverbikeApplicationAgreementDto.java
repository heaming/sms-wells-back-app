package com.kyowon.sms.wells.web.service.visit.dto;

import io.swagger.annotations.ApiModel;

import javax.validation.constraints.NotBlank;

/**
 * <pre>
 * W-SV-U-0150P01 피버바이크 신청동의서 화면
 * </pre>
 *
 * @author yeonghwa.cheon
 * @since 2023.06.01
 */
public class WsnbFiverbikeApplicationAgreementDto {

    @ApiModel(value = "WsnbFiverbikeApplicationAgreementDto-SearchReq")
    public record SearchReq(
        String cntrNo,
        String cntrSn
    ) {}

    @ApiModel(value = "WsnbFiverbikeApplicationAgreementDto-SearchRes")
    public record SearchRes(
        String agreeYn, // 동의 여부
        String agreeCstNm, // 동의자명
        String signImage, // 사인이미지
        String agreeDate, // 동의일자
        String custNm // 계약고객명
    ) {}

    @ApiModel(value = "WsnbFiverbikeApplicationAgreementDto-CreateReq")
    public record CreateReq(
        @NotBlank
        String cntrNo,
        @NotBlank
        String cntrSn,
        String agreeYn, // 동의 여부
        String custNm, // 동의자명
        String signImage // 사인이미지
    ) {}

}
