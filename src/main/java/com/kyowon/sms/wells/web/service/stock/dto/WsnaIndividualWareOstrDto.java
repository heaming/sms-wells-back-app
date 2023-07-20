package com.kyowon.sms.wells.web.service.stock.dto;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

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

        @ValidDate
        String ostrDt,
        @NotBlank
        String hgrStrWareNo,
        @NotBlank
        String strWareNo,

        String itmPdCd,
        String strtSapCd,
        String endSapCd
    ) {}

    @ApiModel("WsnaIndividualWareOstrDto-CreateReq")
    public record CreateReq(
        String ostrAkNo,
        String ostrAkSn,
        String ostrAkTpCd,
        String ostrOjWareNo,
        String strOjWareNo,
        String ostrAkRgstDt,
        String strHopDt,
        String itmPdCd,
        String itmGdCd,
        String mngtUnitCd,
        String ostrAkQty,
        String rmkCn,
        String dtaDlYn
    ) {}
}
