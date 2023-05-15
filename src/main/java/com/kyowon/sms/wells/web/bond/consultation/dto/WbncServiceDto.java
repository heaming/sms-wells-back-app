package com.kyowon.sms.wells.web.bond.consultation.dto;

import io.swagger.annotations.ApiModel;

public class WbncServiceDto {
    @ApiModel(value = "WbncServiceDto-FindRes")
    public record FindRes(
        String cstSvAsnNo,
        String cstSvExcnSn,
        String cntrNo,
        String cntrSn,
        String cntrNoSn,
        String sepIstCsDtlCd,
        String sepIstCsDtlNm,
        String svBizDclsfCd,
        String svBizDclsfNm,
        String svBizHclsfCd,
        String svBizHclsfNm,
        String vstFshDt,
        String asCausCd,
        String asCausNm,
        String svProcsCn,
        String bilOjAmt,
        String dpSumAmt,
        String blam
    ) {}
}
