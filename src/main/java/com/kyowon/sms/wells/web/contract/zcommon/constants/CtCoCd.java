package com.kyowon.sms.wells.web.contract.zcommon.constants;

import com.sds.sflex.system.config.exception.BizException;

import java.util.Arrays;
import java.util.Objects;

public enum CtCoCd {
    KYOWON("1200", "교원"),
    KYOWON_PROPERTY("2000", "교원프라퍼티"),
    REGACY_01("01", "(주)교원(부산지점)"),
    REGACY_02("02", "교원(인천지점)"),
    REGACY_03("03", "교원(부산지점)"),
    REGACY_04("04", "대전지점"),
    REGACY_10("10", "(주)교원(10)"),
    REGACY_11("11", "교원(인천지점)"),
    REGACY_12("12", "교원(부산지점)"),
    REGACY_13("13", "교원낙산스위트호텔"),
    REGACY_14("14", "교원더스위트호텔"),
    REGACY_15("15", "(주)교원문산물류"),
    REGACY_16("16", "(주)교원안성물류"),
    REGACY_17("17", "(주)교원.안성"),
    REGACY_19("19", "교원(합산)"),
    REGACY_20("20", "(주)교원"),
    REGACY_21("21", "(주)교원성수물류센터"),
    REGACY_22("22", "(주)교원부산사무소"),
    REGACY_23("23", "(주)교원인천사무소"),
    REGACY_24("24", "(주)교원성수물류센터"),
    REGACY_25("25", "(주)교원인천지점"),
    REGACY_26("26", "(주)교원대전지점"),
    REGACY_27("27", "(주)교원광주지점"),
    REGACY_28("28", "(주)교원대구지점"),
    REGACY_29("29", "(주)교원부산지점"),
    REGACY_2A("2A", "(주)교원대전사무소"),
    REGACY_2B("2B", "(주)교원경주스위트"),
    REGACY_2C("2C", "(주)교원안성물류센터"),
    REGACY_2D("2D", "(주)교원스위트호텔제주"),
    REGACY_2E("2E", "(주)교원스위트호텔낙산"),
    REGACY_2F("2F", "(주)교원파주물류센터"),
    REGACY_2G("2G", "(주)교원구로물류센터"),
    REGACY_2H("2H", "(주)교원부전사무소"),
    REGACY_2J("2J", "(주)교원안성사무소"),
    REGACY_2Z("2Z", "(주)교원(합산)"),
    REGACY_30("30", "(주)교원구몬"),
    REGACY_31("31", "공문교육안성물류센터"),
    REGACY_32("32", "공문교육도고연수원"),
    REGACY_33("33", "공문교육남원연수원"),
    REGACY_34("34", "공문교육가평연구소"),
    REGACY_35("35", "공문교육연구원(주)대구사무"),
    REGACY_36("36", "공문교육연구원(주)성수물류"),
    REGACY_40("40", "(주)교원교육"),
    REGACY_41("41", "교원교육물류센터"),
    REGACY_50("50", "(주)교원여행"),
    REGACY_51("51", "교원여행부산지점"),
    REGACY_60("60", "(주)교원L&C"),
    REGACY_61("61", "(주)교원L&C안성"),
    REGACY_62("62", "(주)교원L&C파주물류센터"),
    REGACY_63("63", "(주)교원L&C인천공장"),
    REGACY_70("70", "(주)케이더블유인터내셔널"),
    REGACY_80("80", "(주)교원하이퍼센트"),
    REGACY_90("90", "(주)교원라이프"),
    REGACY_99("99", "외부업체"),
    REGACY_A0("A0", "(주)승광"),
    REGACY_B0("B0", "(주)교원크리에이티브"),
    REGACY_C0("C0", "(주)교원인베스트"),
    REGACY_D0("D0", "(주)교원크리에이티브"),
    REGACY_E0("E0", "(주)교원더오름"),
    REGACY_F0("F0", "(주)교원구몬"),
    REGACY_G0("G0", "(주)교원위즈"),
    REGACY_J0("J0", "(주)교원에듀"),
    REGACY_K0("K0", "교원KRT"),
    REGACY_P0("P0", "(주)교원프라퍼티");

    final String code;
    final String codeName;

    static final String name = "회사코드";

    CtCoCd(String code, String codeName) {
        this.code = code;
        this.codeName = codeName;
    }

    public String getCode() {
        return code;
    }

    public String getCodeName() {
        return codeName;
    }

    public static CtCoCd of(String code) {
        return Arrays.stream(values())
            .filter((value) -> Objects.equals(value.getCode(), code))
            .findFirst()
            .orElseThrow(() -> new BizException(name + "을 확인해보세요."));
    }
}
