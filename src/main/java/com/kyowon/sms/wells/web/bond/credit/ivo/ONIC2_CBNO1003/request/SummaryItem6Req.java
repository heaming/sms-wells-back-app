package com.kyowon.sms.wells.web.bond.credit.ivo.ONIC2_CBNO1003.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Request 렌탈CB 일반 요약항목
 */
@Setter
@Getter
@ToString
public class SummaryItem6Req {
    @JsonProperty("siSgmtId6")
    private String siSgmtId6; /* segmentID(CBR06) */
    @JsonProperty("siRecvCnt6")
    private String siRecvCnt6; /* 렌탈CB 일반 요약항목 수신건수 */
    @JsonProperty("siReqCnt6")
    private String siReqCnt6; /* 렌탈CB 일반 요약항목 요청건수(반복건수) */
}
