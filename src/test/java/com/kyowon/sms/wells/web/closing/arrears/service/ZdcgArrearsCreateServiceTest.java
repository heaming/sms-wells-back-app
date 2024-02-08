package com.kyowon.sms.wells.web.closing.arrears.service;

import com.kyowon.sms.common.web.closing.arrears.dvo.ZdcgArrearsCreateDvo;
import com.sds.sflex.common.utils.DateUtil;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.kyowon.sms.common.web.closing.arrears.service.ZdcgArrearsCreateService;
import com.sds.sflex.system.config.test.SpringTestSupport;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
class ZdcgArrearsCreateServiceTest extends SpringTestSupport {
    private final ZdcgArrearsCreateService service;

    @Test
    @Transactional
    @DisplayName("WELLS 연체생성 서비스 테스트")
    void createArrearsByContract() {
        String arrearsTypeCode = "W001"; // 렌탈

        ZdcgArrearsCreateDvo dvo = new ZdcgArrearsCreateDvo();
        dvo.setPerfYm(DateUtil.getNowString().substring(0, 6)); // 현재년월
        dvo.setCntrNo("W20240117474"); // 계약번호 - W20230004584, W20240117474
        dvo.setCntrSn("1"); // 계약상세일련번호

        String result = service.createArrearsByContract(dvo, arrearsTypeCode);
        Assertions.assertThat(result).isEqualTo("Y");
    }
}
