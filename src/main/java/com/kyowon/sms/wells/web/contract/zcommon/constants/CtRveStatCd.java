package com.kyowon.sms.wells.web.contract.zcommon.constants;

import com.sds.sflex.system.config.exception.BizException;

import java.util.Arrays;
import java.util.Objects;

public enum CtRveStatCd {
    REQUEST("00", "수납요청"),
    PARTIAL("01", "부분수납"),
    COMPLETE("02", "수납완료"),
    CANCLE("99", "요청취소");

    final String code;
    final String codeName;

    static final String name = "수납상태코드";

    CtRveStatCd(String code, String codeName) {
        this.code = code;
        this.codeName = codeName;
    }

    public String getCode() {
        return code;
    }

    public String getCodeName() {
        return codeName;
    }

    public static CtRveStatCd of(String code) {
        return Arrays.stream(values())
            .filter((value) -> Objects.equals(value.getCode(), code))
            .findFirst()
            .orElseThrow(() -> new BizException(name + "을 확인해보세요."));
    }
}
