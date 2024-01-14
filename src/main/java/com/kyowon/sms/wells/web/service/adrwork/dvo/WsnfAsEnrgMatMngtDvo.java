package com.kyowon.sms.wells.web.service.adrwork.dvo;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WsnfAsEnrgMatMngtDvo {
    String pdCd;
    String pdNm;
    String sapMatCd;
    String itmPdCd;
    String itmPdNm;
    String recomSapCd;
    String cnslTpLcsfCdNm;
    String cnslCn;
    Integer itmRcmdRnk;
    BigDecimal itmRcmdQty;
    String cnslTpHcsfCd;
    String cnslTpMcsfCd;
    String cnslTpLcsfCd;
}
