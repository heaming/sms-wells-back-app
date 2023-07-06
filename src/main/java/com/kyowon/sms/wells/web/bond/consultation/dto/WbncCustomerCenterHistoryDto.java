package com.kyowon.sms.wells.web.bond.consultation.dto;

import org.apache.commons.lang.StringUtils;

import com.sds.sflex.common.utils.DbEncUtil;

import io.swagger.annotations.ApiModel;

public class WbncCustomerCenterHistoryDto {
    @ApiModel(value = "WbncCustomerCenterHistoryDto-FindRes")
    public record FindRes(
        String no,
        String cnslNo,
        String cnslDt,
        String cnslStDt,
        String cnslStTm,
        String cnslStDtTm,
        String cstNo,
        String jobCd,
        String jobNm,
        String centerCd,
        String centerNm,
        String sellTpCd,
        String cntrNo,
        String cntrSn,
        String cntrNoSn,
        String cnslCn,
        String cstNm,
        String cstTnoa,
        String cstTnob,
        String cstTnoc,
        String cnslEdDt,
        String cnslEdTm,
        String callTpCd,
        String callTpNm,
        String regDt,
        String regTm,
        String regUserNm,
        String modDt,
        String modTm,
        String modUserId,
        String modUserNm
    ) {
        public FindRes {
            cstTnob = StringUtils.isNotEmpty(cstTnob) ? DbEncUtil.dec(cstTnob) : cstTnob;
        }
    }
}
