package com.kyowon.sms.wells.web.closing.payment.service;

import org.junit.jupiter.api.Assertions;
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
    @DisplayName("잡이익 입금전표생성 테스트(Wells)")
    void createSlip() {
        String beforeMonth = "202312";
        String kwGrpCoCd = "2000";
        int atamDv = 2;

        String result = zdcaEtcProfitSlipCreateService.createSlip(beforeMonth, kwGrpCoCd, atamDv);

        Assertions.assertEquals("SUCCESS", result);
    }
}
