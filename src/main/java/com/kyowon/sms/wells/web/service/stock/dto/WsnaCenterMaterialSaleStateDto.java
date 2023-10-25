package com.kyowon.sms.wells.web.service.stock.dto;

import io.swagger.annotations.ApiModel;

/**
 * <pre>
 * 센터 자재별 매출현황
 * </pre>
 *
 * @author leeminwoo
 * @since 2023.10.24
 */
public class WsnaCenterMaterialSaleStateDto {

    @ApiModel(value = "WsnaCenterMaterialSaleStateDto-SearchReq")
    public record SearchReq(
        String startDt,
        String endDt,
        String wareDvCd,
        String wareNoM,
        String wareNoD,
        String wareDtlDvCd,
        String itmKndCd,
        String itmCdFrom,
        String itmCdTo

    ) {}

    @ApiModel(value = "WsnaCenterMaterialSaleStateDto-SearchRes")
    public record SearchRes(
        String sapMatCd,
        String pdCd,
        String pdNm,
        String fre,
        String recap,
        String ostrSum,
        String amtSumWon,
        String instl,
        String beforeService,
        String frisuAfterService,
        String fltr,
        String amtSum,
        String recapAfterService,
        String amt

    ) {}

}
