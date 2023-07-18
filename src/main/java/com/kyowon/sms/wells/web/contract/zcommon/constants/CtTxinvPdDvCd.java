package com.kyowon.sms.wells.web.contract.zcommon.constants;

import com.sds.sflex.system.config.exception.BizException;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

public enum CtTxinvPdDvCd {
    CD_10("10", "전집"),
    CD_11("11", "전집도서"),
    CD_20("20", "웰스"),
    CD_21("21", "웰스일시불"),
    CD_22("22", "웰스필터"),
    CD_23("23", "웰스렌탈"),
    CD_24("24", "웰스등록비"),
    CD_25("25", "웰스멤버십"),
    CD_26("26", "웰스AS"),
    CD_27("27", "정기배송"),
    CD_28("28", "웰스할부"),
    CD_30("30", "학습&도서"),
    CD_31("31", "빨간펜"),
    CD_32("32", "과학소년"),
    CD_33("33", "잡지"),
    CD_34("34", "JEM"),
    CD_35("35", "+JEM"),
    CD_40("40", "유아"),
    CD_41("41", "지도회비");

    final String code;
    final String codeName;

    static final String name = "세금계산서상품구분코드";

    CtTxinvPdDvCd(String code, String codeName) {
        this.code = code;
        this.codeName = codeName;
    }

    public String getCode() {
        return code;
    }

    public String getCodeName() {
        return codeName;
    }

    public static CtTxinvPdDvCd of(String code) {
        return Arrays.stream(values())
            .filter((value) -> Objects.equals(value.getCode(), code))
            .findFirst()
            .orElseThrow(() -> new BizException(name + "을 확인해보세요."));
    }

    public static Optional<CtTxinvPdDvCd> of(CtSellTpCd sellTpCd) {
        CtTxinvPdDvCd rtn;
        switch (sellTpCd) {
            case SPAY -> rtn = CtTxinvPdDvCd.CD_21;
            case RENTAL -> rtn = CtTxinvPdDvCd.CD_23;
            case MSH -> rtn = CtTxinvPdDvCd.CD_25;
            case RGLR_SPP -> rtn = CtTxinvPdDvCd.CD_27;
            default -> rtn = null;
        }
        return Optional.ofNullable(rtn);
    }
}
