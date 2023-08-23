package com.kyowon.sms.wells.web.service.stock.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.sds.sflex.system.config.validation.validator.ValidDate;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

/**
 * <pre>
 * W-SV-U-01261M01 필터 종류별 출고내역 dto
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-08-09
 */

public class WsnaShippingByFilterTypeDto {

    @Builder
    @ApiModel("WsnaShippingByFilterTypeDto-SearchPdRes")
    public record SearchPdRes(

        String pdCd,
        String pdNm,
        String itmGrCd

    ) {}

    @Builder
    @ApiModel("WsnaShippingByFilterTypeDto-SearchReq")
    public record SearchReq(

        @NotBlank
        @ValidDate
        String strtDt,
        @NotBlank
        @ValidDate
        String endDt,
        @NotBlank
        String wareDvCd,
        String gbYn,
        String svBizHclsfCd,
        String hgrWareNo,
        String wareNo,
        String itmGrCd,
        String itmPdCd,

        @ValidDate
        String ostrConfDt

    ) {}

    @Builder
    @ApiModel("WsnaShippingByFilterTypeDto-SearchRes")
    public record SearchRes(

        String cstSvAsnNo,
        int wkOstrSn,
        String ogNm,
        String prtnrNo,
        String prtnrKnm,
        String stkrPrntYn,
        String rmkCn,
        String cntrNo,
        int cntrSn,
        String cntrDtlNo,
        String cstKnm,
        String basePdNm,
        String ostrConfDt,
        String fnlVstFshDt,
        String itmPdCd,
        String itmPdNm,
        String sellTpNm,
        String svBizHclsfNm,
        String svBizDclsfNm,
        String refriDvNm,
        String adrZip,
        String cstAdr

    ) {}

    @Builder
    @ApiModel("WsnaShippingByFilterTypeDto-EditReq")
    public record EditReq(

        @NotBlank
        String cstSvAsnNo,
        @Positive
        int wkOstrSn,
        String stkrPrntYn,
        @Size(max = 4000)
        String rmkCn,
        @ValidDate
        String ostrConfDt
    ) {}

}
