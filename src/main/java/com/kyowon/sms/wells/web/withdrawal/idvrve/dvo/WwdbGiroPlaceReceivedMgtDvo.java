package com.kyowon.sms.wells.web.withdrawal.idvrve.dvo;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * 지로 수신처 저장 DVO
 * </pre>
 *
 * @author heungjun.lee
 * @since 2023-05-19
 */
@Getter
@Setter
public class WwdbGiroPlaceReceivedMgtDvo {
    String cntrNo;
    String cntrSn;
    String giroBizDvCd; // --지로업무구분코드
    String giroBizTpCd; //--지로업무유형코드 판매유형
    String cstFnm; //--고객명
    //    String zip;// --우편번호
    //    String adrDvCd;// --주소구분코드
    //    String basAdr;
    String giroPlrcvAdrId; //주소ID
    String giroPlrcvRgstDt; //등록일자
}
