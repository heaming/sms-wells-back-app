package com.kyowon.sms.wells.web.service.stock.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class WsnaStockStateIndividualWareDvo {

    String wareNo;
    String sapMatCd;
    String kiwiPdCd;
    String pdNm;
    String lctNm;
    String ordnyHvMatYn;
    String cmnPartDvCd;
    String wareNoInStr;
    String agrgQty;
    String wareNoAgrgQtyFields;
    String wareNoAgrgQtySumFields;
    String stockDt;
    String baseYm;
    String wareUseYn;
    String itmKndCd;
    String itmGdCd;
    String useYn;
    String stndUnuseYn;
    String fromSapCd;
    String toSapCd;
}
