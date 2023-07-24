package com.kyowon.sms.wells.web.fee.aggregate.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WfeaManagerFeeMeetingAttendanceDvo {
    String ogTpCd; /* 조직유형 */
    String perfYm; /* 실적년월 */
    String rsbTpCd; /* 직책유형코드 */
    String feeTcntDvCd; /* 수수료차수구분코드 */
    String perfAgrgCrtDvCd; /* 실적집계생성구분코드 */
    String perfAtcCd; /* 실적항목코드 */
    String coCd; /* 회사코드 */
}
