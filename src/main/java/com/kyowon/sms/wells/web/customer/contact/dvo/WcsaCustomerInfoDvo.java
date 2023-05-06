package com.kyowon.sms.wells.web.customer.contact.dvo;

import lombok.Getter;
import lombok.Setter;

/**
 * 파트너 계약(주문) 간 가족등록여부 체크 - 고객 조회 req DVO
 */
@Getter
@Setter
public class WcsaCustomerInfoDvo {
    private String calngDvCd; /* 호출구분코드 */
    private String copnDvCd; /* 법인격구분코드 */
    private String cstNo; /* 고객번호 */
    private String locaraTno; /* 지역전화번호 */
    private String exno; /* 전화국번호 */
    private String idvTno; /* 개별전화번호 */
    private String cralLocaraTno; /* 휴대전화지역 */
    private String mexno; /* 휴대전화국번호 */
    private String cralIdvTno; /* 휴대전화번호 */
    private String adrId; /* 주소ID */
    private String adrDvCd; /* 주소구분코드 */
    private String chLtrqConfYn; /* 변경요청서확인여부 */
    private String chLtrqConfDt; /* 변경요청서확인일자 */
    private String unuitmCn; /* 특이사항내용 */
    private String rgstMdfcUsrId; /* 등록수정사용자ID */

}
