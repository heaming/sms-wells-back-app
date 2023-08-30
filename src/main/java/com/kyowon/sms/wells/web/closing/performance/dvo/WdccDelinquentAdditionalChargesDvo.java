package com.kyowon.sms.wells.web.closing.performance.dvo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class WdccDelinquentAdditionalChargesDvo {
    private String perfYm;
    private String sellTpCd;
    private String sellTpCdNm;
    private String sellTpDtlCd;
    private String sellTpDtlCdNm;
    private String sapPdDvCd;
    private String sapPdAtcNm;
    private String cntrNo;
    private String cstKnm;
    private String btdDlqAddAmt;
    private String thmOcDlqAddAmt;
    private String thmCtrDlqAddAmt;
    private String thmDlqAddDpSumAmt;
    private String thmDlqAddRfndSumAmt;
    private String eotDlqAddAmt;
}
