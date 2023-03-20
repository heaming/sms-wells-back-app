package com.kyowon.sms.wells.web.closing.performances.interfaces.ivo.EAI_WCLI0003.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LumpSumPerformancesReqIvo {

    @JsonProperty("CNTR_NO")
    String cNTRNO;
    @JsonProperty("CNTR_SN")
    String cNTRSN;
}
