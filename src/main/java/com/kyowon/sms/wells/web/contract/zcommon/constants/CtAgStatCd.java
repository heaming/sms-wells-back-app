package com.kyowon.sms.wells.web.contract.zcommon.constants;

import com.sds.sflex.system.config.exception.BizException;

import java.util.Arrays;
import java.util.Objects;

public enum CtAgStatCd {
    AG("01", "동의"),
    REJ("02", "거부"),
    UNDEF("03", "미결정");

    final String code;
    final String codeName;

    static final String name = "동의상태코드";

    CtAgStatCd(String code, String codeName) {
        this.code = code;
        this.codeName = codeName;
    }

    public String getCode() {
        return code;
    }

    public static CtAgStatCd of(String code) {
        return Arrays.stream(values())
            .filter((value) -> Objects.equals(value.getCode(), code))
            .findFirst()
            .orElseThrow(() -> new BizException(name + "을 확인해보세요."));
    }
}


/*	1	01	동의
	2	02	거부
	3	03	미결정
*/
