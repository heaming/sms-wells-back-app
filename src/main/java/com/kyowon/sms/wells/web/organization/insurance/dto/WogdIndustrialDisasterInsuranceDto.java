package com.kyowon.sms.wells.web.organization.insurance.dto;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

import javax.validation.constraints.NotBlank;

/**
 * <pre>
 * 산재보험
 * </pre>
 *
 * @author 이한울
 * @since 2023-04-27
 */
public class WogdIndustrialDisasterInsuranceDto {
    @ApiModel("WogdIndustrialDisasterInsuranceDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String baseYm,
        String prtnrNo,
        @NotBlank
        String bzStatCd
    ) {
    }

    /**
     * @param level2Nm          2레벨조직명
     * @param ogCd              조직코드
     * @param prtnrKnm          파트너명
     * @param prtnrNo           파트너번호
     * @param pstnDvCd          직책구분코드
     * @param cltnDt            해약일자
     * @param inddInsrDdctam    공제금액
     * @param inddInsrUcamAmt
     * @param inddInsrFnlCnfmYn 확정여부
     */
    @ApiModel("WogdIndustrialDisasterInsuranceDto-SearchRes")
    public record SearchRes(
        String level2Nm,
        String ogCd,
        String prtnrKnm,
        String prtnrNo,
        String pstnDvCd,
        String cltnDt,
        String inddInsrDdctam,
        String inddInsrUcamAmt,
        String inddInsrFnlCnfmYn,
        String ogTpCd,
        String dsbYm
    ) {
    }

    @ApiModel("WogdIndustrialDisasterInsuranceDto-EditReq")
    public record EditReq(
        @NotBlank
        String prtnrNo,
        @NotBlank
        String ogTpCd,
        @NotBlank
        String dsbYm,
        String dtaDlYn,
        String inddInsrFeeAmt,
        String inddInsrUcamAmt
    ) {
    }

    @ApiModel("WogdIndustrialDisasterInsuranceDto-RemoveReq")
    public record RemoveReq(
        @NotBlank
        String prtnrNo,
        @NotBlank
        String ogTpCd,
        @NotBlank
        String dsbYm
    ) {
    }

    @Builder
    @ApiModel("WogdIndustrialDisasterInsuranceDto-SearchPrtnrReq")
    public record SearchPrtnrReq(
        String prtnrNo,
        String ogTpCd,
        String dsbYm
    ) {
    }
}
