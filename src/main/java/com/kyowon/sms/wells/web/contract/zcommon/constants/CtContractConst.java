package com.kyowon.sms.wells.web.contract.zcommon.constants;

import com.sds.sflex.system.config.constant.CommConst;

public class CtContractConst {

    private CtContractConst() {}

    public static final String REST_URL_V1 = CommConst.REST_URL_V1 + "/sms/wells/contract";
    public static final String TNO_DELIM = "-";

    public enum CntrStatCd {
        /*
        계약상태코드(계약상태상세코드)
        001: 정상(101: 정상)
        002: 정지(201: 고객요청정지, 202: 연체정지, 203: 해약접수정지)
        003: 해약(301: 고객요청해약, 302: 연체해약, 303: 계약취소)
        004: 종료(401: 최종종료, 402: 계약기간종료)
         */
        NORMAL("001", "101"),
        SUSPENSION("002", "201", "202", "203"),
        CANCELLATION("003", "301", "302", "303"),
        TERMINATION("004", "401", "402");

        private final String code;
        private final String[] dtlCodes;

        CntrStatCd(String code, String... dtlCodes) {
            this.code = code;
            this.dtlCodes = dtlCodes;
        }

        public String getCd() {
            return code;
        }

        public String[] getDtlCds() {
            return dtlCodes;
        }
    }
}
