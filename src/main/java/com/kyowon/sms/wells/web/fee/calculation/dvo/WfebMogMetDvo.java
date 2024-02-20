package com.kyowon.sms.wells.web.fee.calculation.dvo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class WfebMogMetDvo {
    private String type;        /* 유형 */
    private String perfAtcCd;   /* 실적항목코드 */
    private String perfColNm;   /* 실적컬럼명 */
    private String eduCd;       /* 교육코드 */
}
