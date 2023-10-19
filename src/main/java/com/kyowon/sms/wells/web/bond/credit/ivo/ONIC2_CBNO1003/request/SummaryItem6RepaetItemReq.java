package com.kyowon.sms.wells.web.bond.credit.ivo.ONIC2_CBNO1003.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Request 렌탈CB 일반 요약항목 반복부
 */
@Setter
@Getter
public class SummaryItem6RepaetItemReq {
    @JsonProperty("siCd6")
    private String siCd6; /* 요약항목 코드 */
}
