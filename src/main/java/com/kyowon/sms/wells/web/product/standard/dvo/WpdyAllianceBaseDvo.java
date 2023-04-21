package com.kyowon.sms.wells.web.product.standard.dvo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class WpdyAllianceBaseDvo {
    private String pdAlncmpBaseId; /* 상품제휴사기준ID */
    private String alncmpCd; /* 제휴사코드 */
    private String sellTpCd; /* 판매유형코드 */
    private String pdCd; /* 상품코드 */
    private String svPdCd; /* 서비스상품코드 */
    private String stplPrdCd; /* 약정주기코드 */
    private String rentalDscDvCd; /* 렌탈할인구분코드 */
    private String rentalDscTpCd; /* 렌탈할인유형코드 */
    private String rentalCrpDscrCd; /* 렌탈법인할인율코드 */
    private String apyStrtdt; /* 적용시작일자 */
    private String apyEnddt; /* 적용종료일자 */
    private String ogTpCd; /* 조직유형코드 */
    private String dtaDlYn; /* 데이터삭제여부 */
}
