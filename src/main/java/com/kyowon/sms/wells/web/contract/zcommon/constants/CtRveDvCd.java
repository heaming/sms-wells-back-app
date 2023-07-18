package com.kyowon.sms.wells.web.contract.zcommon.constants;

import com.sds.sflex.system.config.exception.BizException;

import java.util.Arrays;
import java.util.Objects;

public enum CtRveDvCd {
    CNTRAM("01", "계약금"),
    DLQFE("02", "연체가산금"),
    MM_PY("03", "월납입액"),
    MSH_SSPCS("04", "멤버십회비"),
    RGLR_SPP_SSPCS("05", "정기배송회비"),
    BSCH("06", "매변"),
    CCAM("07", "위약금"),
    RFDAMT("08", "환불금"),
    DFA("09", "대손"),
    RNTF("10", "손료"),
    PTYPF("11", "잡이익"),
    FGPT_RPLC("12", "사은품대체"),
    FEE_DDTN("13", "수수료공제"),
    SV_DP("14", "서비스입금"),
    PNPYAM("15", "가지급금"),
    LWSC("16", "소송비"),
    ATAM("97", "영업선수"),
    ETC_ATAM("98", "기타선수");

    final String code;
    final String codeName;

    static final String name = "수납상태코드";

    CtRveDvCd(String code, String codeName) {
        this.code = code;
        this.codeName = codeName;
    }

    public String getCode() {
        return code;
    }

    public String getCodeName() {
        return codeName;
    }

    public static CtRveDvCd of(String code) {
        return Arrays.stream(values())
            .filter((value) -> Objects.equals(value.getCode(), code))
            .findFirst()
            .orElseThrow(() -> new BizException(name + "을 확인해보세요."));
    }
}


/*
*
* 	1	01	계약금						- 01:청약(계약)금
	2	02	연체가산금						- 02:연체가산금
	3	03	월납입액						- 03:월납입액(할부금, 렌탈료)
	4	04	멤버십회비						- 04:멤버십회비
	5	05	정기배송회비						- 05:정기배송회비
	6	06	매변						- 06:매변
	7	07	위약금						- 07:위약금
	8	08	환불금						- 08:환불금
	9	09	대손						- 09:대손
	10	10	손료						- 10:손료
	11	11	잡이익						- 11:잡이익
	12	12	사은품대체						- 12:사은품대체
	13	13	수수료공제						- 13:수수료공제
	14	14	서비스입금						- 14:서비스 입금
	15	15	가지급금						- 15:가지급금
	16	16	소송비						- 16:소송비
	97	97	영업선수						- 97 : 영업선수
	98	98	기타선수						- 98 : 기타선수

*
* */
