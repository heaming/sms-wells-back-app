package com.kyowon.sms.wells.web.contract.zcommon.constants;

import com.sds.sflex.system.config.exception.BizException;

import java.util.Arrays;
import java.util.Objects;

public enum CtCopnDvCd {
    INDIVIDUAL("1"), /*개인*/
    COOPERATION("2"); /*법인*/

    final String code;

    static final String name = "법인격구분코드";

    CtCopnDvCd(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static CtCopnDvCd of(String code) {
        return Arrays.stream(values())
            .filter((value) -> Objects.equals(value.getCode(), code))
            .findFirst()
            .orElseThrow(() -> new BizException(name + "을 확인해보세요."));
    }
}
