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

    static final String name = "회사코드";

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


/*
	1	01	본인                                                                                              							                    												(이전작업본 업무공통코드그룹의 코드 그대로 등록)
	2	02	자녀                                                                                              							                    												(이전작업본 업무공통코드그룹의 코드 그대로 등록)
	3	03	부(아버지)                                                                                      							                    												(이전작업본 업무공통코드그룹의 코드 그대로 등록)
	4	04	모(어머니)                                                                                      							                    												(이전작업본 업무공통코드그룹의 코드 그대로 등록)
	5	05	조부모                                                                                            							                    												(이전작업본 업무공통코드그룹의 코드 그대로 등록)
	6	06	친척                                                                                              							                    												(이전작업본 업무공통코드그룹의 코드 그대로 등록)
	7	07	지인                                                                                              							                    												(이전작업본 업무공통코드그룹의 코드 그대로 등록)
	8	08	법인                                                                                              							                    												(이전작업본 업무공통코드그룹의 코드 그대로 등록)
	9	09	배우자                                                                                            							                    												(이전작업본 업무공통코드그룹의 코드 그대로 등록)
	10	10	제휴판매고객                                                                                      							                    												(이전작업본 업무공통코드그룹의 코드 그대로 등록)

* */
