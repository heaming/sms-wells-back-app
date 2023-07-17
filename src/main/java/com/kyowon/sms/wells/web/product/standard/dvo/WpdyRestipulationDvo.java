package com.kyowon.sms.wells.web.product.standard.dvo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class WpdyRestipulationDvo {
    private String pdCd; /* 상품코드 */
    private String rstlBaseTpCd; /* 재약정기준유형코드 */
    private Integer stplTn; /* 약정회차 */
    private String rstlSellChnlDvCd; /* 재약정판매채널구분코드 */
    private String apyStrtdt; /* 적용시작일자 */
    private String apyEnddt; /* 적용종료일자 */
    private Integer rstlMcn; /* 재약정개월수 */
    private Long minRentalAmt; /* 최소렌탈금액 */
    private Long stplDscAmt; /* 약정할인금액 */
    private Integer rcpStrtMcn; /* 접수시작개월수 */
    private Integer rcpEndMcn; /* 접수종료개월수 */
    private Integer rstlDutyMcn; /* 재약정의무개월수 */

    private String ackmtAmt; /* 인정금액 */
    private Integer ackmtCt; /* 인정건수 */
    private String feeAckmtBaseAmt; /* 수수료인정기준금액 */
    private String feeFxamYn; /* 수수료정액여부 */
    private String rid;

    private String dtaDlYn; /* 데이터삭제여부 */

    private String pdNm; /* 상품명 */
    private String dupliYn; /* 중복체크용 생성변수 */
    private String orglFnlMdfcDtm;
}
