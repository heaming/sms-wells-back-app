package com.kyowon.sms.wells.web.service.stock.dto;

import java.math.BigDecimal;

import javax.validation.constraints.Max;
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
        @Max(999999999999L)
        BigDecimal cnt,
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
        String mngtUnit,
        String matGdCd,

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

        BigDecimal bsFullQty,
        BigDecimal bsAsnQty,
        BigDecimal stockOgQty,
        BigDecimal stockIndvQty,
        BigDecimal nedQty,
        BigDecimal boxUnitQty,

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
        String asnOjYm,
        BigDecimal asnTnN,
        String strWareNo,
        String ostrWareNo,
        String itmPdCd,
        String wareDvCd,
        String wareDtlDvCd,
        String sppDvCd,
        String wareMngtPrtnrNo,
        String ogTpCd,
        String bldCd,
        String adrId,
        String matGdCd,
        BigDecimal geAsnQomCt,
        BigDecimal crpAsnQomCt,
        BigDecimal woAsnQomCt,
        BigDecimal etnWtcfApyQty,
        BigDecimal mcbyAcuOstrQty,
        BigDecimal crtlStocQty,
        BigDecimal thwkExpQty,
        BigDecimal borrExpQty,
        BigDecimal cnfmQty,
        BigDecimal boxUnitQty,
        BigDecimal aclOstrQty,
        BigDecimal bfsvcFshCt

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
