package com.kyowon.sms.wells.web.withdrawal.bilfnt.dvo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class WwdaDesignationWithdrawalCustomerMgtDvo {

    private String rowState;
    private Integer dataRow;
    private String cntr; // 계약번호
    private String cntrNo; // 계약상세번호
    private String cntrSn; // 계약상세 일련번호
    private Integer dsnWdrwAmt; // 지정금액
    private Integer dpAmt; // 입금금액
    private Integer ucAmt; // 잔액
    private String fntYn; // 이체구분
    private String fntYm; // 이체년월
    private String dsnWdrwFntD; // 이체일
    private String dsnWdrwFntPrdCd; // 이체주기코드
    private String bilNo; // 청구번호
    private String bilDtlSn; // 청구상세일련번호
    private String dtaDlYn; // 삭제여부
    private String cstKnm; // 고객명
    private String sellTpCd; // 상품코드

}
