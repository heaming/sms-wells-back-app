package com.kyowon.sms.wells.web.withdrawal.interfaces.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WwdaAutoTransferRealNameCertificationInterfaceDvo {
    private String acFntRsCd; /*계좌이체결과코드*/
    private String acFntRsCdNm; /*계좌이체결과코드명*/
    private String owrKnm; /*소유자한글명*/
}
