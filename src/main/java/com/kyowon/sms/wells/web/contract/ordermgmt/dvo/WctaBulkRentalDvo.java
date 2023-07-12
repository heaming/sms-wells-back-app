package com.kyowon.sms.wells.web.contract.ordermgmt.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WctaBulkRentalDvo {
    private String basePdCd;
    private String copnDvCd;
    private String cstNo;
    private String sellPrtnrNo; /* from  alncmpDgPrtnrMapngCd */
    private String alncmpCd;
    private String sellOgTpCd; /* from alncmpDgPrtnrOgTpCd */
    private Long cntrAmt;
    private Integer cntrPtrm;
    private String svPdCd;
    private Integer stplPtrm;
    private String pspcCstInflwDt;
    private String sellDscTpCd; /* from rentalDscTpCd */
    private String sellDscDvCd; /* from rentalDscDvCd */
    private String sellDscrCd; /* from rentalCrpDscrCd */
    private Long sellDscCtrAmt;
    private String otsdLkDrmVal; /* from alncmpSuscOrdNo */
    private String adrId;
    private String pdHclsfId;
    private String pdMclsfId;
    private String pdLclsfId;
    private String pdDclsfId;
    private String sellTpCd;
    private String sellTpDtlCd;
    private Integer svPrd;
    private Double pdBaseAmt; /* todo change type */
    private Double sellAmt;
    private Double dscAmt;
    private Double fnlAmt;
    private Long vat;
    private Double cntrTam;
    private Float ackmtPerfRt;
    private Long ackmtPerfAmt;
    private Integer feeAckmtCt;
    private Long feeAckmtBaseAmt;
    private String cntrNo;
    private Integer cntrSn;
    private String cntrTpCd;
    private String cntrPrgsStatCd;
    private String cntrNatCd;
    private Integer pdQty;
    private String cntrwTpCd;
    private String txinvPblOjYn;
    private String blgCrpCd;
    private String rveCrpCd;
    private String coCd;
    private Long cntramDscAmt; /* todo */
    private String cntrPrtnrRelId;
    private String cntrPrtnrTpCd;
    private String cntrCstRelId;
    private String cntrUnitTpCd;
    private String cntrCstRelTpCd;
    private String cntrtRelCd;
    private String sellInflwChnlDtlCd;
    private String pdctCntrPdRelId;
    private String pdctPdRelId;
    private String pdctPdCd;
    private String pdctVlStrtDtm;
    private String pdctVlEndDtm;
    private Integer pdctPdQty;
    private String svCntrPdRelId;
    private String svPdRelId;
    private String svVlStrtDtm;
    private String svVlEndDtm;
    private Integer svPdQty;
}
