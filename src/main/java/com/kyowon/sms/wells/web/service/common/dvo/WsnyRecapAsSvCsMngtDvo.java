package com.kyowon.sms.wells.web.service.common.dvo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class WsnyRecapAsSvCsMngtDvo {
    private String pdCd; // 상품코드
    private String basePdCd; // 기준상품코드
    String apyStrtdt; // 적용시작일자
    String apyEnddt; // 적용종료일자
    private int csmrUprcAmt; // 소비자단가금액
    private int whlsUprcAmt; // 도매단가금액
    private int insiUprcAmt; // 내부단가금액
    private int tcfeeAmt; // 기술료금액
    private int izSn; // 내역일련번호

}
