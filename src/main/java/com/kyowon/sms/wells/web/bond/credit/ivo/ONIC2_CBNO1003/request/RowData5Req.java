package com.kyowon.sms.wells.web.bond.credit.ivo.ONIC2_CBNO1003.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Request 렌탈CB연체(일반)정보
 */
@Setter
@Getter
@ToString
public class RowData5Req {
    @JsonProperty("rdSgmtId5")
    private String rdSgmtId5;/* segmentID(CBR05) */
    @JsonProperty("rdRecvCnt5")
    private String rdRecvCnt5;/* 렌탈CB 연체(일반)정보내역 수신건수 */
    @JsonProperty("rdReqCnt5")
    private String rdReqCnt5;/* 렌탈CB 연체(일반)정보내역 요청건수 */
}
