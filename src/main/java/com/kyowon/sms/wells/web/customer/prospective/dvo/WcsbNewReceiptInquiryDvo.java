package com.kyowon.sms.wells.web.customer.prospective.dvo;

import lombok.Getter;
import lombok.Setter;

/**
 * 웰스홈페이지 신규접수 Dvo
 */
@Getter
@Setter
public class WcsbNewReceiptInquiryDvo {
    String pspcCstInflwDt; /* 가망고객유입일자 */
    String aplcSn; /* 신청일련번호 */
    String sellInflwChnlDvCd; /* 판매유입채널구분코드 */
    String inrtPdHclsfId; /* 관심상품대분류ID */
    String inrtPdMclsfId; /* 관심상품중분류ID */
    String inrtPdLclsfId; /* 관심상품소분류ID */
    String inrtPdDclsfId; /* 상품세분류ID */
    String pspcCstKnm; /* 가망고객한글명 */
    String cralLocaraTno; /* 휴대지역전화번호 */
    String mexnoEncr; /* 휴대전화국번호암호화 */
    String cralIdvTno; /* 휴대개별전화번호 */
    String locaraTno; /* 지역전화번호 */
    String exnoEncr; /* 전화국번호암호화 */
    String idvTno; /* 개별전화번호 */
    String zip1; /*우편번호1*/
    String zip2; /*우편번호2*/
    String rnadr; /*도로명주소*/
    String rdadr; /*도로명상세주소*/
    String rdadr2; /*도로명상세주소2*/
    String cnslPsbStrtDt; /* 컨택요청일 */
    String cnslPsbStrtHh; /* 컨택요청시작시간 */
    String cnslPsbEndHh; /* 컨택요청종료시간 */
    String pspcCstRcpCn; /* 가망고객요청내용 */
    String fstRgstUsrId; /* 최초등록사용자ID */
    //
    String adrId; /* 주소ID */
}
