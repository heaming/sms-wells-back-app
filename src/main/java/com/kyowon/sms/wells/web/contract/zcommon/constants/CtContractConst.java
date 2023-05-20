package com.kyowon.sms.wells.web.contract.zcommon.constants;

import com.sds.sflex.system.config.constant.CommConst;

public class CtContractConst {

    private CtContractConst() {}

    public static final String REST_URL_V1 = CommConst.REST_URL_V1 + "/sms/wells/contract";
    public static final String INTERFACE_URL_V1 = CommConst.REST_URL_V1 + "/interface/sms/wells/contract";

    public static final String STRT_DTM = "00000101000000";
    public static final String END_DTM = "99991231235959";
    public static final String EXCEL_UPLOAD_SUCCESS = "S";
    public static final String EXCEL_UPLOAD_ERROR = "E";
    public static final String CNTR_TP_CD_INDV = "01";
    public static final String CNTR_TP_CD_CRP = "02";
    public static final String CNTR_TP_CD_ENSM = "03";
    public static final String CNTR_TP_CD_MSH = "07";
    public static final String CNTR_TP_CD_RSTL = "08";
    public static final String CNTR_TP_CD_QUOT = "09";

    public enum PeriodType {
        RCT_DT, /*접수일자*/
        SL_RCOG_DT, /*매출일자*/
        CAN_DT, /*취소일자*/
        IST_DT, /*설치일자*/
        EXN_DT /*만료일자*/
    }

}
