package com.kyowon.sms.wells.web.closing.performance.service;

import com.sds.sflex.system.config.test.SpringTestSupport;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@RequiredArgsConstructor
class ZdccMonthlySalesClosingAggregationServiceTest extends SpringTestSupport {
    private final WdccMonthlySalesClosingAggregationService service;

    @Test
    @DisplayName("WELLS 매출 월마감 내역 집계 테스트")
    void createSalesMonthlyClosingDetailsTally() {
        int result = service.createSalesMonthlyClosingDetailsTallyForWells("20231025", "W20233701936", "1");
        Assertions.assertThat(result).isEqualTo(1);
    }
}
