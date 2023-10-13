package com.kyowon.sms.wells.web.service.common.dvo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class WsnyInstallSeparationMgtDvo {
    String sellTpCd; /*판매유형코드*/
    String pdCd; /*상품코드*/
    String sepIstCsAtcCd; /*분리설치비용항목코드*/
    String sepIstCsDtlCd; /*분리설치비용상세코드*/
    int izSn; /*내역일련번호*/
    String apyStrtdt; /*적용시작일자*/
    String apyEnddt; /*적용종료일자*/
    String wkCsAmt; /*작업비용금액*/
    String recapSvYn; /*유상서비스여부*/
    String rmkCn; /*비고내용*/
}
