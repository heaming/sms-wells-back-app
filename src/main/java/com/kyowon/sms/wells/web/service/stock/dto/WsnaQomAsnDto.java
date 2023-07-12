package com.kyowon.sms.wells.web.service.stock.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

/**
 * <pre>
 * W-SV-U-0190M01, W-SV-U-0191M01 개인창고, 독립창고 물량배정 dto
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-07-11
 */

public class WsnaQomAsnDto {

    @Builder
    @ApiModel("WsnaQomAsnDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String apyYm,
        @NotBlank
        String asnOjYm,
        @Positive
        int cnt,
        @NotBlank
        String ostrWareNo,
        String itmKndCd,
        String itmPdCd,
        @NotBlank
        String wareDvCd,
        @NotBlank
        String wareDtlDvCd,
        String strWareNo
    ) {}

    @Builder
    @ApiModel("WsnaQomAsnDto-SearchRes")
    public record SearchRes(
        String sapCd,
        String itmPdCd,
        String itmPdNm,

        String wareNo,
        String prtnrNo,
        String prtnrNm,
        String wareNm,

        BigDecimal centerQty,
        BigDecimal geQty,
        BigDecimal crpQty,
        BigDecimal totalQty,
        BigDecimal apyQty,
        BigDecimal ostrQty,
        BigDecimal bsQty,
        BigDecimal stocQty,
        BigDecimal thwkQty,
        BigDecimal borrQty,
        BigDecimal cnfmQty,
        BigDecimal boxQty,

        String bldCd,
        String bldNm,
        String telNo,
        String adrZip,
        String rnadr,
        String rdadr

    ) {}

    @Builder
    @ApiModel("WsnaQomAsnDto-CreateReq")
    public record CreateReq(
        @NotBlank
        String apyYm,
        @NotBlank
        String asnOjYm,
        @Positive
        int cnt,
        @NotBlank
        String ostrWareNo,
        @NotBlank
        String wareDvCd,
        @NotBlank
        String wareDtlDvCd

    ) {}

    @Builder
    @ApiModel("WsnaQomAsnDto-EditWareRenewalReq")
    public record EditReq(
        @NotBlank
        String apyYm,
        @NotBlank
        String asnOjYm,
        @NotBlank
        String ostrWareNo

    ) {}

}
