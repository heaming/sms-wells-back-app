package com.kyowon.sms.wells.web.bond.transfer.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WbnaBondPartTransferDvo {
    private String baseYm; /* 기준 년월일 */
    private String bzHdqDvCd; /* 사업부 구분코드 */
    private String clctamDvCd; /* 집금구분코드 */
    private String cntrNo; /* 계약번호 */
    private String cntrSn; /* 계약일련번호 */
    private String cstNo; /* 고객번호 */

}
