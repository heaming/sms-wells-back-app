package com.kyowon.sms.wells.web.contract.zcommon.constants;

import com.sds.sflex.system.config.exception.BizException;

import java.util.Arrays;
import java.util.Objects;

public enum CtDpMesCd {
    CASH("01", "현금"),
    CREDIT_CARD("02", "신용카드"),
    BILL("03", "어음"),
    GIRO("04", "지로"),
    DEDUCTION("05", "공제"),
    POINT("06", "포인트"),
    MILEAGE("07", "마일리지"),
    K_MEMBERS_CASH("08", "캐시");

    final String code;
    final String codeName;

    static final String name = "입금수단코드";

    CtDpMesCd(String code, String codeName) {
        this.code = code;
        this.codeName = codeName;
    }

    public String getCode() {
        return code;
    }

    public String getCodeName() {
        return codeName;
    }

    public static CtDpMesCd of(String code) {
        return Arrays.stream(values())
            .filter((value) -> Objects.equals(value.getCode(), code))
            .findFirst()
            .orElseThrow(() -> new BizException(name + "을 확인해보세요."));
    }
}

/*

- 01 : 현금
- 02 : 신용카드
- 03 : 어음
- 04 : 지로
- 05 : 공제
- 06 : 포인트
- 07 : 마일리지
- 08 : 캐시

* */
