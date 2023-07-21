package com.kyowon.sms.wells.web.contract.zcommon.constants;

import com.sds.sflex.system.config.exception.BizException;

import java.util.Arrays;
import java.util.Objects;

public enum CtRvePhCd {
    BIL_AC("01", "청구이체-계좌"),
    BIL_CARD("02", "청구이체-카드"),
    IDV_AC("03", "개별수납-계좌"),
    IDV_CARD("04", "개별수납-카드"),
    SELL_AC("05", "판매-카드"),
    SELL_CARD("06", "판매-계좌"),
    SV_AC("07", "서비스-계좌"),
    SV_CARD("08", "서비스-카드"),
    CSMR_NOTY_AC("09", "소비자알림센터-계좌"),
    CSMR_NOTY_CARD("10", "소비자알림센터-카드"),
    BND("11", "채권"),
    MUTU_ALNC("12", "상조제휴"),
    BU_NOTI("13", "부담통보"),
    WAPB("14", "무통장"),
    ETC("99", "기타");

    final String code;
    final String codeName;

    static final String name = "수납경로";

    CtRvePhCd(String code, String codeName) {
        this.code = code;
        this.codeName = codeName;
    }

    public String getCode() {
        return code;
    }

    public String getCodeName() {
        return codeName;
    }

    public static CtRvePhCd of(String code) {
        return Arrays.stream(values())
            .filter((value) -> Objects.equals(value.getCode(), code))
            .findFirst()
            .orElseThrow(() -> new BizException(name + "을 확인해보세요."));
    }
}


/*
01    청구이체-계좌
02    청구이체-카드
03    개별수납-계좌
04    개별수납-카드
05    판매-카드
06    판매-계좌
07    서비스-계좌
08    서비스-카드
09    소비자알림센터-계좌
10    소비자알림센터-카드
11    채권
12    상조제휴
13    부담통보
14    무통장
99    기타
*
* */
