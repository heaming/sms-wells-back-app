package com.kyowon.sms.wells.web.closing.sales.dto;

import io.swagger.annotations.ApiModel;

import javax.validation.constraints.NotBlank;

public class WdcbMonthlySalesVolumeTableDto {
    // *********************************************************
    // Result Dto
    // *********************************************************
    // 월 매출수량 집계표 Search Dto
    @ApiModel("WdcbMonthlySalesVolumeTableDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String sellTpCd,
        String slStartDt,
        String slEndDt,
        String sppmthdtpcd,
        String sellinflwchnldtlcd,
        String copndvcd
    ) {
    }

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 월 매출수량 집계표 Search Dto
    @ApiModel("WdcbMonthlySalesVolumeTableDto-SearchRes")
    public record SearchRentalRes(
        String sapMatCd,
        String basePdCd,
        String pdCd,
        String pdNm,
        String sellPrpChval
    ) {
    }

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 월 매출수량 집계표 Search Dto
    @ApiModel("WdcbMonthlySalesVolumeTableDto-SearchRes")
    public record SearchPaymentRes(
        String sapMatCd,     /* SAP상품코드 */
        String basePdCd,     /* 수불코드 */
        String pdcd,      /* 상품코드 */
        String pdnm      /* 상품명 */
    ) {
    }

}
