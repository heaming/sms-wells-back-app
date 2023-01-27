package com.kyowon.sms.wells.web.contract.risk.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WctcSellLimitOjIzDvo {
    private String sellLmId;
    private String vlStrtDtm;
    private String vlEndDtm;
    private String sellLmProcsTpCd;
    private String sellLmOjDrmCd;
    private String sellLmCstNo;
    private String sellLmPrtnrNo;
    private String sellLmCntrNo;
    private Integer sellLmCntrSn;
    private String sellLmBzrno;
    private String sellLmRsonCn;
    private String sellLmRsonCd;
    private String sellLmOcDtm;
    private String sellLmRlsDtm;
    private String sellLmRlsCn;
    private String dtaDlYn;
    private String orglFnlMdfcDtm;
}
