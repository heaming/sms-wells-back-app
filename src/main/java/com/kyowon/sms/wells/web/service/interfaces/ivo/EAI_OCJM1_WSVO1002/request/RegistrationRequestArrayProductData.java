package com.kyowon.sms.wells.web.service.interfaces.ivo.EAI_OCJM1_WSVO1002.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RegistrationRequestArrayProductData {

    @JsonProperty("MPCK_SEQ")
    private String mPCKSEQ;

    @JsonProperty("GDS_NM")
    private String gDSNM;

    @JsonProperty("GDS_CD")
    private String gDSCD;

    @JsonProperty("UNIT_CD")
    private String uNITCD;

    @JsonProperty("UNIT_NM")
    private String uNITNM;

    @JsonProperty("GDS_AMT")
    private String gDSAMT;

    @JsonProperty("GDS_QTY")
    private String gDSQTY;
}
