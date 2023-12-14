package com.kyowon.sms.wells.web.service.common.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WsnyCalendarDvo {
    String svCnrOgId; /* 서비스센터조직ID */
    String baseY; /* 기준년 */
    String baseMm; /* 기준월 */
    String baseD; /* 기준일 */
    String dfYn; /* 휴무여부 */
    String rmkCn; /* 비고 */
//    String bndtWrkPsicNo;
    String bndtWrkPsicNoPrtnrNo; /* 당직자파트너번호 */
    String bndtWrkPsicNoOgTpCd; /* 당직자조직유형코드 */
}
