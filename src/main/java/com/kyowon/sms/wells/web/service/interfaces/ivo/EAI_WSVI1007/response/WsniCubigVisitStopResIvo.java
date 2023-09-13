package com.kyowon.sms.wells.web.service.interfaces.ivo.EAI_WSVI1007.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonInclude;

@Setter
@Getter
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class WsniCubigVisitStopResIvo {
    @JsonProperty(value = "RESULT_CODE")
    String resultCode;
    @JsonProperty(value = "RESULT_MESSAGE")
    String resultMessage;
}
