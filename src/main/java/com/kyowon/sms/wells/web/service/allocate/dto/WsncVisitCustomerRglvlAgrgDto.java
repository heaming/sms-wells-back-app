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

    @ApiModel(value = "WsncVisitCustomerRglvlAgrgDto-SearchRgrpRes")
    public record SearchRgrpRes(
        String dddp,
        String dbna,
        Integer mct1,
        Integer mct2,
        Integer ncnt,
        Integer g1cn,
        Integer w1ct,
        Integer w2ct,
        Integer wacc,
        Integer wper,
        Integer hacc,
        Integer hper,
        Integer w3ct,
        Integer w4ct,
        Integer w5ct,
        Integer wcnt,
        Integer ecnt,
        Integer gcnt,
        Integer wrkCnt
    ) {}

    @ApiModel(value = "WsncVisitCustomerRglvlAgrgDto-SearchPsicRes")
    public record SearchPsicRes(
        Integer lcde,
        String dddp,
        String ddpt,
        String lnam,
        String dbna,
        String ldeg,
        String heldt,
        String byedt,
        Integer duldt,
        Integer mct1,
        Integer mct2,
        Integer ncnt,
        Integer g1cn,
        Integer w1ct,
        Integer w2ct,
        Integer wacc,
        Integer wper,
        Integer hacc,
        Integer hper,
        Integer w3ct,
        Integer w4ct,
        Integer w5ct,
        Integer wcnt,
        Integer ecnt,
        Integer gcnt,
        Integer wrkCnt
    ) {}
}
