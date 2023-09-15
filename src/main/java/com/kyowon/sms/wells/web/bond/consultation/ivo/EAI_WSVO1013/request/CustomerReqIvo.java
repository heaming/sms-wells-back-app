package com.kyowon.sms.wells.web.bond.consultation.ivo.EAI_WSVO1013.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CustomerReqIvo {

    @JsonProperty("cstNo")
    private String cstNo;
}
