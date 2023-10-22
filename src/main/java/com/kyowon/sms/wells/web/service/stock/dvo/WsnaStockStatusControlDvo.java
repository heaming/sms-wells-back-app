package com.kyowon.sms.wells.web.service.stock.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WsnaStockStatusControlDvo {

    String wareNo; /*창고번호 */
    String wareNm; /* 창고명 */
    String wareMngtPrtnrNo; /* 창고관리파트너번호 */
    String wareDvCd; /* 창고구분코드 */
    String ogNm; /* 조직명 */
    String itemKnd; /* 품목구분 */
    String ctrWkDt; /* 조정작업일자 */
    String statCtrApyDt; /* 상태조정적용일자 */
    String itmPdCd; /* 품목상품코드 */
    String itmGdCtrTpCd; /* 품목등급조정유형코드 */
    String itmGdCtrTpNm; /* 품목등급조정유형명 */
    String itmPdNm; /* 품목상품명 */
    String mgtUnit; /* 관리단위 */
    int bfctNomStocAGdQty;/* 조정전정상재고A등급수량 */
    int bfctNomStocEGdQty;/* 조정전정상재고E등급수량 */
    int bfctNomStocRGdQty;/* 조정전정상재고R등급수량 */
    String bfctItmGdCd;/* 조정전품목등급코드 */
    String afctItmGdCd;/* 조정후품목등급코드 */
    int ctrQty; /* 조정수량 */
    String itmGdCtrRsonCd; /* 품목등급조정사유코드 */
    String itmGdCtrRsonNm; /* 품목등급조정사유명*/
    String ctrSn; /* 조정일련번호 */
    String rmkCn; /* 비고 */
}
