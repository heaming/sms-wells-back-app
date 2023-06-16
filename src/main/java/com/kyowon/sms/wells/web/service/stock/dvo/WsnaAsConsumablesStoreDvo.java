package com.kyowon.sms.wells.web.service.stock.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WsnaAsConsumablesStoreDvo {
    String strWareNo; /*입고창고번호*/
    String wareNm; /*창고명*/
    String strRgstDt; /*입고등록일자*/
    String sapCd; /*sap코드*/
    String itmPdCd; /*품목상품코드*/
    String itmPdNm; /*품목상품명*/
    String itmGdCd; /*품목등급코드*/
    String strQty; /*입고수량*/
    String rmkCn; /*입고사유*/

    //서비스 호출을 위한 파라미터 및 키값 세팅
    String itmStrNo;
    int itmStrSn;
    String strTpCd;
    String strRgstYm;
    String wareDvCd;

    //삭제처리를 위한 시점재고A등급수량
    int pitmStocAGdQty;
    int pitmStocEGdQty;
    int monthlyPitmStocAGdQty;
    int monthlyPitmStocEGdQty;
    int etcStrAGdQty;
    int etcStrEGdQty;

    //품목재고내역을 호출하기위한 파라미터 세팅
    private String procsYm; /*처리년월*/
    private String procsDt; /*처리일자*/
    private String wareDv; /*창고구분*/
    private String wareNo; /*창고번호*/
    private String wareMngtPrtnrNo; /*창고관리파트너번호*/
    private String iostTp; /*입출고유형*/
    private String workDiv; /*작업구분*/
    private String mngtUnit; /*관리단위*/
    private String itemGd; /*상품등급*/
    private String qty; /*수량*/

}
