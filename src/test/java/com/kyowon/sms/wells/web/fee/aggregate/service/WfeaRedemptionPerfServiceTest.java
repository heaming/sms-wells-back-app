package com.kyowon.sms.wells.web.fee.aggregate.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.Commit;

import com.sds.sflex.system.config.test.SpringTestSupport;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class WfeaRedemptionPerfServiceTest extends SpringTestSupport {

    private final WfeaRedemptionPerfService service;

    @Test
    @DisplayName("P조직 취소 되물림 실적 생성")
    @Commit
    void aggregateRedemptionOfFeePerformanceForPlanner() {
        // given
        String baseYm = "202307";
        String ogTpCd = "W01";
        String perfAgrgCrtDvCd = "102";
        String cntrPerfCrtDvCd = "02";

        // when & then
        service.aggregateRedemptionOfFeePerformance(baseYm, ogTpCd, perfAgrgCrtDvCd, cntrPerfCrtDvCd);
    }

    @Test
    @DisplayName("M조직 취소 되물림 실적 생성")
    @Commit
    void aggregateRedemptionOfFeePerformanceForManager() {
        // given
        String baseYm = "202307";
        String ogTpCd = "W02";
        String perfAgrgCrtDvCd = "202";
        String cntrPerfCrtDvCd = "03";

        // when & then
        service.aggregateRedemptionOfFeePerformance(baseYm, ogTpCd, perfAgrgCrtDvCd, cntrPerfCrtDvCd);
    }

    @Test
    @DisplayName("홈마스터 취소 되물림 실적 생성")
    @Commit
    void aggregateRedemptionOfFeePerformanceForHomeMaster() {
        // given
        String baseYm = "202307";
        String ogTpCd = "W03";
        String perfAgrgCrtDvCd = "302";
        String cntrPerfCrtDvCd = "04";

        // when & then
        service.aggregateRedemptionOfFeePerformance(baseYm, ogTpCd, perfAgrgCrtDvCd, cntrPerfCrtDvCd);
    }
}
