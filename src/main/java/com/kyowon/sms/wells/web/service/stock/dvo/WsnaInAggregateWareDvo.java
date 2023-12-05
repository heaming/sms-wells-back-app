package com.kyowon.sms.wells.web.service.stock.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WsnaInAggregateWareDvo {

    String baseDateFrom;
    String wareDvCd;
    String wareNo;
    String wareNm;
    boolean isSumFields; // 합계 필드 노출 여부
}
