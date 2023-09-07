package com.kyowon.sms.wells.web.fee.standard.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WfeyNewChannelBaseDvo {
    private String rowState;
    private Long mtrSn;              /* 자료일련번호 */
    private String sellTpCd;         /* 판매유형코드 */
    private String pdCd;             /* 상품코드 */
    private String uswyTpCd;         /* 용도유형코드 */
    private String sellDscDvCd;      /* 판매할인구분코드 */
    private String sellDscrCd;       /* 판매할인율코드 */
    private String pmotCd;           /* 프로모션코드 */
    private String feecDvCd;         /* 수수료채널구분코드 */
    private String sppDvCd;          /* 배송구분코드 */
    private Long svPrd;              /* 서비스주기 */
    private String hcrDvCd1;          /* 홈케어구분코드 */
    private String hcrDvCd2;          /* 홈케어구분코드 */
    private String hgrPdCd;          /* 상위상품코드 */
    private String apyStrtdt;        /* 적용시작일자 */
    private Long totStplMcn;         /* 총약정개월수 */
    private Long chSn;               /* 변경일련번호 */
    private String apyEnddt;         /* 적용종료일자 */
    private String feeFxamYn;        /* 수수료정액여부 */
    private Long sellFee;            /* 판매수수료 */
    private String dtaDlYn;          /* 데이터삭제여부 */
}
