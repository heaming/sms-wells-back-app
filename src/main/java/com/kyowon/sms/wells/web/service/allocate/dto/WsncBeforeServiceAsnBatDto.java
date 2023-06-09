package com.kyowon.sms.wells.web.service.allocate.dto;

import io.swagger.annotations.ApiModel;

public class WsncBeforeServiceAsnBatDto {

    // *********************************************************
    // Request Dto
    // *********************************************************

    @ApiModel(value = "WsncBeforeServiceAsnBatDto-SaveProcessReq")
    public record SaveProcessReq(
        String asnOjYm,
        String prtnrNo
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
}
