package com.kyowon.sms.wells.web.closing.payment.dvo;

// TODO: 테이블 미정의로 추후 재작업 필요

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class WdcaEtcAnticipationAmtDvo {
    private String etcAtamNo;
    private String etcAtamOcDt;
    private String itgDpNo;
    private String etcAtamAmt;
    private String etcAtamTpCd;
    private String etcAtamBlam;
    private String cntrNo;
    private String cntrSn;
    private String coCd;
    private String rveCd;
    private String ichrPrtnrNo;
    private String custNo;
}
