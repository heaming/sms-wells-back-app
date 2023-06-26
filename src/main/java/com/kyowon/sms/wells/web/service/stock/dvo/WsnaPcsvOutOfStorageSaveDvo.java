package com.kyowon.sms.wells.web.service.stock.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WsnaPcsvOutOfStorageSaveDvo {

    /* 작업결과 */
    String cstSvAsnNo;
    String prtnrNo;
    String siteAwSvTpCd;
    String siteAwAtcCd;
    String cntrNo;
    String cntrSn;
    String urgtYn;
    String rpbLocaraCd;
    String asLctCd;
    String asPhnCd;
    String asCausCd;
    String ivcPrntSn;
    String ogTpCd;
    String pdGrpCd;

    /* 사용제품 내역등록 ,수불처리 */
    String pdGdCd;
    String useQty;
    String wkWareNo;
    String pdCd;
    String svBizHclsfCd;
    String svBizDclsfCd;
    String asRefriDvCd;
    String bfsvcRefriDvCd;
    String filtSellTpCd;
    String pdSellTpCd;
    String pdUswyCd;
    String istDt;

}
