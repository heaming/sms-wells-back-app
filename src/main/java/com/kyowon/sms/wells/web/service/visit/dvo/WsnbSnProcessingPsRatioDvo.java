package com.kyowon.sms.wells.web.service.visit.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WsnbSnProcessingPsRatioDvo {
    Long bsCntrTotal;
    Long curMmRe;
    Long procsTotal;
    Long curMmReProcs;
    Long scanTotal;
    Long curMmReScan;
    Long handTotal;
    Long curMmReHand;
    Long reReqTotal;
    Long curMmReReq;
    Long yetProcs;
    Long curMmReYetProcs;
    Long stpTotal;
    Long curMmReStp;
    Double ratProcsTotal;
    Double ratReProcs;
    Double ratStickTotal;
    Double ratReStick;
    Double ratScanTotal;
    Double ratReScan;
}
