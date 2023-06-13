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
    String mngtUnit; /*관리단위*/
    String rmkCn; /*입고사유*/

    //서비스 호출을 위한 파라미터 및 키값 세팅
    String itmStrNo;
    int itmStrSn;
    String strTpCd;
    String strRgstYm;
    String wareDvCd;
    String wareMngtPrtnrNo;
    String workDiv;

}
