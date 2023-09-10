package com.kyowon.sms.wells.web.organization.zcommon.constants;

import com.sds.sflex.system.config.constant.CommConst;

public class OgConst {
    public static final String REST_PREFIX_SMS_WELLS = CommConst.REST_URL_V1 + "/sms/wells";

    public static final String REST_PREFIX_INTERFACE_SMS_WELLS = CommConst.REST_URL_V1 + "/interface/sms/wells";

    // 자격신청구분코드
    public enum QlfAplcDvCd {
        QLF_APLC_DV_CD_1("1", "승급"),
        QLF_APLC_DV_CD_2("2", "자격해제"),
        QLF_APLC_DV_CD_3("3", "보류");

        private final String code;
        private final String name;

        QlfAplcDvCd(String code, String name) {
            this.code = code;
            this.name = name;
        }

        public String getCode() {
            return code;
        }

        public String getName() {
            return name;
        }
    }
}
