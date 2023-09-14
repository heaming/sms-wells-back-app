package com.kyowon.sms.wells.web.bond.consultation.dto;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WbncCustomerCenterHistoryDto {
    @Builder
    @ApiModel(value = "WbncCustomerCenterHistoryDto-FindRes")
    public record FindRes(
        String rownum,
        String cnslNo,
        String cnslDt,
        String cnslStDtTm,
        String cstNo,
        String jobCd,
        String jobNm,
        String centerCd,
        String centerNm,
        String sellTpCd,
        String cntrDtlNo,
        String cnslCn,
        String cstNm,
        String telNo,
        String cnslEdDt,
        String cnslEdTm,
        String callTpCd,
        String regDt,
        String regTm,
        String regUserId,
        String regUserNm,
        String modDt,
        String modTm,
        String modUserId,
        String modUserNm
    ) {}
}
