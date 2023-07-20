package com.kyowon.sms.wells.web.service.stock.dvo;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class WsnaItemBaseInformationDvo {
    String itmPdCd; /*품목상품코드*/
    String itmPdNm; /*품목상품명*/
    String sapCd; /*SAP코드*/
    String itmPdNm1; /*품목상품명1*/
    String imgUrl; /*이미지URL*/
    String itemKnd; /*품목종류*/
    String boxUnitQty; /*박스단위수량*/
    String mngtUnitCd; /*신청단위코드*/
    String delUnt; /*관리단위*/
    BigDecimal warehouseQty; /* 현재출고창고재고 */
    String centerQty; /*현재센터A등급재고(조직)*/
    String centerBqty; /*현재센터B등급재고(조직)*/
    String indiQty; /*개인수량*/
    String useQty; /*당월사용수량*/
    String useQtyP; /*전월기준재고*/
    String useQtyY; /*전년도*/
    String shortSupply; /*신청 예상수량*/
    String totalQty;/*총재고*/
}
