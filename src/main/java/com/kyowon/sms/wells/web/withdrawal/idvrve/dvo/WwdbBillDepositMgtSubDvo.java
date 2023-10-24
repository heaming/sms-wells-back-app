package com.kyowon.sms.wells.web.withdrawal.idvrve.dvo;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * 어음입금 등록 상세 DVO ( 팝업 )
 * </pre>
 *
 * @author heungjun.lee
 * @since 2023-10-24
 */
@Getter
@Setter
public class WwdbBillDepositMgtSubDvo {
    String itgDpNo; /* 통합입금번호 */
    String cntrNo; /* 계약번호 */
    String cntrSn; /* 계약일련번호 */
    String billDpAmt; /* 입금액 */
    String sellBzsBzrno; /* 판매업체사업자등록번호 */
    String pblBzsBzrno; /* 발행업체사업자등록번호 */
}
