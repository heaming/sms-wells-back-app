package com.kyowon.sms.wells.web.service.allocate.dto;

import io.swagger.annotations.ApiModel;

import javax.validation.constraints.NotBlank;

public class WsncTransferHistoryDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    @ApiModel(value = "WsncTransferHistoryDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String cstSvAsnNo
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    @ApiModel(value = "WsncTransferHistoryDto-SearchRes")
    public record SearchRes(
        String fnlMdfcDtm,
        String asnTfDvCd,
        String bfOgNm,
        String bfCnfmPsicPrtnrNo,
        String bfPrtnrKnm,
        String afOgNm,
        String afCnfmPsicPrtnrNo,
        String afPrtnrKnm
    ) {}

}
