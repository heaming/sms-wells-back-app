package com.kyowon.sms.wells.web.service.allocate.dto;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

import javax.validation.constraints.NotBlank;

public class WsncWellsAsInterfaceDto {

    @ApiModel(value = "WsncWellsAsInterfaceDto-SearchCustInfoReq")
    public record SearchCustInfoReq(
        String cntrNo,
        int cntrSn,
        String cstKnm,
        String hpno,
        String newAdrZip,
        String pdGrpId
    ) {}

    @ApiModel(value = "WsncWellsAsInterfaceDto-SearchCustInfoRes")
    public record SearchCustInfoRes(
        String bryyMmdd,
        String cntrCstNo,
        String cntrNo,
        String cralIdvTno,
        String cralLocaraTno,
        String cstKnm,
        String exnoEncr,
        String idvTno,
        String locaraTno,
        String mexnoEncr,
        String pdCd,
        String pdNm,
        String rcgvpKnm,
        String sexDvCd,
        String hpno,
        String newAdrZip
    ) {}

}
