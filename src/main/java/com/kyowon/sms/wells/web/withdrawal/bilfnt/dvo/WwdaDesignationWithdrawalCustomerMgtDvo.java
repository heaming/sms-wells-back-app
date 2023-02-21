package com.kyowon.sms.wells.web.withdrawal.bilfnt.dvo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class WwdaDesignationWithdrawalCustomerMgtDvo {

    private String rowState;
    private String dataRow;
    private String cntrNo; // 계약번호
    private Integer cntrSn; // 계약일련번호
    private Integer dsnWdrwAmt; // 지정금액
    private Integer dpAmt; // 입금금액
    private String fntYn; // 이체구분
    private String dsnWdrwFntD; // 이체일
    private String dsnWdrwFntPrdCd; // 이체주기코드
    private String bilNo; // 이체주기코드
    private Integer bilDtlSn; // 이체주기코드

}
