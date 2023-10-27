package com.kyowon.sms.wells.web.withdrawal.idvrve.dvo;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * 어음신규 등록 전자어음 입금대상 엑셀 업로드 DVO
 * </pre>
 *
 * @author dagyeong.hong
 * @since 2023-10-26
 */

@Getter
@Setter
public class WwdbBillDepositExcelUploadDvo {

    String cntr;      /* 계약상세번호 */
    String cntrNo;    /* 계약번호 */
    String cntrSn;    /* 계약일련번호 */
    String sellAmt;   /* 금액(원) */
    String errorCode; /* 1.정상 2.계약번호 없음 3.금액없음 4. */
}
