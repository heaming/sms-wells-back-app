package com.kyowon.sms.wells.web.contract.zcommon.constants;

import com.sds.sflex.system.config.exception.BizException;

import java.util.Arrays;
import java.util.Objects;

public enum CtTxinvPblDvCd {
    CD_01("01", "세금계산서"),
    CD_02("02", "계산서"),
    CD_03("03", "영수증"),
    CD_04("04", "확인증"),
    CD_05("05", "청구서"),
    CD_06("06", "견적서"),
    CD_07("07", "납품서");

    final String code;
    final String codeName;

    static final String name = "세금계산서발행구분코드";

    CtTxinvPblDvCd(String code, String codeName) {
        this.code = code;
        this.codeName = codeName;
    }

    public String getCode() {
        return code;
    }

    public String getCodeName() {
        return codeName;
    }

    public static CtTxinvPblDvCd of(String code) {
        return Arrays.stream(values())
            .filter((value) -> Objects.equals(value.getCode(), code))
            .findFirst()
            .orElseThrow(() -> new BizException(name + "을 확인해보세요."));
    }

    public static CtTxinvPblDvCd of(CtSellTpCd sellTpCd) {
        if (Objects.requireNonNull(sellTpCd) == CtSellTpCd.RGLR_SPP) {
            return CtTxinvPblDvCd.CD_02;
        } else {
            return CtTxinvPblDvCd.CD_01;
        }
    }
}
