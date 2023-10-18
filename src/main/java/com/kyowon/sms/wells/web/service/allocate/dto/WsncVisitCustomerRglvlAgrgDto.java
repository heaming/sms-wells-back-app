package com.kyowon.sms.wells.web.service.allocate.dto;

import io.swagger.annotations.ApiModel;

import javax.validation.constraints.NotBlank;

public class WsncVisitCustomerRglvlAgrgDto {
    @ApiModel(value = "WsncVisitCustomerRglvlAgrgDto-SearchReq")
    public record SearchReq(
        String vstYm,
        String rcgvpDiv,
        String inqrDiv,
        String dgr1LevlOgId,
        String dgr2LevlOgId,
        String dgr3LevlOgId,
        String partnerNo
    ) {}

    @ApiModel(value = "WsncVisitCustomerRglvlAgrgDto-SearchRes")
    public record SearchRes(
        String col1,
        String col2,
        String col3,
        Integer col4,
        Integer col5,
        Integer col6,
        Integer col7,
        Integer col8,
        Integer col9,
        Integer col10,
        Integer col11,
        Integer col12
    ) {}
}
