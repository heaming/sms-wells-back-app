package com.kyowon.sms.wells.web.service.stock.dvo;

import com.sds.sflex.system.config.annotation.DBDecField;
import com.sds.sflex.system.config.annotation.DBEncField;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * <pre>
 * W-SV-U-0117M01 출고요청 관리
 * </pre>
 *
 * @author gs.piit58 송태성
 * @since 2022.12.26
 */
@Setter
@Getter
public class WsnaOutOfStorageAskMngtDvo {

    // String strOjWareNo; /* 출고요청창고 = 입고대상창고번호 */
    String ostrAkNo; /* 출고요청번호 */
    String ostrAkTpCd; /* 출고요청유형코드 */
    String wareNm; /* 창고명 */
    String sapCd; /*SAP코드*/
    String ostrAkRgstDt; /*출고요청등록일자*/
    String itmPdCd; /*품목상품코드*/
    String itmPdNm; /*품목상품명*/
    String ostrAkSn; /*출고요청순번*/
    String strHopDt; /*입고희망일자*/
    String mngtUnitCd; /*관리단위코드*/
    String boxUnitgQty; /*박스단위수량*/
    String itmGdCd; /*품목등급코드*/
    String ostrAkQty; /*출고요청수량*/
    String ostrCnfmQty; /*출고확정수량*/
    String rmkCn; /*비고*/
    String ovivTpCd;
    String rectOstrDt; /*최근출고일자*/
    String ostrWareMngtPrtnrNo; /*출고창고관리파트너번호*/
    String ostrOjWareNo; /*출고대상창고번호*/
    String strOjWareNo; /*입고대상창고번호*/
    String itmKnd; /*품목종류*/
    String itmKndNm; /*품목종류명*/
    String imgUrl; /*imgurl*/
    String ostrAkWareDvCd; /*출고요청창고구분코드*/
    String wareMngtPrtnrNo; /*창고관리파트너번호*/
    String warehouseQty; /*출고창고수량*/
    String centerQty; /*센터수량*/
    String indiQty;/*개인수량*/
    String useQty; /*당월수량*/
    String baseStocQty; /*기본재고수량*/

    //물류인터페이스 전송용 dvo
    String rowState;
    String ostrOjWareDvCd;
    String ostrHopDt; /*출고희망일자*/
    String wareMngtPrtnrOgTpCd;
    String mpacSn; /*합포장일련번호*/
    String iostAkDvCd; /*입출고요청구분코드*/
    String sapIostTpCd; /*sap입출고유형코드*/
    String lgstSppMthdCd; /*물류배송방식코드*/
    String lgstWkMthdCd; /*물류작업방식코드*/
    String mdlvPrtnrNo; /*배달파트너번호*/
    //    String lgstItmGdCd; /*물류품목등급코드*/
    String svCnrCd; /*서비스센터코드*/
    String svCnrNm; /*서비스센터명*/
    String svCnrIchrPrtnrNm; /*서비스센터담당파트너명*/
    String locaraTno; /*지역전화번호*/

    @DBDecField
    String exnoEncr; /*전화국번호암호화*/
    String idvTno; /*개별전화번호*/
    String svCnrLkTnoEncr; /*서비스센터전화번호*/
    String svCnrAdr; /*서비스센터 주소*/
    String hsmtrlClsfCd; /*학습지분류코드*/
    String hsmtrlClsfNm; /*학습지분류명*/
}
