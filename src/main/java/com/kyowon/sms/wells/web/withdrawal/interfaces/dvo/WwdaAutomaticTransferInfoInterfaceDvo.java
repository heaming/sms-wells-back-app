package com.kyowon.sms.wells.web.withdrawal.interfaces.dvo;

import com.sds.sflex.system.config.annotation.DBDecField;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WwdaAutomaticTransferInfoInterfaceDvo {

    private String fntDvCd; /*이체구분코드*/
    private String fntDvCdNm; /*이체구분코드명*/
    private String fnitCd; /*금융기관코드*/
    private String fnitNm; /*금융기관명*/
    private String acnoCdno; /*계좌카드번호*/
    private String owrKnm; /*소유자한글명*/
    private String fntStplD; /*이체약정일*/
    private String cardExpdtYm; /*카드유효기간년월*/
    private String copnDvCd; /*법인격구분코드*/
    private String copnDvDrmVal; /*법인격구분식별값*/
    private String mpno; /*휴대전화번호*/
    private String aftnRsCd; /*자동이체결과코드*/
    private String aftnRsNm; /*자동이체결과명*/
    private String cralLocaraTno; /*전화번호시작*/
    @DBDecField
    private String mexnoEncr; /*전화번호중간*/
    private String cralIdvTno; /*전화번호끝*/
}
