package com.kyowon.sms.wells.web.service.interfaces.rest;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.TestPropertySource;

import com.kyowon.sms.wells.web.service.interfaces.dto.WsniMyKPaymentInfoDto;
import com.kyowon.sms.wells.web.service.interfaces.service.WsniMyKPaymentInfoService;
import com.kyowon.sms.wells.web.withdrawal.zcommon.constants.WdWithdrawalConst;
import com.sds.sflex.system.config.test.SpringTestSupport;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@TestPropertySource(properties = {"spring.profiles.active=local", "active.profiles=local"})
class WsniMyKPaymentInfoInterfaceControllerTest extends SpringTestSupport {
    private static final String BASE_URL = WdWithdrawalConst.INTERFACE_URL_V1 + "/idvrve/edicard";
    private final WsniMyKPaymentInfoService service;

    @Test
    @DisplayName("(마이K)웰스 납부 정보 조회")
    void getCreativeConvAppRenewalInformation() throws Exception {

        WsniMyKPaymentInfoDto.FindReq req = WsniMyKPaymentInfoDto.FindReq.builder()
            .cntrNo("W20224799317")
            .cntrSn(1)
            .build();

        WsniMyKPaymentInfoDto.FindRes result = service.selectMyKPaymentInfo(req);

        log.info("INPUT : {}", req);
        log.info("OUTPUT : {}", result);

        Assertions.assertThat(result.rsltCd()).isEqualTo("Y");
    }
}
