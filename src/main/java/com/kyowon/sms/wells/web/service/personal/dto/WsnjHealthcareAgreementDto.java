package com.kyowon.sms.wells.web.service.personal.dto;

import io.swagger.annotations.ApiModel;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class WsnjHealthcareAgreementDto {
    @ApiModel(value = "WsnjHealthcareAgreementDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String cntrNo,
        @NotBlank
        String cntrSn,
        @NotBlank
        String customerServiceCode
    ) {}

    @ApiModel(value = "WsnjHealthcareAgreementDto-SearchRes")
    public record SearchRes(
        String cntrNo,
        String cntrSn,
        String customerServiceCode,
        String customerServiceUseAgreeYn,
        String personalInfoAgreeYn,
        String notakForwardDate,
        String agreePersonName,
        String agreementImageContent

    ) {}

    @ApiModel(value = "WsnjHealthcareAgreementDto-MergeReq")
    public record MergeReq(
        @NotBlank
        @Length(max = 12)
        String cntrNo,
        @NotBlank
        String cntrSn,
        @NotBlank
        @Length(max = 2)
        String customerServiceCode,
        @NotBlank
        @Length(max = 1, min = 1)
        @Pattern(regexp = "^[Y|N]$")
        String customerServiceUseAgreeYn,
        @NotBlank
        @Length(max = 1, min = 1)
        @Pattern(regexp = "^[Y|N]$")
        String personalInfoAgreeYn,
        @NotBlank
        String agreePersonName,
        @NotBlank
        String agreementImageContent

    ) {}
}
