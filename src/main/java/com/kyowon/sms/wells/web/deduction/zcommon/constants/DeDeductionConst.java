package com.kyowon.sms.wells.web.deduction.zcommon.constants;

import com.sds.sflex.system.config.constant.CommConst;

public class DeDeductionConst {
    public static final String REST_URL_V1 = "/api/v1/sms/wells/deduction";

    //wells interface deduction URL
    public static final String INTERFACE_URL_V1 = CommConst.REST_URL_V1 + "/interface/sms/wells/deduction";

    private DeDeductionConst() {
        throw new IllegalStateException("DeductionConst class");
    }
}
