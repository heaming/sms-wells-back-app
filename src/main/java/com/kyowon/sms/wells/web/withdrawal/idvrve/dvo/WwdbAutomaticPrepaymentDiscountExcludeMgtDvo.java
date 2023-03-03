package com.kyowon.sms.wells.web.withdrawal.idvrve.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WwdbAutomaticPrepaymentDiscountExcludeMgtDvo {
    String cntrNo; //계약번호
    String cntrSn; //일련번호 
    String prmDscExcdStrtYm; //선납제외시작월
    String prmDscExcdEndYm; //선납제외종료월
}
