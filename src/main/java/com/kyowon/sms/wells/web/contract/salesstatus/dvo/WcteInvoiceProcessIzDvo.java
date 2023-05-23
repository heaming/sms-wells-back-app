package com.kyowon.sms.wells.web.contract.salesstatus.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WcteInvoiceProcessIzDvo {
    private String cntrNo; /*계약번호*/
    private int cntrSn; /*계약일련번호*/
    private String sppBzsOrdId; /* 엑셀:주문번호, 컬럼:배송업체주문ID*/
    private String sppFshDt; /* 엑셀:설치일자, 컬럼:배송완료일시*/
}
