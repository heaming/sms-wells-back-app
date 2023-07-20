package com.kyowon.sms.wells.web.service.stock.dvo;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class WsnaItemBaseInformationReturnDvo {
    String sapCd; /*SAP코드*/
    String sapGrp; /*자재그룹*/
    String itmPdCd; /*품목상품코드*/
    String itmPdNm; /*품목상품명*/
    String itmPdAbbr1; /*품목상품명1*/
    BigDecimal lgstQty; /*물류수량*/
    String centerQty; /*센터A등급재고*/
    String myCenterQty; /*조직창고 수량*/
    String indiStckQty; /*개인창고수량*/
    String lQty;
    String itmKnd; /*품목종류*/
    String itmKndNm; /*품목종류명*/
    String delUnt; /*출고단위*/
    String delUntNm; /*출고단위명*/
    String imgUrl; /*이미지URL*/
    String apldFr; /*사용시작일*/
    String apldTo; /*사용중지일*/
    String boxQty; /*박스수량*/
    String leadTime;/*leadTime*/
}
