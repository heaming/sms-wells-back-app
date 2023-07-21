package com.kyowon.sms.wells.web.contract.zcommon.constants;

import com.sds.sflex.system.config.exception.BizException;

import java.util.Arrays;
import java.util.Objects;

public enum CtFnitAprRsCd {
    ERROR("B", "오류(승인결과)"),
    NO_APPROVAL("N", "승인전/이체중지"),
    APPROVAL("Y", "승인완료");

    final String code;
    final String codeName;

    static final String name = "수납요청상태코드";

    CtFnitAprRsCd(String code, String codeName) {
        this.code = code;
        this.codeName = codeName;
    }

    public String getCode() {
        return code;
    }

    public String getCodeName() {
        return codeName;
    }

    public static CtFnitAprRsCd of(String code) {
        return Arrays.stream(values())
            .filter((value) -> Objects.equals(value.getCode(), code))
            .findFirst()
            .orElseThrow(() -> new BizException(name + "을 확인해보세요."));
    }
}
