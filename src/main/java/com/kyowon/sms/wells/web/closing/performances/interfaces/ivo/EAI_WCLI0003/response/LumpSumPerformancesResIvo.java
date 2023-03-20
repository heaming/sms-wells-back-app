package com.kyowon.sms.wells.web.closing.performances.interfaces.ivo.EAI_WCLI0003.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LumpSumPerformancesResIvo {
    @JsonProperty("CNTR_SN")
    String cNTRSN;
    @JsonProperty("DP_SUM_AMT")
    String dPSUMAMT;
    @JsonProperty("DLQ_MCN")
    String dLQMCN;
    @JsonProperty("UC_AMT")
    String uCAMT;
    @JsonProperty("FULPY_DT")
    String fULPYDT;
    @JsonProperty("BU_NOTI_DT")
    String bUNOTIDT;
    @JsonProperty("REDF_DT")
    String rEDFDT;
    @JsonProperty("ADSB_DT")
    String aDSBDT;
    @JsonProperty("FULPY_EXP_AMT")
    String fULPYEXPAMT;
    @JsonProperty("MPY_TN")
    String mPYTN;
    @JsonProperty("CNTR_NO")
    String cNTRNO;
    @JsonProperty("RES_TN")
    String rESTN;
    @JsonProperty("EOT_DLQ_AMT")
    String eOTDLQAMT;
}
