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

    private String rspCd;           /* 응답코드 */
    private String rspMsg;          /* 응답메시지 */
}
