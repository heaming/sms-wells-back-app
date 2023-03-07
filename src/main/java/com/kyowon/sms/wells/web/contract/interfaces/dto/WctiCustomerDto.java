package com.kyowon.sms.wells.web.contract.interfaces.dto;

import io.swagger.annotations.ApiModel;

public class WctiCustomerDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // 고객 통합 리스트 Search Request Dto
    @ApiModel("WctiCustomerDto-SearchReq")
    public record SearchReq(
        String CST_NM,
        String CRAL_LOCARA_TNO,
        String MEXNO,
        String CRAL_IDV_TNO,
        String LOCARA_TNO,
        String EXNO,
        String IDV_TNO,
        String CST_NO
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 고객 통합 리스트 Search Result Dto
    @ApiModel("WctiCustomerDto-SearchRes")
    public record SearchRes(
        String CST_DV,
        String CST_NO,
        String ITG_CST_NO,
        String CST_KNM,
        String CRAL_LOCARA_TNO,
        String MEXNO,
        String CRAL_IDV_TNO,
        String LOCARA_TNO,
        String EXNO,
        String IDV_TNO,
        String EMADR,
        String BRYY_MMDD,
        String SEX_DV_CD
    ) {}
}
