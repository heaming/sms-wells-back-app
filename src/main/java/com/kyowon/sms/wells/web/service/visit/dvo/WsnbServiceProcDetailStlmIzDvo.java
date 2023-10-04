package com.kyowon.sms.wells.web.service.visit.dvo;

import com.sds.sflex.system.config.annotation.DBDecField;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class WsnbServiceProcDetailStlmIzDvo {

    String stlmDvCd;
    String dpSumAmt;
    String iscmpCd;
    // 카드번호암호화
    @DBDecField
    String crcdnoEncr;
    String istmMcn;

}
