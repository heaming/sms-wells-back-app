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
    String rowState;
    String itmPdCd;
    String cstSvAsnNo;
    String itmKndCd;
    String stFnlVstFshDtFrom;
    String edFnlVstFshDtTo;
    String strWareDvCd;
    String strWareNoM;
    String strWareNoD;

    String useQty; /*수량*/
    String ostrConfDt; /*확인일자*/
    String rtngdProcsTpCd; /*반품처리유형코드*/
    String mgtUnt; /*관리단위*/
    String itemKnd; /*품목종류*/
    String WareNm; /*창고명*/
    String wkWareNo; /*작업창고번호*/
    String rtngdConfYn; /*반품확인여부*/
    String fnlItmGdCd; /*최종품목등급코드*/
    String itemGrNm;
    String itemGr;

    String itmOstrNo;
    String ostrSn;
    String strSn;
    String ostrTpCd;
    String ostrDt;
    String itmStrNo;
    String rtngdRvpyProcsYn;
    String cfrmDt;

    String hgrWareNo;
    String upHgrWareNo;
    String hgrWarePrtnrNo;
    String upHgrWarePrtnrNo;

    String ostrAkNo;
    String ostrAkSn;
    String disuseItmOstrNo;
    String quantityItmOstrNo;
    String strQuantityItmStrNo;

    String ostrAkTpCd;
    String disuseOstrTpCd;
    String strQuantityStrTpCd;
    String quantityOstrTpCd;
    String stkrPrntYn;
    String rmkCn;
    String cntrNo;
    String cntrSn;
    String wkOstrSn;

    //물류에 전송하기 위한 데이터 dvo
    private String ostrAkRgstDt;
    private String strHopDt;
    private String lgstStrTpCd;
    private String iostAkDvCd;
    private String wareMngtPrtnrNo;
    private String wareMngtPrtnrOgTpCd;
    private String sapIostTpCd;
    private String lgstSppMthdCd;
    private String ostrAkQty;
    private String ostrOjWareNo;
    private String svCnrCd;
    private String svCnrNm;
    private String itmGdCd;
}
