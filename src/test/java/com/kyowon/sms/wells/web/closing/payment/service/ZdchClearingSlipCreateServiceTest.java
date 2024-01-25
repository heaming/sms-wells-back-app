package com.kyowon.sms.wells.web.closing.payment.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.common.web.closing.sales.dto.ZdchClearingSlipCreateDto;
import com.kyowon.sms.common.web.closing.sales.service.ZdchClearingSlipCreateService;
import com.sds.sflex.system.config.test.SpringTestSupport;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class ZdchClearingSlipCreateServiceTest extends SpringTestSupport {
    private final ZdchClearingSlipCreateService zdchClearingSlipCreateService;

    @Test
    @Transactional
    @DisplayName("반제전표생성 테스트")
    void clearingSlipCreate() throws Exception {
        ZdchClearingSlipCreateDto.SaveReq saveReq = ZdchClearingSlipCreateDto.SaveReq.builder()
            .baseYm("202310")
            .sapBzDvCd("1110")
            .dpKndCd("1")
            .sellTpCd("1")
            .build();
        String result = zdchClearingSlipCreateService.clearingSlipCreate(saveReq);

        Assertions.assertThat(result).isEqualTo("S");
    }
}
