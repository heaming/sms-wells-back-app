package com.kyowon.sms.wells.web.service.visit.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WsnbSnProcessingAgrgBySnDvo {
    String cnfmPsicDvCd;
    String prtnrNo;
    String prtnrKnm;
    String pstnDvCd;
    String ogId;
    String ogNm;
    String rgrpId;
    String rgrpNm;
    String bldNm;
    Long bsCntrTotal;
    Long bsCntrObj;
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
    Long stickTotal;
    Long curMmReStick;
    Double ratProcsTotal;
    Double ratReProcs;
    Double ratStickTotal;
    Double ratReStick;
    Double ratScanTotal;
    Double ratReScan;
}
