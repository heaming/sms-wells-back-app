package com.kyowon.sms.wells.web.service.common.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WsnyBeforeServiceBizClDvo {
    String mngtYm; /* 관리년월 */
    String strtdt; /* 시작일자 */
    String strtHh; /* 시작시간 */
    String enddt; /* 종료일자 */
    String endHh; /* 종료시간 */
    String mngtItm; /* 관리항목 */
}
