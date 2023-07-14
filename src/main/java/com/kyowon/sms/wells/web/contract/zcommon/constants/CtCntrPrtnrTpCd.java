package com.kyowon.sms.wells.web.contract.zcommon.constants;

import com.sds.sflex.system.config.exception.BizException;

import java.util.Arrays;
import java.util.Objects;

public enum CtCntrPrtnrTpCd {
    SELLER_PERSON("010", "판매자"),
    RECOMMENDER("020", "소개자"),
    PROMOTION_TEACHER("030", "홍보교사"),
    AGENT_SELLER_PERSON("040", "대리계약판매자"),
    MANAGER("050", "관리자"),
    TM_SELLER_PERSON("060", "TM판매자"),
    ;

    final String code;
    final String codeName;

    static final String name = "회사코드";

    CtCntrPrtnrTpCd(String code, String codeName) {
        this.code = code;
        this.codeName = codeName;
    }

    public String getCode() {
        return code;
    }

    public String getCodeName() {
        return codeName;
    }

    public static CtCntrPrtnrTpCd of(String code) {
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
