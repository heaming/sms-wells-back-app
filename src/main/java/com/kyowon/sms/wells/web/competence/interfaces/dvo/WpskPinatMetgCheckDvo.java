package com.kyowon.sms.wells.web.competence.interfaces.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WpskPinatMetgCheckDvo {
    private String prtnrNos;        /* 파트너 번호 */
    private String prtnrNo;         /* 파트너 번호 */
    private String metgPrscDt;      /* 미팅참석년도 */
    private String ogTpCd;          /* 조직유형코드 */

    private String chek;           /* 체크 결과 */
    private String ercd;            /* 결과메시지코드 */
    private String emsg;            /* 결과메시지 */
}
