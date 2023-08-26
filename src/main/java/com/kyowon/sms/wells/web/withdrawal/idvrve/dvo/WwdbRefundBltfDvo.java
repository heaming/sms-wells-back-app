package com.kyowon.sms.wells.web.withdrawal.idvrve.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WwdbRefundBltfDvo {
    private String kwGrpCoCd;
    private String rfndAkNo;
    private String aftRfndAkNo; /* 채번용 키값 */
    private String cntrNo;
    private String cntrSn;
    private String rveNo;
    private String rveSn;
    private String bltfOjCntrNo;
    private String bltfOjCntrSn;
    private String cstNo;
    private String rfndBltfAkAmt;
    private String bltfRfndDvCd;
    private String bltfRfndTpCd;
    private String bltfRfndMbDvCd;
    private String rfndEvidMtrFileId;
    private String dtaDlYn;
}
