package com.kyowon.sms.wells.web.contract.zcommon.constants;

import com.sds.sflex.system.config.constant.CommConst;

public class CtContractConst {

    private CtContractConst() {
    }

    public static final String REST_URL_V1 = CommConst.REST_URL_V1 + "/sms/wells/contract";

    public static final String INTERFACE_URL_V1 = CommConst.REST_URL_V1 + "/interface/sms/wells/contract";

    public enum PeriodType {
        RCT_DT, /*접수일자*/
        SL_DT,/*매출일자*/
        CAN_DT, /*취소일자*/
        IST_DT, /*설치일자*/
        EXN_DT /*만료일자*/
    }

}
