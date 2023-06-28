package com.kyowon.sms.wells.web.customer.contact.dvo;

import java.util.Map;

import lombok.Getter;
import lombok.Setter;

/**
 * 고객 약관동의 정보 DVO
 */
@Getter
@Setter
public class WcsaCustomerAgreementDvo {
    private String wkDv;                            /* 작업구분 (01 : 약관동의변경, 02 : 회원탈퇴) */
    private String cstNo;                           /* 고객번호 */
    private String agWdwalDtm;                      /* 동의탈퇴일시 */
    private String prvCn;                           /* 약관내용 */
    private Map<String, String> agAtcDvCdMap;       /* 동의항목구분코드 Map */
    private String cstAgId;                         /* 고객동의ID */
    private String agAtcDvCd;                       /* 동의항목구분코드 */
    private String agYn;                            /* 동의여부 */
}
