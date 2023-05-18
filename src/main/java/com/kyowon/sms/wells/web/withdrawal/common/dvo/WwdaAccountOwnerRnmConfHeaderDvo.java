package com.kyowon.sms.wells.web.withdrawal.common.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WwdaAccountOwnerRnmConfHeaderDvo {
    private String whlLngt; // (LENGTH) = "0200"
    private String inttCd; // (기관코드) = 입력 시스템구분코드 = 'W' ? "41000065" : "41000365"
    private String dealKindCd; // (거래종류코드) = "2001"
    private String snrcCd; // (송수신코드) = "3"
    private String dealDt; // (거래일자) = 현재일자(YYYYMMDD)
    private String dealHr; // (거래시간) = 현재시간(HH24MISS)
    private String openBankCd; // (개설은행코드)  = substring(TB_RVDW_FNIT_CD.금융기관대표코드, 2, 2) // 뒤 2자리
    private String dealNo; // (거래번호) = ""
    private String rplyCd; // (응답코드) = ""
    private String nwOpenBankCd; // (신개설은행코드) = ""
    private String resrScp1; // (예약 영역) = ""
    private String resrScp2; // (예약 영역) = ""
    private String userDealNo; // (사용자거래번호) = TB_RVCL_AC_FNT_RTM_AK_APR_IZ.AK_SN(요청일련번호)
    private String occrdiv; // (발생구분) = "O"
    private String spczTrnmWayDiv; // (전문전송방식 구분) = "Y"
    private String resrScp3; // (예약 영역) = ""
    private String atmtCancYn; // (자동취소여부) = ""
}
