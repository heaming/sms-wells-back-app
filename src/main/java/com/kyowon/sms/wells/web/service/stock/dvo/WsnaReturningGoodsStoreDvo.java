package com.kyowon.sms.wells.web.service.stock.dvo;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * W-SV-U-0108M01 반품입고 등록
 * </pre>
 *
 * @author SongTaeSung
 * @since 2023.07.20
 */
@Getter
@Setter
public class WsnaReturningGoodsStoreDvo {
    String rowState; /*행상태*/
    String itmPdCd; /*품목상품코드*/
    String cstSvAsnNo; /*고객서비스배정번호*/
    String itmKndCd; /*품목구분코드*/
    String stFnlVstFshDtFrom; /*처리일자FROM*/
    String edFnlVstFshDtTo; /*처리일자TO*/
    String strWareDvCd; /*입고창고구분코드*/
    String strWareNoM; /*입고창고상위*/
    String strWareNoD; /*입고창고*/

    String useQty; /*수량*/
    String ostrConfDt; /*확인일자*/
    String rtngdProcsTpCd; /*반품처리유형코드*/
    String mgtUnt; /*관리단위*/
    String itemKnd; /*품목종류*/
    String WareNm; /*창고명*/
    String wkWareNo; /*작업창고번호*/
    String rtngdConfYn; /*반품확인여부*/
    String fnlItmGdCd; /*최종품목등급코드*/
    String itemGrNm; /*품목구분명*/
    String itemGr; /*품목구분*/

    String itmOstrNo; /*품목출고번호*/
    String ostrSn; /*출고순번*/
    String strSn; /*입고순번*/
    String ostrTpCd; /*출고구분코드*/
    String ostrDt; /*출고일자*/
    String itmStrNo; /*품목입고번호*/
    String rtngdRvpyProcsYn; /*반품수불여부*/
    String cfrmDt; /*확정일자*/
    String vstFshDt; /*처리일자*/

    String hgrWareNo; /*상위창고번호*/
    String upHgrWareNo; /*상위상위창고번호*/
    String hgrWarePrtnrNo; /*상위창고파트너번호*/
    String upHgrWarePrtnrNo; /*상위상위파트너번호*/

    String ostrAkNo; /*출고요청번호*/
    String ostrAkSn; /*출고요청순번*/
    String disuseItmOstrNo; /*폐기출고번호*/
    String quantityItmOstrNo; /*물량이동품목출고번호*/
    String strQuantityItmStrNo; /*물량품목입고번호*/

    String ostrAkTpCd; /*출고요청구분코드*/
    String disuseOstrTpCd; /*폐기요청구분코드*/
    String strQuantityStrTpCd; /*입고물량이동품목구분코드*/
    String quantityOstrTpCd; /*물량출고구분코드*/
    String stkrPrntYn; /*스티커출력여부*/
    String rmkCn; /*비고*/
    String cntrNo; /*계약번호*/
    String cntrSn; /*계약순번*/
    String wkOstrSn; /*작업출고순번*/

    //물류에 전송하기 위한 데이터 dvo
    private String ostrAkRgstDt; /*출고요청등록일자*/
    private String strHopDt; /*입고희망일자*/
    private String lgstStrTpCd; /*물류입고구분코드*/
    private String iostAkDvCd; /*입출고요청구분콛,*/
    private String wareMngtPrtnrNo; /*창고관리파트너번호*/
    private String wareMngtPrtnrOgTpCd; /*창고관리파트너조직구분코드*/
    private String sapIostTpCd; /*SAP입출고구분코드*/
    private String lgstSppMthdCd; /*물류배송방식코드*/
    private String ostrAkQty; /*출고요청수량*/
    private String ostrOjWareNo; /*출고요청창고번호*/
    private String svCnrCd; /*서비스센터코드*/
    private String svCnrNm; /*서비스센터명*/
    private String itmGdCd; /*품목등급코드*/
}
