package com.kyowon.sms.wells.web.service.interfaces.ivo.EAI_OCJM1_WSVO1001.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CertKeyCreateRequestData {

    @JsonProperty("CUST_ID")
    private String CUST_ID;

    @JsonProperty("BIZ_REG_NUM")
    private String BIZ_REG_NUM;

}
