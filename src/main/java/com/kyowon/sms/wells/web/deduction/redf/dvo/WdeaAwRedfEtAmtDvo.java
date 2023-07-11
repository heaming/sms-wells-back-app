package com.kyowon.sms.wells.web.deduction.redf.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WdeaAwRedfEtAmtDvo {
    /*되물림예상계약기본*/
    String baseYm; /* 기준년월 */
    String perfYm; /* 실적년월 */
    String ogTpCd; /* 조직유형코드 */
    String prtnrNo; /* 파트너번호 */
    String cntrNo; /* 계약번호 */
    String cntrSn; /* 계약일련번호 */
    String cntrPerfCrtDvCd; /* 계약실적생성구분코드 */
    String redfAdsbTpCd; /* 되물림재지급유형코드 */
    String perfDvCd; /* 실적구분코드 */
    String ackmtPerfAmt; /* 인정실적금액 */
    String slAmt; /* 매출금액 */
    String etCanAmt; /* 예상취소금액 */
    String dtaDlYn; /* 데이터삭제여부 */

    /* 되물림예상금액내역 */
    String feeCd; /* 수수료코드 */
    String redfOcAmt; /* 되물림발생금액 */

    String pagingChk; /*페이징 체킹*/
}
