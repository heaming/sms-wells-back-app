package com.kyowon.sms.wells.web.organization.insurance.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WogdIndustrialDisasterInsuranceDvo {
    String prtnrNo;//파트너번호
    String ogTpCd;//조직유형코드
    String dsbYm; //지급년월
    String dtaDlYn;//삭제여부
    String inddInsrFeeAmt; //수수료
    String inddInsrUcamAmt; //미수금
    String inddInsrDdctam;//산재보험공제금액
}
