package com.kyowon.sms.wells.web.closing.payment.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.common.web.closing.payment.service.ZdcaEtcProfitSlipCreateService;
import com.sds.sflex.system.config.test.SpringTestSupport;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class ZdcaEtcProfitSlipCreateServiceTest extends SpringTestSupport {
    private final ZdcaEtcProfitSlipCreateService zdcaEtcProfitSlipCreateService;

    @Test
    @Transactional
    @DisplayName("잡이익 입금전표생성 테스트")
    void createSlip() {
        String result = zdcaEtcProfitSlipCreateService.createSlip("202312", "2000");

        Assertions.assertThat(result).isEqualTo("SUCCESS");
    }
}
