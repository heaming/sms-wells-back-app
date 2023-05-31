package com.kyowon.sms.wells.web.contract.ordermgmt.service;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaKMembersWellsOrdInqrDto;
import com.sds.sflex.system.config.test.SpringTestSupport;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
class WctaKMembersWellsOrdInqrServiceTest extends SpringTestSupport {
    private final WctaKMembersWellsOrdInqrService service;

    @Test
    void getKMembersWellsOrdInqrs() {
        String cntrNo = "W20226293492";
        String cntrSn = "1";
        String cmnSfkVal = "7333101128305";
        List<WctaKMembersWellsOrdInqrDto.SearchRes> lists = service.getKMembersWellsOrdInqrs(cntrNo, cntrSn, cmnSfkVal);
        log.info("▶▶▶ INPUT : {}, {}, {}", cntrNo, cntrSn, cmnSfkVal);
        log.info("▶▶▶ OUTPUT : {}", lists);
    }
}
