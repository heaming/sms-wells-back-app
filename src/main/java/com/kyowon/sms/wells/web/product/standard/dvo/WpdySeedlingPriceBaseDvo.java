package com.kyowon.sms.wells.web.product.standard.dvo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class WpdySeedlingPriceBaseDvo {
    private String fnlMdfcDtm;
    private String fnlMdfcUsrNm;

    private String rglrSppSdingPrcId; /* 정기배송모종가격ID */
    private String pdctPdCd; /* 제품상품코드 */
    private String rglrSppMchnKndCd; /* 정기배송기기종류코드 */
    private String rglrSppMchnTpCd; /* 정기배송기기유형코드 */
    private String rglrSppPrcDvCd; /* 정기배송가격구분코드 */
    private Long pdPrcTcnt; /* 상품가격차수 */
    private String apyStrtdt; /* 적용시작일자 */
    private String apyEnddt; /* 적용종료일자 */
    private Long sdingQty; /* 모종수량 */
    private Long sellAmt; /* 판매금액 */
    private Long splAmt; /* 공급금액 */
    private Long vat; /* 부가가치세 */
    private Long asSellAmt; /* AS판매금액 */
    private String useYn; /* 사용여부 */
    private String dtaDlYn; /* 데이터삭제여부 */
}
