package com.kyowon.sms.wells.web.withdrawal.interfaces.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WwdaAutoTransferInfoBulkRegistrationReleasesInterfaceDvo {
    private String cntrNo; /*계약번호*/
    private String cntrSn; /*계약일련번호*/
    private String fntDvCd; /*이체구분코드*/
    private String fnitCd; /*금융기관코드*/
    private String acnoCdno; /*계좌카드번호*/
    private String owrKnm; /*소유자한글명*/
    private String mpyBsdt; /*납부기준일자*/
    private String cardExpdtYm; /*카드유효기간년월*/
    private String copnDvCd; /*법인격구분코드*/
    private String crpSpmtDrmNm; /*법인추가식별명*/
    private String mpno; /*휴대전화번호*/
    private String reslCd; /*(결과코드)(E : 에러, F : 실패, S : 정상)*/
    private String pcsRsltCn; /*처리결과내용*/

}
