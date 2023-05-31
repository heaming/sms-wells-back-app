package com.kyowon.sms.wells.web.contract.ordermgmt.dvo;

import com.sds.sflex.system.config.annotation.DBDecField;
import com.sds.sflex.system.config.annotation.DBEncField;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WctaKMembersWellsOrdInqrDvo {
    private String cmnSfkVal;
    private String basePdCd;
    private String pdNm;
    private String prchsPath;
    private String sellTpCd;
    private String sellTpDtlCd;
    private String sellAmt;
    private String cntrCnfmDtm;
    private String stplPtrm;
    private String amt1;
    private String adr;
    private String cntrDtlStatCd;
    private String istDt;
    private String svDt;
    private String prtnrNo;
    private String cralLocaraTno;
    @DBEncField
    @DBDecField
    private String mexnoEncr;
    private String cralIdvTno;
    private String dpTpCd;
    private String bnkCdcoCd;
    @DBEncField
    @DBDecField
    private String acnoCrcdnoEncr;
    private String owrKnm;
    private String mpyBsdt;
}
