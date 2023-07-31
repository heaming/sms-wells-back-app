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

public class WsnaIndependenceWareOstrDto {

    @Builder
    @ApiModel("WsnaIndependenceWareOstrDto-SearchWareReq")
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
        @Positive
        int cnt
    ) {}

    @Builder
    @ApiModel("WsnaIndependenceWareOstrDto-SearchPdRes")
    public record SearchPdRes(
        String pdCd,
        String pdNm,
        String itmKndCd
    ) {}

    @Builder
    @ApiModel("WsnaIndependenceWareOstrDto-SearchReq")
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

        @NotBlank
        String wareDvCd,
        @NotBlank
        String wareDtlDvCd,
        @NotBlank
        String strWareNo,

        String itmPdCd,
        String strtSapCd,
        String endSapCd

    ) {}

    @Builder
    @ApiModel("WsnaIndependenceWareOstrDto-SaveReq")
    public record SaveReq(

        @NotBlank
        String itmPdCd,

        String mngtUnit,
        String matGdCd,

        BigDecimal boxUnitQty,

        @Positive
        @Max(999999999999L)
        BigDecimal outQty,

        @NotBlank
        String itmQomAsnNo,
        @NotBlank
        String asnOjYm,
        @NotBlank
        String ostrWareNo,
        @NotBlank
        String strWareNo,
        @NotBlank
        String wareMngtPrtnrNo,
        @NotBlank
        String ogTpCd,

        @Size(max = 4000)
        String rmkCn,
        @Positive
        BigDecimal asnTnN,
        @NotBlank
        String wareDvCd,

        String ostrAkNo,
        @Positive
        Integer ostrAkSn,
        @NotBlank
        @ValidDate
        String ostrDt,

        String ostrWareDvCd,
        String ostrPrtnrNo,
        String ostrPrtnrOgTpCd
    ) {}

    @Builder
    @ApiModel("WsnaIndependenceWareOstrDto-CreateReq")
    public record CreateReq(

        @NotBlank
        String asnOjYm,
        @Positive
        @Max(999999999999L)
        BigDecimal cnt,
        @NotBlank
        String ostrWareNo,

        @NotBlank
        String wareDvCd,

        @NotBlank
        String wareDtlDvCd

    ) {}
}
