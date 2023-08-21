package com.kyowon.sms.wells.web.service.stock.dvo;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

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
    BigDecimal centerQty; /*현재센터A등급재고(조직)*/
    BigDecimal centerBQty; /*현재센터B등급재고(조직)*/
    BigDecimal indiQty; /*개인수량*/
    BigDecimal useQty; /*당월사용수량*/
    BigDecimal useQtyP; /*전월기준재고*/
    BigDecimal useQtyY; /*전년도*/
    BigDecimal shortSupply; /*신청 예상수량*/
    BigDecimal totalQty;/*총재고*/
}
