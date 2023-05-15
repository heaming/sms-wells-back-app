package com.kyowon.sms.wells.web.withdrawal.interfaces.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WwdaAutoTransferCardEffectivenessCheckInterfaceDvo {
    private String cardFntRsCd; /*카드이체결과코드*/
    private String cardFntRsCdNm; /*카드이체결과코드명*/
    private String cdcoCd; /*카드사코드*/
    private String cdcoNm; /*카드사코드명*/
}
