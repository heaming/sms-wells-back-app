package com.kyowon.sms.wells.web.bond.transfer.service;

import com.sds.sflex.system.config.test.SpringTestSupport;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
class WbnaFosterDataTransferServiceTest extends SpringTestSupport {
    private final WbnaFosterDataTransferService service;

    @Test
    @DisplayName("추심사에 발송 할 전문자료 생성")
    void getFosterDatas() throws IOException {
        String baseYm = "202301";
        String bzHdqDvCd = "10";
        String bndClctnPrpDvCd = "02";
        String cntrNo = "E20221113065";
        String cntrSn = "1";
        String cstNo = "040751652";

        service.getFosterData(baseYm, bzHdqDvCd, bndClctnPrpDvCd, cntrNo, cntrSn, cstNo);
    }
}
