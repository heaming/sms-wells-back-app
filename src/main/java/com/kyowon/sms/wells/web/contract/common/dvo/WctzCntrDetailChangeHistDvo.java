package com.kyowon.sms.wells.web.contract.common.dvo;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WctzCntrDetailChangeHistDvo {
    private String cntrNo;
    private String histStrtDtm;
    private int cntrSn;
    private String histEndDtm;
    private String basePdCd;
    private String hgrPdCd;
    private long pdQty;
    private String stplPtrmUnitCd;
    private long stplPtrm;
    private long istmMcn;
    private String cntrPdStrtdt;
    private String cntrPdEnddt;
    private String cntrDtlStatCd;
    private String sellTpCd;
    private String dscApyTpCd;
    private String dscApyDtlCd;
    private String dscApyDrmVal;
    private String svPtrmUnitCd;
    private long svPrd;
    private String cntrwTpCd;
    private String blgCrpCd;
    private String rveCrpCd;
    private String coCd;
    private String booSellTpCd;
    private String pdGdCd;
    private String pdHclsfId;
    private String pdMclsfId;
    private String pdLclsfId;
    private String pdDclsfId;
    private String stlmTpCd;
    private String crncyDvCd;
    private long apyExcr;
    private long pdBaseAmt;
    private long fnlAmt;
    private long vat;
    private long sellAmt;
    private long cntrAmt;
    private long istmPcamAmt;
    private long istmIntAmt;
    private long mmIstmAmt;
    private long ackmtPerfRt;
    private long ackmtPerfAmt;
    private long cvtPerfAmt;
    private String sppDuedt;
    private String resubYn;
    private String rstlYn;
    private String frisuYn;
    private String frisuDsbTpCd;
    private long feeAckmtCt;
    private long feeAckmtBaseAmt;
    private String feeFxamYn;
    private String txinvPblOjYn;
    private String smtplId;
    private int smtplSn;
    private String bfOrdNo;
    private String cntrChRcpId;
    private int cntrChSn;
    private String cntrChDtlRsonCd;
    private String cntrChDtlAkCn;
    private String dtaDlYn;
    private String fstRgstDtm;
    private String fstRgstUsrId;
    private String fstRgstPrgId;
    private String fstRgstDeptId;
    private String fnlMdfcDtm;
    private String fnlMdfcUsrId;
    private String fnlMdfcPrgId;
    private String fnlMdfcDeptId;
}
