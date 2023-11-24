package com.kyowon.sms.wells.web.withdrawal.idvrve.dvo;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * 입금전표 생성 - 수납요청상세 DVO
 * </pre>
 *
 * @author heungjun.lee
 * @since 2023-08-14
 */
@Getter
@Setter
public class WwdbBillDepositContractDvo {

    String rveAkNo; // 수납요청번호
    String rveBizDvCd; // 수납업무구분코드
    String itgDpNo; // 통합입금번호
    String cntrNo; // 계약번호
    String cntrSn; // 계약상세번호
}
