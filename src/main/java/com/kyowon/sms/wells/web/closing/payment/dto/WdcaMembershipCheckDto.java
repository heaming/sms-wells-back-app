package com.kyowon.sms.wells.web.closing.payment.dto;

import io.swagger.annotations.ApiModel;

// TODO: 테이블 미정의
public class WdcaMembershipCheckDto {
    @ApiModel(value = "WdcaMembershipCheckDto-SearchRes")
    public record SearchRes(
        String col1,
        String col2,
        String col3,
        String col4,
        String col5,
        String col6,
        String col7,
        String col8,
        String col9,
        String col10,
        String col11
    ) {}

    @ApiModel(value = "WdcaMembershipCheckDto-SearchAfterRes")
    public record SearchAfterRes(
        String col1,
        String col2,
        String col3,
        String col4,
        String col5,
        String col6,
        String col7,
        String col8,
        String col9,
        String col10,
        String col11,
        String col12,
        String col13,
        String col14,
        String col15
    ) {}
}
