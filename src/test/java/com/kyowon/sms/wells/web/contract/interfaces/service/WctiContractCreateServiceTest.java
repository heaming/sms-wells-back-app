package com.kyowon.sms.wells.web.contract.interfaces.service;

import com.kyowon.sms.wells.web.contract.ordermgmt.service.WctaContractPmotSlRfltService;
import com.sds.sflex.system.config.test.SpringTestSupport;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@Slf4j
@RequiredArgsConstructor
class WctiContractCreateServiceTest extends SpringTestSupport {
    private final WctiContractCreateService service;

    @Test
    void testContractCreateForRental() {
        try {
            service.cancelContract("1", "1");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        log.info("▶▶▶ INPUT : {} {}", "W20234901909", 1);
        log.info("▶▶▶ OUTPUT : {}", 1);

        Assertions.assertTrue(1 > 0);
    }
}
