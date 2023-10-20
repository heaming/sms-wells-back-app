package com.kyowon.sms.wells.web.bond.credit.ivo.ONIC2_CBNO1003.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

/**
 * Request 렌탈CB 기타 요약항목
 */
@Setter
@Getter
public class SummaryItem7Req {
    @JsonProperty("siSgmtId7")
    private String siSgmtId7; /* segmentID(CBR07) */
    @JsonProperty("siRecvCnt7")
    private String siRecvCnt7; /* 렌탈CB 기타 요약항목 수신건수 */
    @JsonProperty("siReqCnt7")
    private String siReqCnt7; /* 렌탈CB 기타 요약항목 요청건수(반복건수) */
}
