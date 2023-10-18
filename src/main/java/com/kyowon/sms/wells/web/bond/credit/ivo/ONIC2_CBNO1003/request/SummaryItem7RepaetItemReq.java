package com.kyowon.sms.wells.web.bond.credit.ivo.ONIC2_CBNO1003.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

/**
 * Request 렌탈CB 기타 요약항목 반복부
 */
@Setter
@Getter
public class SummaryItem7RepaetItemReq {
    @JsonProperty("siCd7")
    private String siCd7; /* 요약항목 코드 */
}
