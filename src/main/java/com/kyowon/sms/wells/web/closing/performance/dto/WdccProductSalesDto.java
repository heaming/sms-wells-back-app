package com.kyowon.sms.wells.web.closing.performance.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import org.springframework.lang.Nullable;

import java.math.BigDecimal;

public class WdccProductSalesDto {
    @ApiModel("WdccProductSalesDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String baseDtFrom,
        @NotBlank
        String baseDtTo,
        String sellTpCd,
        String sellTpDtlCd,
        String inqrDv,
        String sellChnlDvCd,
        String sapPdDvCd
    ) {}

    @ApiModel("WdccProductSalesDto-SearchRes")
    public record SearchRes(
        String slRcogDt,
        String sellTpCd,
        String sellTpDtlCd,
        String pdCd,
        String pdNm,
        String sapPdDvCd,
        String sapPdDvNm,
        String sellQty,
        String sellAmt,
        String sellSplAmt,
        String sellAmtVat,
        String pvdaAmt,
        String chQty,
        String slChAmt,
        String chSplAmt,
        String chVat,
        String chPvdaAmt,
        String canQty,
        String slCanAmt,
        String canSplAmt,
        String canVat,
        String canPvdaAmt,
        String totQty,
        String totAmt,
        String totSplAmt,
        String totVat,
        String totPvdaAmt
    ) {}

    @ApiModel("WdccProductSalesDto-SearchRentalRes")
    public record SearchRentalRes(
        String slRcogDt,
        String sellTpCd,
        String sellTpDtlCd,
        String pdCd,
        String pdNm,
        String sapPdDvCd,
        String sapPdDvNm,
        String rentalRgstCost,
        String rentalRgstCostSpl,
        String rentalRgstCostVat,
        String nomSlAmt,
        String splAmt,
        String vat,
        String totSlAmt,
        String totSpl,
        String totVat
    ) {}

    @ApiModel("WdccProductSalesDto-SearchMembershipRes")
    public record SearchMembershipRes(
        String slRcogDt,
        String sellTpCd,
        String sellTpDtlCd,
        String sapPdDvCd,
        String sapPdDvNm,
        String sellAmt,
        String sellSplAmt,
        String sellAmtVat,
        String filSellAmt,
        String filSellSplAmt,
        String filSellAmtVat,
        String totSellAmt,
        String totSellSplAmt,
        String totSellAmtVat
    ) {}

    @ApiModel("WdccProductSalesDto-SearchSapMaterialsRes")
    public record SearchSapMaterialsRes(
        String pdctSlSumAmt,
        String pdctSlSumVat,
        String prdtSlSumAmt,
        String prdtSlSumVat,
        String svdtSlSumAmt,
        String svdtSlSumVat,
        String etcSlSumAmt,
        String etcSlSumVat
    ) {}
}
