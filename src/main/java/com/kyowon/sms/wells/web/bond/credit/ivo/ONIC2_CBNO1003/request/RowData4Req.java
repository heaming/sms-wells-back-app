package com.kyowon.sms.wells.web.bond.credit.ivo.ONIC2_CBNO1003.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Request 렌탈CB연체(렌터카)정보
 */
@Setter
@Getter
@ToString
public class RowData4Req {
    @JsonProperty("rdSgmtId4")
    private String rdSgmtId4;/* segmentID(CBR04) */
    @JsonProperty("rdRecvCnt4")
    private String rdRecvCnt4;/* 렌탈CB 연체(렌터카)정보내역 수신건수 */
    @JsonProperty("rdReqCnt4")
    private String rdReqCnt4;/* 렌탈CB 연체(렌터카)정보내역 요청건수 */
}
