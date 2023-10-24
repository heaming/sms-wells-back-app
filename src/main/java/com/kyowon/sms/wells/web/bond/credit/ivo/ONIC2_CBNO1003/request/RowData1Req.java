package com.kyowon.sms.wells.web.bond.credit.ivo.ONIC2_CBNO1003.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Request 렌탈CB식별정보
 */
@Setter
@Getter
@ToString
public class RowData1Req {
    @JsonProperty("rdSgmtId1")
    private String rdSgmtId1; /* segmentID(CBR01) */
    @JsonProperty("rdRecvCnt1")
    private String rdRecvCnt1; /* 렌탈CB 식별정보내역 수신건수 */
    @JsonProperty("rdReqCnt1")
    private String rdReqCnt1; /* 렌탈CB 식별정보내역 요청건수 */
}
