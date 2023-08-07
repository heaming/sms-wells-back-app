package com.kyowon.sms.wells.web.closing.performance.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WdccContractDvo {
    private String cntrNo; // 계약번호
    private String cntrSn; // 계약일련번호
    private String sellTpCd; // 판매유형코드
    private String sellTpDtlCd; // 판매유형상세코드
    private String islease; // 렌탈여부(?) 결과가 이상함 21,23이 렌탈인거 아닌가
}
