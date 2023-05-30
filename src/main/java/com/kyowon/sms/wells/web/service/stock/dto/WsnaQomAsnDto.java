package com.kyowon.sms.wells.web.service.stock.dto;

import io.swagger.annotations.ApiModel;

public class WsnaQomAsnDto {
    @ApiModel(value = "WsnaQomAsnDto-SearchReq")
    public record SearchReq(
        String apyYm,
        String asnOjYm,
        String asnTnN,
        String wareDtlDvCd
    ){}

    @ApiModel(value = "WsnaQomAsnDto-SearchRes")
    public record SearchRes(

    ){}

    @ApiModel(value = "WsnaQomAsnDto-CreateIndependenceWareReq")
    public record CreateIndependenceWareReq(

    ){}

    @ApiModel(value = "WsnaQomAsnDto-CreateIndividualWareReq")
    public record CreateIndividualWareReq(

    ){}
}
