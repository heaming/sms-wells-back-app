package com.kyowon.sms.wells.web.competence.evaluate.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WpsdPdBaseDvo {
    private String baseYm;           /* 기준년월 */
    private String evlOgTpCd;        /* 조직유형코드 */
    private String evlPdDvCd;        /* 평가상품구분코드 */
    private String pdCd;             /* 상품코드 */
    private String cvtPc;              /* 환산점수 */
    private String rowState;
}
