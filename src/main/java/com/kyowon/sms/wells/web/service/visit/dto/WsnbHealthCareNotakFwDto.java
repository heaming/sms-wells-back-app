package com.kyowon.sms.wells.web.service.visit.dto;

import io.swagger.annotations.ApiModel;

public class WsnbHealthCareNotakFwDto {

    @ApiModel(value = "WsnbHealthCareNotakFwDto-SearchReq")
    public record SearchReq() {}

    @ApiModel(value = "WsnbHealthCareNotakFwDto-SearchRes")
    public record SearchRes(
        String cntrNo,
        String cntrSn,
        String spmtCstSvUAgYn,
        String pifThpOfrAgYn,
        String agpNm,
        String cstNm,
        String svBizDclsfCd,
        String asnDt,
        String cstSvAsnNo,
        String cphonIdvTno,
        String cstFnm
    ) {}

    @ApiModel(value = "WwsnyDevelopmentSupportDto-FindReq")
    public record FindReq() {}

    @ApiModel(value = "WsnbHealthCareNotakFwDto-FindRes")
    public record FindRes(
        String cntrNo,
        String cntrSn,
        String spmtCstSvUAgYn,
        String pifThpOfrAgYn,
        String agpNm,
        String cstNm,
        String svBizDclsfCd,
        String asnDt,
        String cstSvAsnNo,
        String cphonIdvTno,
        String cstFnm
    ) {}

    @ApiModel(value = "WsnbHealthCareNotakFwDto-SaveReq")
    public record SaveReq() {}

    @ApiModel(value = "WsnbHealthCareNotakFwDto-CreateReq")
    public record CreateReq() {}

    @ApiModel(value = "WsnbHealthCareNotakFwDto-EditReq")
    public record EditReq() {}

    @ApiModel(value = "WsnbHealthCareNotakFwDto-RemoveReq")
    public record RemoveReq() {}

}
