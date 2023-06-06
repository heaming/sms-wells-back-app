package com.kyowon.sms.wells.web.service.interfaces.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;

public class WsniIotQrcodeCustomerInterfaceDto {
    @ApiModel(value = "WsniIotQrcodeCustomerInterfaceDto-SearchReq")
    public record SearchReq(
        @JsonProperty(value = "BC_NO")
        String bcNo
    ) {

    }
    @ApiModel(value = "WsniIotQrcodeCustomerInterfaceDto-SearchRes")
    public record SearchRes(
        @JsonProperty(value = "CNTR_NO")
        String cntrNo,
        @JsonProperty(value = "CNTR_SN")
        Integer cntrSn,
        @JsonProperty(value = "CUST_NM")
        String custNm
    ) {

    }
}
