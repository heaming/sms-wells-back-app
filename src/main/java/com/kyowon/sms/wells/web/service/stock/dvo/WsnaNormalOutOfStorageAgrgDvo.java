package com.kyowon.sms.wells.web.service.stock.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WsnaNormalOutOfStorageAgrgDvo {
    // PIVOT 창고번호 조건
    String wareNoInStr;
    // PIVOT 창고번호 필드
    String wareNoFields;

    String wareNoFieldsSumStr;

    String startStrHopDt;

    String endStrHopDt;

    String ostrAkTpCd;

    String ostrOjWareNo;

    String itmKndCd;

    String wareDvCd;

    String wareLocaraCd;

    String ostrCnfmYn;
}
