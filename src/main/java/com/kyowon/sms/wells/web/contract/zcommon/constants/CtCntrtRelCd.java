package com.kyowon.sms.wells.web.contract.zcommon.constants;

import com.sds.sflex.system.config.exception.BizException;

import java.util.Arrays;
import java.util.Objects;

public enum CtCntrtRelCd {
    SELF("01", "본인"),
    CHILDREN("02", "자녀"),
    FATHER("03", "부"),
    MOTHER("04", "모"),
    GRANDPARENT("05", "조부모"),
    KINDRED("06", "친척"),
    FRIEND("07", "지인"),
    CORPORATION("08", "법인"),
    SPOUSE("09", "배우자"),
    ALLIANCE("10", "제휴판매고객");

    final String code;
    final String codeName;

    static final String name = "계약자관계코드";

    CtCntrtRelCd(String code, String codeName) {
        this.code = code;
        this.codeName = codeName;
    }

    public String getCode() {
        return code;
    }

    public String getCodeName() {
        return codeName;
    }

    public static CtCntrtRelCd of(String code) {
        return Arrays.stream(values())
            .filter((value) -> Objects.equals(value.getCode(), code))
            .findFirst()
            .orElseThrow(() -> new BizException(name + "을 확인해보세요."));
    }
}
