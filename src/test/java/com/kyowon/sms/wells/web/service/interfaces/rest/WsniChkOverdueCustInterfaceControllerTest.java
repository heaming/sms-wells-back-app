package com.kyowon.sms.wells.web.service.interfaces.rest;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.TestPropertySource;

import com.kyowon.sms.wells.web.service.interfaces.dto.WsniChkOverdueCustDto;
import com.kyowon.sms.wells.web.service.interfaces.service.WsniChkOverdueCustService;
import com.sds.sflex.system.config.test.SpringTestSupport;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@TestPropertySource(properties = {"spring.profiles.active=local", "active.profiles=local"})
class WsniChkOverdueCustInterfaceControllerTest extends SpringTestSupport {
    private final WsniChkOverdueCustService service;

    @Test
    @DisplayName("Wells연체 고객 체크")
    void getCreativeConvAppRenewalInformation() throws Exception {
        WsniChkOverdueCustDto.FindReq req = WsniChkOverdueCustDto.FindReq.builder()
            .cntrCstNo("034246851")
            .dlqMcn(5)
            .build();

        WsniChkOverdueCustDto.FindRes result = service.selectChkOverdueCust(req);

        log.info("INPUT : {}", req);
        log.info("OUTPUT : {}", result);

        Assertions.assertThat(result.rsltCd()).isNotEmpty();
    }
}
