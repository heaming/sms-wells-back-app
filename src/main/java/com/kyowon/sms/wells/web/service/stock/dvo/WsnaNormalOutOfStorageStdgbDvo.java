package com.kyowon.sms.wells.web.service.stock.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WsnaNormalOutOfStorageStdgbDvo {
    // 기준년월
    String apyYm;
    // 창고번호
    String wareNo;
    // 표준창고 사용여부
    String stckStdGb;
}
