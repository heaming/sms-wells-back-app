package com.kyowon.sms.wells.web.service.stock.dto;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.sds.sflex.system.config.validation.validator.ValidDate;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

/**
 * <pre>
 * W-SV-U-0192M01 개인창고출고관리
 * </pre>
 *
 * @author songTaeSung
 * @since 2023.02.16
 */
public class WsnaIndividualWareOstrDto {

    @Builder
    @ApiModel("WsnaIndividualWareOstrDto-SearchWareReq")
    public record SearchWareReq(
        @NotBlank
        String apyYm,
        @NotBlank
        String asnOjYm,
        @NotBlank
        String ostrWareNo,
        @NotBlank
        String wareDvCd,
        @NotBlank
        String wareDtlDvCd,
        @NotBlank
        String hgrDvCd,
        @Positive
        int cnt,

        String hgrStrWareNo

    ) {}

    @Builder
    @ApiModel("WsnaIndividualWareOstrDto-SearchPdRes")
    public record SearchPdRes(
        String pdCd,
        String pdNm,
        String itmKndCd
    ) {}

    @Builder
    @ApiModel("WsnaIndividualWareOstrDto-SearchReq")
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
        List<String> itmPdCds,

        @Positive
        @Max(999999999999L)
        BigDecimal totOutQty,

        @NotBlank
        String hgrStrWareNo,
        @NotBlank
        String strWareNo,

        String itmPdCd,
        String strtSapCd,
        String endSapCd
    ) {}

    @ApiModel("WsnaIndividualWareOstrDto-SaveReq")
    public record SaveReq(
        String sapMatCd,
        @NotBlank
        String itmPdCd,

        BigDecimal partUseQty,
        BigDecimal under20per,
        BigDecimal hgrCrtlStocQty,
        BigDecimal totOutQty,

        String mngtUnit,
        String matGdCd,
        BigDecimal logisticStocQty,
        BigDecimal logisticFilterQty,

        BigDecimal boxUnitQty,
        BigDecimal crtlStocQty,
        BigDecimal useQty,
        BigDecimal cnfmQty,
        BigDecimal cnfmBoxQty,
        BigDecimal aclOstrQty,
        BigDecimal aclOstrBoxQty,

        BigDecimal filterBoxQty,
        @Positive
        @Max(999999999999L)
        BigDecimal outQty,
        BigDecimal outBoxQty,

        @NotBlank
        String itmQomAsnNo,
        String asnOjYm,
        @NotBlank
        String ostrWareNo,
        @NotBlank
        String strWareNo,
        @NotBlank
        String wareMngtPrtnrNo,
        @NotBlank
        String ogTpCd,
        String itmKndCd,
        @Size(max = 4000)
        String rmkCn,
        @Positive
        BigDecimal asnTnN,
        @NotBlank
        String wareDvCd,
        String ostrAkNo,
        @Positive
        Integer ostrAkSn,

        @ValidDate
        @NotBlank
        String ostrDt

    ) {}
}
