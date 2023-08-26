package com.kyowon.sms.wells.web.service.visit.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WsnbSnProcessingAgrgByRgrpDvo {
    String rgrpId;
    String rgrpNm;
    String bmKnm;
    Long bsCntrTotal;
    Long curMmRe;
    Long procsTotal;
    Long curMmReProcs;
    Long scanTotal;
    Long curMmReScan;
    Long handTotal;
    Long curMmReHand;
    Long reReqTotal;
    Long curMmgReReq;
    Long yetProcs;
    Long curMmReYetProcs;
    Long stpTotal;
    Long curMmReStp;
    Long stickTotal;
    Long curMmReStick;
    Double ratProcsTotal;
    Double ratReProcs;
    Double ratStickTotal;
    Double ratReStick;
    Double ratScanTotal;
    Double ratReScan;
}
