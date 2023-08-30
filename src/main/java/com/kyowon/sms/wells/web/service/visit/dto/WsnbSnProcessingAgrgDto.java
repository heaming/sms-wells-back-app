package com.kyowon.sms.wells.web.service.visit.dto;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;

public class WsnbSnProcessingAgrgDto {
    @ApiModel(value = "WsnbSnProcessingAgrgDto-SearchReq")
    public record SearchReq(
        @NotNull
        String baseDt,
        String dgr1LevlOgId,
        String dgr2LevlOgId
    ) {}

    @ApiModel(value = "WsnbSnProcessingAgrgDto-SearchSnRes")
    public record SearchSnRes(
        String cnfmPsicDvCd,
        String prtnrNo,
        String prtnrKnm,
        String pstnDvCd,
        String ogId,
        String ogNm,
        String rgrpId,
        String rgrpNm,
        String bldNm,
        Long bsCntrTotal,
        Long bsCntrObj,
        Long curMmRe,
        Long procsTotal,
        Long curMmReProcs,
        Long scanTotal,
        Long curMmReScan,
        Long handTotal,
        Long curMmReHand,
        Long reReqTotal,
        Long curMmReReq,
        Long yetProcs,
        Long curMmReYetProcs,
        Long stpTotal,
        Long curMmReStp,
        Long stickTotal,
        Long curMmReStick,
        Double ratProcsTotal,
        Double ratReProcs,
        Double ratStickTotal,
        Double ratReStick,
        Double ratScanTotal,
        Double ratReScan
    ) {}

    @ApiModel(value = "WsnbSnProcessingAgrgDto-SearchRgrpRes")
    public record SearchRgrpRes(
        String rgrpId,
        String rgrpNm,
        String bmKnm,
        Long bsCntrTotal,
        Long bsCntrObj,
        Long curMmRe,
        Long procsTotal,
        Long curMmReProcs,
        Long scanTotal,
        Long curMmReScan,
        Long handTotal,
        Long curMmReHand,
        Long reReqTotal,
        Long curMmReReq,
        Long yetProcs,
        Long curMmReYetProcs,
        Long stpTotal,
        Long curMmReStp,
        Long stickTotal,
        Long curMmReStick,
        Double ratProcsTotal,
        Double ratReProcs,
        Double ratStickTotal,
        Double ratReStick,
        Double ratScanTotal,
        Double ratReScan
    ) {}

}
