package com.kyowon.sms.wells.web.withdrawal.interfaces.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WwdaAutoTransferObjectItemizationInterfaceDvo {

    private String cntrStlmId; /*계약결제ID*/
    private String cstNo; /*고객번호*/
    private String sellTpCd; /*판매유형코드*/
    private String sellTpCdNm; /*판매유형코드*/
    private String cntrNo; /*계약번호*/
    private String cntrSn; /*계약일련번호*/
    private String cntrDtlStatCd; /*계약상세상태코드*/
    private String cntrtNm; /*계약자명*/
    private String pdNm; /*상품명*/
    private String istllKnm; /*설치자한글명*/
    private String fntDvCd; /*이체구분코드*/
    private String fntDvCdNm; /*이체구분코드명*/
    private String fnitNm; /*금융기관명*/
    private String acnoCdno; /*계좌카드번호*/
    private String owrKnm; /*소유자한글명*/
    private String fntStplD; /*이체약정일*/
    private String dgYn; /*대표여부*/
    private String bndlCntrYn; /*묶음계약여부*/
    private String dgCntrNo; /*대표계약번호*/
    private String dgCntrSn; /*대표계약일련번호*/
    private String istDt; /*설치일자*/
    private String istAddr; /*설치주소*/
}
