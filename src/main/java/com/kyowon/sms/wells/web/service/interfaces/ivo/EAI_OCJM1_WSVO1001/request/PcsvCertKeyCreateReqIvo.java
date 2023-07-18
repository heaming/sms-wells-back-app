package com.kyowon.sms.wells.web.service.interfaces.ivo.EAI_OCJM1_WSVO1001.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PcsvCertKeyCreateReqIvo {

    @JsonProperty("DATA")
    private CertKeyCreateRequestData dATA;

}
