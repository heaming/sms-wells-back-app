package com.kyowon.sms.wells.web.service.zcommon.constants;

import com.sds.sflex.system.config.constant.CommConst;

public class SnServiceConst {

    private SnServiceConst() {}

    @Deprecated
    public static final String REST_URL_WELLS_SERVICE = "/api/v1/sms/wells/service";

    public static final String REST_URL_V1 = CommConst.REST_URL_V1 + "/sms/wells/service";
    public static final String INTERFACE_URL_V1 = CommConst.REST_URL_V1 + "/sms/wells/interfaces/";

    public static final String REST_INTERFACE_DOC_V1 = "[WSNI] Wells 서비스 인터페이스 REST API";
    public static final String REST_INTERFACE_URL_V1 = CommConst.REST_URL_V1 + "/interface/sms/wells/service";

    // 창고상세구분코드 (WARE_DTL_DV_CD)
    public enum WareDtlDvCd {
        LOGISTICS_CENTER("10", "물류센터"),
        SERVICE_CENTER_ORGANIZATION("20", "서비스센터 조직창고"),
        SERVICE_CENTER_INDIVIDUAL("21", "서비스센터 개인창고"),
        BUSINESS_CENTER_ORGANIZATION("30", "영업센터 조직창고"),
        BUSINESS_CENTER_INDIVIDUAL("31", "영업센터 개인창고"),
        BUSINESS_CENTER_INDEPENDENCE("32", "영업센터 독립창고");

        private final String code;
        private final String name;

        WareDtlDvCd(String code, String name) {
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
