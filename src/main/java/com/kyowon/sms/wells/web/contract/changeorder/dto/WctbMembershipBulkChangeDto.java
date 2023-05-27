package com.kyowon.sms.wells.web.contract.changeorder.dto;

import io.swagger.annotations.ApiModel;

public class WctbMembershipBulkChangeDto {
    // *********************************************************
    // Result Dto
    // *********************************************************
    @ApiModel("WctbMembershipBulkChangeDto-SearchRes")
    public record SearchRes(
        String cntrDtlNo,
        String cstKnm,
        String sellInflwChnlDtlCd,
        String sellTpDtlCd,
        String sellPrtnrNo,
        String prtnrNm,
        String rveCd,
        String reqdDt,
        String cntrChRcpDtm,
        String istDt,
        String cntrStlmFshDtm,
        String svPrd,
        String useyn,
        String basePdCd,
        String pdNm,
        String fnlAmt,
        String stlmTpCd,
        String frisuBfsvcPtrmN,
        String cntrwTpCd,
        String stplPtrm,
        String cntrCnfmAprDtm,
        String canDt,
        String duedt,
        String cntrCnfmDtm,
        String wdwalDt,
        String vstPrd,
        String cttRsNm,
        String cttPsicId,
        String cttPsicNm,
        String hcrDvCd,
        String feeFxamYn,
        String feeAckmtBaseAmt,
        String sellDscDvCd,
        String sellDscrCd,
        String grpGbn,
        String fstRgstDtm,
        String fstRgstUsrId,
        String fstRgstUsrNm,
        String fnlMdfcDtm,
        String fnlMdfcUsrId,
        String fnlMdfcUsrNm,
        String cntrChAkCn
    ) {}
}
