package com.kyowon.sms.wells.web.closing.expense.dvo;

import com.sds.sflex.system.config.annotation.DBDecField;
import com.sds.sflex.system.config.annotation.DBEncField;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WdcdCleanersDvo {
    String clinrRgno;
    String rcpYm;
    String fstRgstDtm;
    String fnlMdfcDtm;
    String clinrNm;
    String bldCd;
    String bldNm;
    String aplcDt;
    String aplcnsNm;
    String aplcPrtnrNo;
    String cntrwAtthDocId;
    Integer cntrwAtthDocIdNumberOfFiles;
    String cntrLroreAtthDocId;
    Integer cntrLroreAtthDocIdNumberOfFiles;
    String idfAtthDocId;
    Integer idfAtthDocIdNumberOfFiles;
    String bnkbAtthDocId;
    Integer bnkbAtthDocIdNumberOfFiles;
    String fmnCoSpptAmt;
    String clinrFxnAmt;
    String taxDdctam;
    String amt;
    String wrkStrtdt;
    String wrkEnddt;
    String workStatus;
    String bryyMmdd;
    @DBEncField
    @DBDecField
    String rrnoEncr;
    String locaraTno;
    @DBEncField
    @DBDecField
    String exnoEncr;
    String idvTno;
    String telNum;
    String address;
    String bnkCd;
    String bnkNm;
    @DBEncField
    @DBDecField
    String acnoEncr;
}
