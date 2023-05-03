package com.kyowon.sms.wells.web.withdrawal.interfaces.dvo;

import com.sds.sflex.system.config.annotation.DBDecField;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WwdaAutoTransferObjectItemizationInterfaceDvo {

    private String cstNo; /*고객번호*/
    private String sellTpCd; /*판매유형코드*/
    private String cntrNo; /*계약번호*/
    private String cntrSn; /*계약일련번호*/
    private String cntrDtlStatCd; /*계약상세상태코드*/
    private String cntrtNm; /*계약자명*/
    private String pdNm; /*상품명*/
    private String mbNm; /*회원명*/
    private String mmIstmAmt; /*월할부금액*/
    private String fntDvCd; /*이체구분코드*/
    private String fnitNm; /*금융기관명*/
    private String acnoCdno; /*계좌카드번호*/
    private String owrKnm; /*소유자한글명*/
    private String fntStplD; /*이체약정일*/
    private String aftnRsNm; /*자동이체결과명*/
    private String rveCrpCdNm; /*수납법인코드명*/
    private String incmdcYn; /*소득공제여부*/
    private String cralLocaraTno; /*전화번호시작*/
    @DBDecField
    private String mexnoEncr; /*전화번호중간*/
    private String cralIdvTno; /*전화번호끝*/
    private String mpno; /*휴대전화번호*/
}
