package com.kyowon.sms.wells.web.service.interfaces.ivo.EAI_OCJM1_WSVO1001.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PcsvCertKeyCreateResIvo {

    @JsonProperty("RESULT_DETAIL")
    private String rESULTDETAIL;

    @JsonProperty("DATA")
    private CertKeyCreateResultData dATA;

    @JsonProperty("RESULT_CD")
    private String rESULTCD;

}
