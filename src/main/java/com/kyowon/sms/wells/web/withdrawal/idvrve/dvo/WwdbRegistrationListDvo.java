package com.kyowon.sms.wells.web.withdrawal.idvrve.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WwdbRegistrationListDvo {
    String itgDpNo; /* 통합입금번호 */
    String fnitCd; /* 금융기관코드 */
    String billBndNo; /* 어음채권번호 */
    String billBndAmt; /* 어음채권금액 */
    String billRcpDt; /* 어음접수일자 */
    String billExprDt; /* 어음만기일자 */
    String billLnPsbDt; /* 어음대출가능일자 */
    String billBndHndovDt; /* 어음채권양도일자 */
    String billLnPsbAmt; /* 어음대출가능금액 */
    String billDlpnrNm; /* 어음거래처명 */
    String sellBzsBzrno; /* 판매업체사업자등록번호 */
    String pblBzsBzrno; /* 발행업체사업자등록번호 */
    String billRmkCn; /* 어음비고내용 */
    String canYn; /* 취소여부 */
    String canDtm; /* 취소일시 */
    String rveAkNo; /* 수납요청번호 */
    String dtaDlYn; /* 삭제여부 */
    String billDpAmt; /* 입금금액 */
}
