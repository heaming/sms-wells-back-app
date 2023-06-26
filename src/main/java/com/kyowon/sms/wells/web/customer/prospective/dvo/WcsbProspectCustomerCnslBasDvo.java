package com.kyowon.sms.wells.web.customer.prospective.dvo;

import lombok.Getter;
import lombok.Setter;

/**
 * 가망고객상담기본 Dvo
 */
@Getter
@Setter
public class WcsbProspectCustomerCnslBasDvo {
    private String pspcCstCnslId; /* 가망고객상담ID */
    private String pspcCstId; /* 가망고객ID */
    private String cstNo; /* 고객번호 */
    private String pspcCstCnslTpCd; /* 가망고객상담유형코드 */
    private Long aplcSn; /* 신청일련번호 */
    private String sellInflwChnlDvCd; /* 판매유입채널구분코드 */
    private String cnslPsbStrtDtm; /* 상담가능시작일시 */
    private String cnslPsbEndDtm; /* 상담가능종료일시 */
    private String alncmpCd; /* 제휴사코드 */
    private String inrtPdHclsfId; /* 관심상품대분류ID */
    private String inrtPdMclsfId; /* 관심상품중분류ID */
    private String inrtPdLclsfId; /* 관심상품소분류ID */
    private String inrtPdDclsfId; /* 관심상품세분류ID */
    private String inrtPdCd; /* 관심상품코드 */
    private String inrtPdDvCd; /* 관심상품구분코드 */
    private String pspcCstFtfYn; /* 가망고객대면여부 */
    private String vstAgDtm; /* 방문동의일시 */
    private String alncCnslCponId; /* 제휴상담쿠폰ID */
    private String cnslCponUseDtm; /* 상담쿠폰사용일시 */
    private String ichrAsnFshDtm; /* 담당배정완료일시 */
    private String ichrOgTpCd; /* 담당조직유형코드 */
    private String ichrPrtnrNo; /* 담당파트너번호 */
    private String cnslDtm; /* 상담일시 */
    private String pspcCstCnslRsCd; /* 가망고객상담결과코드 */
    private String cnslMoCn; /* 상담메모내용 */
    private String cnslEvlDtm; /* 상담평가일시 */
    private Integer cnslEvlPc; /* 상담평가점수 */
    private String cnslEvlRlyCn; /* 상담평가답변내용 */
    private String dtaDlYn; /* 데이터삭제여부 */
    //
    private String fstRgstUsrId; /* 최초등록사용자ID */
    private String pspcCstInflwDt; /* 가망고객유입일자 */
    private String pspcCstKnm; /* 가망고객한글명 */
    private String cralLocaraTno; /* 휴대지역전화번호 */
    private String mexnoEncr; /* 휴대전화국번호암호화 */
    private String cralIdvTno; /* 휴대개별전화번호 */
    private String locaraTno; /* 지역전화번호 */
    private String exnoEncr; /* 전화국번호암호화 */
    private String idvTno; /* 개별전화번호 */
    private String zip1; /*우편번호1*/
    private String zip2; /*우편번호2*/
    private String rnadr; /*도로명주소*/
    private String rdadr; /*도로명상세주소*/
    private String rdadr2; /*도로명상세주소2*/
    private String cnslPsbStrtDt; /* 컨택요청일 */
    private String cnslPsbStrtHh; /* 컨택요청시작시간 */
    private String cnslPsbEndHh; /* 컨택요청종료시간 */
    private String pspcCstRcpCn; /* 가망고객요청내용 */
    private String adrId; /* 주소ID */
}
