package com.kyowon.sms.wells.web.service.interfaces.ivo.EAI_OCJM1_WSVO1003.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddressCleansingRequestData {

    @JsonProperty("CLNTNUM")
    private String cLNTNUM;

    @JsonProperty("ADDRESS")
    private String aDDRESS;

    @JsonProperty("TOKEN_NUM")
    private String tOKENNUM;

}
