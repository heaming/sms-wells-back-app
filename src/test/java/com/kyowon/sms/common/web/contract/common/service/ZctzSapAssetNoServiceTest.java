package com.kyowon.sms.common.web.contract.common.service;

import com.kyowon.sms.common.web.contract.common.dvo.ZctzRtasStatIzDvo;
import com.kyowon.sms.common.web.contract.common.service.ZctzSapAssetNoService;
import com.sds.sflex.system.config.test.SpringTestSupport;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@RequiredArgsConstructor
class ZctzSapAssetNoServiceTest extends SpringTestSupport {
    private final ZctzSapAssetNoService sapAssetNoService;

    final String existCntrNo = "W20178204733";

    @Test
    void getRtasStatIzs() {
        ZctzRtasStatIzDvo params = new ZctzRtasStatIzDvo();
        params.setCntrNo(existCntrNo);
        params.setCntrSn(1);

        List<ZctzRtasStatIzDvo> rtasStatIzs =  sapAssetNoService.getRtasStatIzs(params);

        assertFalse(rtasStatIzs.isEmpty());
    }

    @Test
    void getRtasStatIzByPk() {
        ZctzRtasStatIzDvo rtasStatIz =  sapAssetNoService.getRtasStatIzByPk(existCntrNo, 1, 1);
        assertNotNull(rtasStatIz);
    }

    @Test
    void getLastRtasStatIz() {
        ZctzRtasStatIzDvo rtasStatIz =  sapAssetNoService.getLastRtasStatIz(existCntrNo, 1);
        assertNotNull(rtasStatIz);
    }

    @Test
    void insertRtasStatIzByCntr() {
        ZctzRtasStatIzDvo exist = sapAssetNoService.getLastRtasStatIz(existCntrNo, 1);
        sapAssetNoService.insertRtasStatIzByCntr(existCntrNo, 1);
        ZctzRtasStatIzDvo added = sapAssetNoService.getLastRtasStatIz(existCntrNo, 1);
        log.debug("exist: {}", exist);
        log.debug("added: {}", added);
        assertEquals(exist.getChSn() + 1, (int) added.getChSn());
    }

    @Test
    void isRtasStatManageTargetCntrDtlForWells() {
        sapAssetNoService.isRtasStatManageTargetCntrDtlForWells(existCntrNo, 1);
    }
}
