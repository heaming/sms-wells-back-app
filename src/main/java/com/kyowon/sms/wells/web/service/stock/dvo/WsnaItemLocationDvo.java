package com.kyowon.sms.wells.web.service.stock.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WsnaItemLocationDvo {

    // 창고번호
    String wareNo;
    // 품목코드
    String itmPdCd;
    // 창고유형
    String wareTpCd;
    // 앵글
    String itmLctAngleVal;
    // 층수
    String itmLctCofVal;
    // 층번호
    String itmLctFlorNoVal;
    // 그룹
    String itmLctMatGrpCd;
    // 품목종류
    String itmKndCd;
    // 표준창고 사용여부
    String stckStdGb;
}
