package com.kyowon.sms.wells.web.withdrawal.idvrve.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WwdbDepositDetailSearchDvo {
    String sellTpCd; // 판매유형
    String dpDvCd;// 입금구분
    String startDt;
    String endDt;
    String cntrNo;
    String cntrSn;
    String stClctamPrtnrNo; // 집금담당자 사번 시작
    String enClctamPrtnrNo; // 집금담당자 사번 끝
    String stFstRgstUsrId; // 입력담당자 사번 시작
    String enFstRgstUsrId;

    String dpMesCd; // 입금수단
    String dpTpCd; // 입금유형
    String vncoDvCd; // van사 구분
    String ogTpCd; // 조직유형
    String[] ogTpCdList;
}
