package com.kyowon.sms.wells.web.contract.ordermgmt.service;

import com.sds.sflex.system.config.test.SpringTestSupport;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@Slf4j
@RequiredArgsConstructor
class WctaContractPmotSlRfltServiceTest extends SpringTestSupport {
    private final WctaContractPmotSlRfltService service;

    @Test
    void testContractPmotSlRflt() {
        int processCount = service.requestContractPmotSlRflt("W20234901909", 1);


        log.info("▶▶▶ INPUT : {} {}", "W20234901909", 1);
        log.info("▶▶▶ OUTPUT : {}", processCount);

        Assertions.assertTrue(processCount > 0);
    }
}
