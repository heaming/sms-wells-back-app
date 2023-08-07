package com.kyowon.sms.wells.web.service.stock.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WsnaStockStatusControlDvo {

    String wareNo;
    String wareNm;
    String wareMngtPrtnrNo;
    String wareDvCd;
    String ogNm;
    String itemKnd;
    String ctrWkDt;
    String statCtrApyDt;
    String itmPdCd;
    String itmGdCtrTpNm;
    String itmPdNm;
    String mgtUnit;
    int bfctNomStocAGdQty;
    int bfctNomStocEGdQty;
    int bfctNomStocRGdQty;
    String bfctItmGdCd;
    String afctItmGdCd;
    int ctrQty;
    String itmGdCtrRsonNm;
    String ctrSn;
    String rmkCn;
}
