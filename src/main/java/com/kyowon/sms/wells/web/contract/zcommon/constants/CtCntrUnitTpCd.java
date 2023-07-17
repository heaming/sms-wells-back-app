package com.kyowon.sms.wells.web.contract.zcommon.constants;

import com.sds.sflex.system.config.exception.BizException;

import java.util.Arrays;
import java.util.Objects;

public enum CtCntrUnitTpCd {
    CNTR_BAS("010", "계약"),
    CNTR_DTL("020", "계약상세"),
    ;

    final String code;
    final String codeName;

    static final String name = "회사코드";

    CtCntrUnitTpCd(String code, String codeName) {
        this.code = code;
        this.codeName = codeName;
    }

    public String getCode() {
        return code;
    }

    public String getCodeName() {
        return codeName;
    }

    public static CtCntrUnitTpCd of(String code) {
        return Arrays.stream(values())
            .filter((value) -> Objects.equals(value.getCode(), code))
            .findFirst()
            .orElseThrow(() -> new BizException(name + "을 확인해보세요."));
    }
}


/*
* 	1	010	판매자
	2	020	소개자
	3	030	홍보교사
	4	040	대리계약판매자
	5	050	관리자
	6	060	TM판매자

* */
