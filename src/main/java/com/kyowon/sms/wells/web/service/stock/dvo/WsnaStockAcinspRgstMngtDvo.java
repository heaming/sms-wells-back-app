package com.kyowon.sms.wells.web.service.stock.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WsnaStockAcinspRgstMngtDvo {
    String apyYm;
    String wareNo;
    String wareNm;
    String itmKnd;
    String sapCd;
    String itmPdCd;
    String pdAbbrNm;
    int acinspQty;
    int eotStoc;
    int minusQty;
    String acinspRmkCn;
    String cnfmdt;
    int cnfmPitmEotStocQty;
    int cnfmPitmStrGapQty;
    int cnfmPitmOstrGapQty;
    String diffQty;
    String iostRfdt;
    String baseYm;
    String wareDvCd;
    String wareDtlDvCd;
    String searchWareNo;
    String useYn;

    String pdCd;
    String pitmStocAGdQty;
    String cstPitmStocAGdQty;
}
