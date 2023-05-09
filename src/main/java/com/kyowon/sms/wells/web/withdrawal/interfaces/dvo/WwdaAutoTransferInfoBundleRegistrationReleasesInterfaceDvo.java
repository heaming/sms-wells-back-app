package com.kyowon.sms.wells.web.withdrawal.interfaces.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WwdaAutoTransferInfoBundleRegistrationReleasesInterfaceDvo {

    private String fntDvCd; /*이체구분코드(카드/은행)*/
    private String dgYn; /*대표여부(Y/N)*/
    private String dgCntrNo; /*대표계약번호*/
    private String dgCntrSn; /*대표계약일련번호*/
    private String dgSellTpCd; /*대표판매유형코드(일시불/렌탈/멤버십/회사/정기배송)*/
    private String cntrNo; /*계약번호*/
    private String cntrSn; /*계약일련번호*/
    private String cntrtNm; /*계약자명*/
    private String sellTpCd; /*판매유형코드(일시불/렌탈/멤버십/회사/정기배송)*/

    private String reslCd; /*(결과코드)(E : 에러, F : 실패, S : 정상)*/
    private String pcsRsltCn; /*처리결과내용*/

}
