package com.kyowon.sms.wells.web.closing.performance.service;

import com.sds.sflex.system.config.test.SpringTestSupport;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@RequiredArgsConstructor
class WdccMonthlySalesClosingAggregationServiceTest extends SpringTestSupport {
    private final WdccMonthlySalesClosingAggregationService service;

    @Test
    @DisplayName("매출 월마감 내역 집계 테스트")
    void createSalesMonthlyClosingDetailsTally() {
        int result = service.createSalesMonthlyClosingDetailsTally("20231009", "W20093924043", "1");
        Assertions.assertThat(result).isEqualTo(1);
    }
}
