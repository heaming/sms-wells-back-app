package com.kyowon.sms.wells.web.service.stock.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WsnaStockAcinspRgstMngtDvo {
    String apyYm; /* 적용년월 */
    String wareNo; /* 창고번호 */
    String wareNm; /* 창고명 */
    String itmKnd; /* 품목구분 */
    String sapCd; /* SAP코드*/
    String itmPdCd; /* 품목상품코드 */
    String pdAbbrNm; /* 품목약어명 */
    int acinspQty; /* 실사수량 */
    int eotStoc; /* 기말수량 */
    int minusQty; /* 차이수량*/
    String acinspRmkCn; /* 실사비고 */
    String cnfmdt; /* 확정일자 */
    int cnfmPitmEotStocQty; /* 확정시점기말재고수량 */
    int cnfmPitmStrGapQty; /* 확정시점입고차이수량 */
    int cnfmPitmOstrGapQty; /* 확정시점출고차이수량 */
    int reCnfmPitmStrGapQty; /* 재조회 확정시점입고차이수량 */
    int reCnfmPitmOstrGapQty; /* 재조회 확정시점출고차이수량 */
    String diffQty; /* 확정차이수량 */
    String iostRfdt; /* 입출고반영일자 */
    String baseYm; /* 기준년월 */
    String wareDvCd; /* 창고구분코드 */
    String wareDtlDvCd; /* 창고상세구분코드 */
    String hgrWareNo; /* 상위창고번호 */
    String searchWareNo; /* 조회창고번호 */
    String useYn; /* 사용여부 */
    String stocAcinspAkId; /*재고실사요청ID*/

    String pdCd; /* 상품코드 */
    String pitmStocAGdQty; /* 시점재고A등급수량 */
    String cstPitmStocAGdQty; /* 고객시점재고A등급수량 */
}
