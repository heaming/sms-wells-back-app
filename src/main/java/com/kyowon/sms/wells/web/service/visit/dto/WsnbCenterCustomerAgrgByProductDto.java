package com.kyowon.sms.wells.web.service.visit.dto;

import io.swagger.annotations.ApiModel;

public class WsnbCenterCustomerAgrgByProductDto {
    @ApiModel(value = "WsnbCenterCustomerAgrgByProductDto-SearchReq")
    public record SearchReq(
        String baseYm,
        String sellTpCd,
        String totalCsmr
    ) {}

    @ApiModel(value = "WsnbCenterCustomerAgrgByProductDto-SearchRes")
    public record SearchRes(
        String sapMatCd,
        String pdCd,
        String pdNm,
        Integer S71350,
        Integer S71351,
        Integer S71352,
        Integer S71353,
        Integer S71354,
        Integer S71355,
        Integer S71356,
        Integer S71357,
        Integer S71358,
        Integer S71359,
        Integer S71360,
        Integer S71361,
        Integer S71362,
        Integer S71363,
        Integer S71364,
        Integer S71365,
        Integer S71366,
        Integer S71367,
        Integer S71368,
        Integer S71369,
        Integer S71386,
        Integer S71387,
        Integer S71448,
        Integer S71447,
        Integer total
    ) {}

}
