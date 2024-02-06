package com.kyowon.sms.wells.web.service.stock.dvo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter

public class WsnaStockContinueMonthAgrgStateDvo {

    String baseYm;
    String itmGdCd;
    String useYn;
    String stockTpCd;
    String markTp;
    String itmPdCd;
    String itmKndCd;
    String strtSapCd;
    String endSapCd;
    String itmGrpCd;
    String wareNoInStr;
    String wareNoPitmFields;
    String wareNoKeppMmFields;
    String wareNoPitmSumFields;
    String sapMatCd;
    String pdNm;
    String ordnyHvMatYn;
    String cmnPartDvCd;
    String trnovrRtOjYn;
    List<String> itmPdCds; // 품목코드
}
