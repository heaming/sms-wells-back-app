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

    // 급지구분코드 (RGLVL_DV_CD)
    public enum RglvlDvCd {
        REGION_LEVEL1("1", "1급지(이동급지)"),
        REGION_LEVEL2("2", "2급지(업무급지)");

        private final String code;
        private final String name;

        RglvlDvCd(String code, String name) {
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

    // 창고구분코드 (WARE_DV_CD)
    public enum WareDvCd {
        LOGISTICS("1", "물류센터"),
        SERVICE("2", "서비스센터"),
        BUSINESS("3", "영업센터");

        private final String code;
        private final String name;

        WareDvCd(String code, String name) {
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

    // 입고유형코드 관련 상수 설정
    // 구매입고(110)
    public static final String PRCHS_STR = "110";
    // 기타입고(117)
    public static final String ETC_STR = "117";
    // 정상입고(121)
    public static final String NOM_STR = "121";
    // 물량배정(122)
    public static final String QOM_ASN = "122";
    // 물량이동(123)
    public static final String QOM_MMT = "123";
    // 반품입고(161)
    public static final String RTNGD_STR = "161";
    // 반품외부입고(162)
    public static final String RTNGD_OTSD_STR = "162";
    // 재고조정입고(181)
    public static final String STOC_CTR_STR = "181";

    // 출고유형코드 관련 상수 설정
    // 정상출고(221)
    public static final String NOM_OSTR = "221";
    // 물량배정(222)
    public static final String QOM_ASN_OSTR = "222";
    // 물량이동(223)
    public static final String QOM_MMT_OSTR = "223";
    // 반품출고(내부)(261)
    public static final String RTNGD_OSTR_INSI = "261";
    // 반품출고(외부)(262)
    public static final String RTNGD_OSTR_OTSD = "262";
    // 외부출고(판매)(211)
    public static final String OTSD_OSTR_SELL = "211";
    // 외부출고(폐기)(212)
    public static final String OTSD_OSTR_DSU = "212";
    // 외부출고(작업)(213)
    public static final String OTSD_OSTR_WK = "213";
    // 외부출고(기타)(217)
    public static final String OTSD_OSTR_ETC = "217";
    // 외부출고(리퍼완료)(218)
    public static final String OTSD_OSTR_REFR = "218";
    // 재고조정출고(281)
    public static final String STOC_CTR_OSTR = "281";
    // 외부출고(기타)(292)
    public static final String STOC_ACINSP_OSTR = "292";
    // 이동입고(991)
    public static final String MMT_STR = "991";

}
