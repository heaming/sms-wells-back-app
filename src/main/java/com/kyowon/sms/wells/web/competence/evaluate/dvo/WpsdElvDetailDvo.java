
package com.kyowon.sms.wells.web.competence.evaluate.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WpsdElvDetailDvo {
    private String baseYm;            /* 기준년월 */
    private String evlOgTpCd;         /* 평가조직유형코드 */
    private String evlDvCd;           /* 평가구분코드 */
    private String evlAtcDvCd;        /* 평가항목구분코드 */
    private String evlPdDvCd;         /* 평가상품구분코드 */
    private Integer evlBaseUnitN;     /* 평가기준단위수 */
    private Long cvtBasePc;           /* 환산기준점수 */
    private Long trgBasePc;           /* 목표기준점수 */
    private String ctstGrpCd;         /* 경진그룹코드 */
    private String dtaDlYn;           /* 데이터삭제여부 */
    private String rowState;
}
