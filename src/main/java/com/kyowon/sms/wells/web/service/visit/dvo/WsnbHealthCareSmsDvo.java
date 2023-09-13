package com.kyowon.sms.wells.web.service.visit.dvo;

import com.sds.sflex.system.config.annotation.DBDecField;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class WsnbHealthCareSmsDvo {
    String cntrNo;
    String cntrSn;
    String spmtCstSvUAgYn;
    String pifThpOfrAgYn;
    String agpNm;
    String cstNm;
    String svBizDclsfCd;
    String asnDt;
    String cstSvAsnNo;
    String cralLocaraTno;
    @DBDecField
    String mexnoEncr;
    String cralIdvTno;
}
