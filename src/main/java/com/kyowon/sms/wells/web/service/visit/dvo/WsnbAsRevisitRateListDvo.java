package com.kyowon.sms.wells.web.service.visit.dvo;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * <pre>
 * K-W-SV-U-0100M01 A/S 재방문율 조회
 * </pre>
 *
 * @author 37758 이재훈
 * @since 2023.07.28
 */
@Setter
@Getter
public class WsnbAsRevisitRateListDvo {
    public WsnbAsRevisitRateListDvo() {}

    public WsnbAsRevisitRateListDvo(String baseDateFrom, String baseDateTo, String ogId) {
        this.baseDateFrom = baseDateFrom;
        this.baseDateTo = baseDateTo;
        this.ogId = ogId;
    }

    String baseDateFrom;
    String baseDateTo;
    String ogId;
    String ogNm;
    String prtnrNo;
    String prtnrNm;
    String dupCnt;
    String asCnt;
    String dupPer;
    String score;
}
