package com.kyowon.sms.wells.web.withdrawal.idvrve.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WwdbRefundDtlDvo {
    private String kwGrpCoCd;
    private String rfndAkNo;
    private String aftRfndAkNo; /* 채번용 키값 */
    private String cntrNo;
    private String cntrSn;
    private String rveNo;
    private String rveSn;
    private String cstNo;
    private String rfndDvCd;
    private String rfndDsbDvCd;
    private String cshRfndDvCd;
    private String rfndCshAkAmt;
    private String rfndCardAkAmt;
    private String crdcdFeeAmt;
    private String crdcdFer;
    private String rfndBltfAkAmt;
    private String rfndRsonCd;
    private String rfndRsonCn;
    private String dtaDlYn;
}
