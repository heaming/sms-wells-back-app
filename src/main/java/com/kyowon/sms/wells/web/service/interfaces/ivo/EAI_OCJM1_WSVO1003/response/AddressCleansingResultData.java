package com.kyowon.sms.wells.web.service.interfaces.ivo.EAI_OCJM1_WSVO1003.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddressCleansingResultData {

    @JsonProperty("P2PCD")
    private Object p2PCD;

    @JsonProperty("RSPSDIV")
    private String rSPSDIV;

    @JsonProperty("SUBCLSFCD")
    private String sUBCLSFCD;

    @JsonProperty("CLLDLVBRANNM")
    private String cLLDLVBRANNM;

    @JsonProperty("CLSFCD")
    private String cLSFCD;

    @JsonProperty("CLLDLVEMPNICKNM")
    private String cLLDLVEMPNICKNM;

    @JsonProperty("CLLDLVEMPNM")
    private String cLLDLVEMPNM;

    @JsonProperty("CLSFADDR")
    private String cLSFADDR;
}
