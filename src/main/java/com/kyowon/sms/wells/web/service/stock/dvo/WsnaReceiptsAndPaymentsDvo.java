package com.kyowon.sms.wells.web.service.stock.dvo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class WsnaReceiptsAndPaymentsDvo {

    String strWareDvCd;
    String strWareNoM;
    String strWareNoD;
    String stFromYmd;
    String edToYmd;
    String itmKnd;
    String itmGdCd;
    String useYn;
    String itmPdCdFrom;
    String sapMatCdFrom;
    String sapMatCdTo;
    List<String> sapMatDpcts;
    Integer offset;
    Integer size;
}
