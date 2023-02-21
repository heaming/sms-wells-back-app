package com.kyowon.sms.wells.web.fee.aggregate.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WfeaFeeBaseAmountDvo {
    String cntrNo; /* 계약번호 */
    String cntrSn; /* 계약일련번호 */
    String chSn; /* 변경일련번호 */
    String cstKnm; /* 계약정보-고객명 */
    String cralLocaraTno; /* 계약정보-휴대지역전화번호 */
    String mexnoEncr; /* 계약정보-휴대전화국번호암호화 */
    String cralIdvTno; /* 계약정보-휴대개별전화번호 */
    String rnadr; /* 계약정보-도로명주소 */
    String rdadr; /* 계약정보-도로명상세주소 */
    String rcgvpKnm; /* 설치정보-고객명 */
    String cralLocaraTnoInstall; /* 설치정보-휴대지역전화번호 */
    String mexnoEncrInstall; /* 설치정보-휴대전화국번호암호화 */
    String cralIdvTnoInstall; /* 설치정보-휴대개별전화번호 */
    String rnadrInstall; /* 설치정보-도로명주소 */
    String rdadrInstall; /* 설치정보-도로명상세주소 */
    String pdNm; /* 상품정보-상품 */
    String pdPrpVal01; /* 상품정보-용도 */
    String pdPrpVal01Nm; /* 상품정보-용도명 */
    String apyStrtYm; /* 적용기간 */
    String chRqrDvCd; /* 변경구분 */
    String fnlMdfcDtm; /* 변경등록일자 */
    String fxnPrtnrDvCd; /* 지정대상 */
    String fxnPrtnrNo; /* 방문담당 */
    String chRsonCn; /* 변경사유 */
    String dtaDlYn; /* 삭제여부 */
    String prtnrKnm; /* 기존담당-담당자 */
    String rsgnDt; /* 기존담당-활동중지일 */
}
