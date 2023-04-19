package com.kyowon.sms.wells.web.bond.transfer.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WbnaFosterDataSearchReqDvo {
    private String baseYm; /* 기준년월 */
    private String bzHdqDvCd; /* 사업부 */
    private String bndClctnPrpDvCd; /* 채권추심속성구분코드 */
    private String cntrNo; /* 계약번호 */
    private String cntrSn; /* 계약일련번호 */
    private String cstNo; /* 고객번호 */
}
