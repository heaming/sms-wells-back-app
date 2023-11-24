package com.kyowon.sms.wells.web.withdrawal.idvrve.dvo;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * 자동 선납할인제외 관리 저장 / 삭제 DVO
 * </pre>
 *
 * @author heungjun.lee
 * @since 2023-03-03
 */
@Getter
@Setter
public class WwdbAutoPrepaymentDiscountExcludeMgtDvo {
    String cntrNo; //계약번호
    String cntrSn; //일련번호
    String prmDscExcdStrtYm; //선납제외시작월
    String prmDscExcdEndYm; //선납제외종료월
}
