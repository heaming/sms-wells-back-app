package com.kyowon.sms.wells.web.service.interfaces.ivo.EAI_OCJM1_WSVO1001.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CertKeyCreateResultData {

    @JsonProperty("TOKEN_NUM")
    private String tOKENNUM;

    @JsonProperty("TOKEN_EXPRTN_DTM")
    private String tOKENEXPRTNDTM;

}
