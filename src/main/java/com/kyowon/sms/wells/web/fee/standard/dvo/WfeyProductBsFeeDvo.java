package com.kyowon.sms.wells.web.fee.standard.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WfeyProductBsFeeDvo {
    private String basePdCd;      /* 기준상품코드 */
    private Integer vstMcn;         /* 방문개월수 */
    private String svFeeDvCd;    /* 서비스수수료구분코드 */
    private String hcrDvCd1;       /* 홈케어구분코드 */
    private String hcrDvCd2;       /* 홈케어구분코드 */
    private String svFeePdDvCd; /* 서비스수수료상품구분코드 */
    private Integer baseChTcnt;    /* 기준변경차수 */
    private Long svFeeBaseAmt; /* 서비스수수료기준금액 */
    private String feeFxamYn;     /* 수수료정액여부 */
    private Long hcrFeeBaseAmt;/* 홈케어수수료기준금액 */
    private String apyStrtYm;     /* 적용시작년월 */
    private String apyEndYm;      /* 적용종료년월 */
    private String dtaDlYn;
}
