package com.kyowon.sms.wells.web.contract.ordermgmt.dvo;

import com.sds.sflex.system.config.annotation.DBDecField;
import com.sds.sflex.system.config.annotation.DBEncField;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WctaOrderDetailInqrDvo {
    private String cntrDtlNo;
    private String cntrNo;
    private String cntrSn;
    private String cstKnm;
    private String copnDvCd;
    private String copnDvNm;
    private String bzrno;
    private String bryyMmdd;
    private String sexDvCd;
    private String rnmno;
    private String rnmnoDvCd;
    private String rnmnoEncr;
    private String basePdCd;
    private String basePdNm;
    private String dscApyTpCd;
    private String dscApyTpNm;
    private String dscApyDtlCd;
    private String dscApyDtlNm;
    private String cntrRcpFshDtm;
    private String sppDuedt;
    private String slDt;
    private String cntrCanDt;
    private String cpsDt;
    private String slPasgDt;
    private String slCnfmYn;
    private String cntrCanRsonCd;
    private String canRsonNm;
    private String cntrChDtlRsonCd;
    private String cntrChDtlRsonNm;
    private String frisuYn;
    private String freExpnCnfmDtm;
    private String cttTpCd;
    private String cttTpNm;
    private String iostDtlCd;
    private String sppIvcCrtDtm;
    private String booSellYn;
    private String cntrTpCd;
    private String cntrTpNm;
    private String sellPrtnrNo;
    private String fstRgstUsrId;
    private String fstRgstDeptId;
    private String rveCd;
    private String svPrd;
    private String frisuAsPtrmN;
    private String frisuBfsvcPtrmN;
    private String alncmpNm;
    private String sellEvCd;
    private String sellEvNm;
    private String fnlAmt;
    private String sellAmt;
    private String vat;
    private String cntrAmt;
    private String istmIntAmt;
    private String feeAckmtBaseAmt;
    private String crpUc;
    private String totDscAmt;
    private String feeAckmtCt;
    private String ackmtPerfAmt;
    private String feeAckmtTotAmt;
    private String feeFxamYn;
    private String pdSaleFee;
    private String cashBlam;
    private String istmMcn;
    private String mmIstmAmt;
    private String istmPcamAmt;
    private String cntrCstNo;
    private String cralLocaraTno;
    @DBEncField
    @DBDecField
    private String mexnoEncr;
    private String cralIdvTno;
    private String newAdrZip;
    private String rnadr;
    private String rdadr;
    private String rcgvpKnm;
    private String istlcCralLocaraTno;
    @DBEncField
    @DBDecField
    private String istlcMexnoEncr;
    private String istlcCralIdvTno;
    private String istlcAdrZip;
    private String istlcAdr;
    private String istlcDadr;
    private String cntrRelDtlNm;
    private String alncmpCntrDrmVal;
    private String alncmpCd;
    private String alncPrtnrDrmVal;
    private String spcOrdDv;
    private String sppOrdIvcNo;
    private String basePdInfo;
    private String basePdCd2;
    private String pdQty;
    private String fgptPdNm1;
    private String fgptPdNm2;
    private String fgptPdNm3;
    private String fgptPdNm4;
    private String bfsvcBzsDvCd;
    private String bfsvcBzsDvNm;
    private String splyBzsDvCd;
    private String splyBzsDvNm;
    private String cntrCanDtm;
}
