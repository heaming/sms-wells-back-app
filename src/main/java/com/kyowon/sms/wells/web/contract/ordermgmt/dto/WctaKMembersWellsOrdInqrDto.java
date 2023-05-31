package com.kyowon.sms.wells.web.contract.ordermgmt.dto;

import io.swagger.annotations.ApiModel;

public class WctaKMembersWellsOrdInqrDto {
    // *********************************************************
    // Result Dto
    // *********************************************************
    @ApiModel("WctaKMembersWellsOrdInqrDto-SearchRes")
    public record SearchRes(
        String cmnSfkVal,
        String basePdCd,
        String pdNm,
        String prchsPath,
        String sellTpCd,
        String sellTpDtlCd,
        String sellAmt,
        String cntrCnfmDtm,
        String stplPtrm,
        String amt1,
        String adr,
        String cntrDtlStatCd,
        String istDt,
        String svDt,
        String prtnrNo,
        String cralLocaraTno,
        String mexnoEncr,
        String cralIdvTno,
        String dpTpCd,
        String bnkCdcoCd,
        String acnoCrcdnoEncr,
        String owrKnm,
        String mpyBsdt
    ) {}
}
