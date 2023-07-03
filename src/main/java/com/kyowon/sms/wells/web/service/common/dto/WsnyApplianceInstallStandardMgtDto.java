package com.kyowon.sms.wells.web.service.common.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;

/**
 *
 * <pre>
 * K-W-SV-U-0303M01 환경가전 설치기준 관리
 * </pre>
 *
 * @author 이재훈
 * @since 2023-06-22
 */
public class WsnyApplianceInstallStandardMgtDto {

    @ApiModel(value = "WsnyApplianceInstallStandardMgtDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String dgr1ClsfCd,
        @NotBlank
        String dgr2ClsfCd
    ) {}

    @ApiModel(value = "WsnyApplianceInstallStandardMgtDto-SearchRes")
    public record SearchRes(
        String dgr1ClsfCd,
        String dgr2ClsfCd,
        String baseCn11,
        String baseCn12,
        String baseCn13,
        String baseCn21,
        String baseCn22,
        String baseCn23,
        String baseCn24,
        String baseCn25,
        String baseCn31,
        String baseCn41,
        String baseCn42,
        String baseCn43,
        String baseCn44,
        String baseCn51
    ) {}

    @ApiModel(value = "WsnyApplianceInstallStandardMgtDto-SaveReq")
    public record SaveReq(
        String dgr1ClsfCd,
        String dgr2ClsfCd,
        String baseCn,
        String hclsfCd,
        String lclsfCd
    ) {}

}
