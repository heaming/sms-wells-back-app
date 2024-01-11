package com.kyowon.sms.wells.web.service.common.dvo;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class WsnyPaidAsCostMgtDvo {
    private String useMatPdCd; // 상품코드
    private String pdctPdCd; // 기준상품코드
    private String apyStrtdt; // 적용시작일자
    private String orgApyStrtdt; // 변경 전 적용시작일자
    private String apyEnddt; // 적용종료일자
    private BigDecimal csmrUprcAmt; // 소비자단가금액
    private BigDecimal whlsUprcAmt; // 도매단가금액
    private BigDecimal insiUprcAmt; // 내부단가금액
    private BigDecimal tcfeeAmt; // 기술료금액
    private BigDecimal izSn; // 내역일련번호

}
