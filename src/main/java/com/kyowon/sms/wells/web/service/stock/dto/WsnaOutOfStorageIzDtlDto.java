package com.kyowon.sms.wells.web.service.stock.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;

import com.sds.sflex.system.config.validation.validator.ValidDate;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

/**
 * <pre>
 * W-SV-U-0144M01 출고내역상세 관리
 * </pre>
 *
 * @author inho.choi
 * @since 2023-02-14
 */
public class WsnaOutOfStorageIzDtlDto {

    @Builder
    @ApiModel("WsnaOutOfStorageIzDtlDto-SearchPdRes")
    public record SearchPdRes(
        String pdCd,
        String pdNm,
        String itmKndCd
    ) {}

    @ApiModel(value = "WsnaOutOfStorageIzDtlDto-SearchReq")
    public record SearchReq(
        @NotBlank
        @ValidDate
        String stOstrDt,
        @NotBlank
        @ValidDate
        String edOstrDt,
        String ostrTpCd,

        @NotBlank
        String ostrWareDvCd,
        String ostrHgrWareNo,
        String ostrWareNo,
        @NotBlank
        String strWareDvCd,
        String strHgrWareNo,
        String strWareNo,
        String itmPdCd,
        String itmGdCd,
        String itmKndCd,
        String useYn
    ) {}

    @ApiModel(value = "WsnaOutOfStorageIzDtlDto-SearchRes")
    public record SearchRes(
        String strWareNm,
        String strPrtnrNo,
        String ostrDt,
        String sapMatCd,
        String itmPdCd,
        String pdAbbrNm,
        String ostrTpCd,
        String mngtUnitNm,
        String itmGdNm,
        BigDecimal ostrQty,
        BigDecimal boxQty,
        String llshcs,
        String ostrWareNm,
        String strRgstDt,
        String ostrAkDtlNo,
        String strDtlNo,
        String ostrDtlNo
    ) {}
}
