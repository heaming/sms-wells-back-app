package com.kyowon.sms.wells.web.bond.transfer.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WbnaCollectorAssignDvo {
    private String baseYm; /* 기준 년월일 */
    private String bzHdqDvCd; /* 사업부 구분코드 */
    private String clctamDvCd; /* 집금구분코드 */
    private String nwYn; /* 신규여부 */
    private String prtnrNo; /* 집금자 번호 */
    private String cntrNo; /* 계약번호 */
    private String cntrSn; /* 계약일련번호 */

}
