package com.kyowon.sms.wells.web.closing.payment.service;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.common.web.closing.payment.dvo.ZdcaBusinessAnticipationAmtWellsDvo;
import com.kyowon.sms.common.web.closing.payment.service.ZdcaBusinessAnticipationAmtWellsService;
import com.sds.sflex.system.config.test.SpringTestSupport;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class ZdcaBusinessAnticipationAmtWellsServiceTest extends SpringTestSupport {
    private final ZdcaBusinessAnticipationAmtWellsService zdcaBusinessAnticipationAmtWellsService;

    @Test
    @Transactional
    @DisplayName("영업선수금생성 테스트")
    void createBusinessAnticipationAmt() {
        List<ZdcaBusinessAnticipationAmtWellsDvo> dvos = new ArrayList<>();
        ZdcaBusinessAnticipationAmtWellsDvo dvo = new ZdcaBusinessAnticipationAmtWellsDvo();
        dvo.setInputGubun("1");
        dvo.setRveNo("1");
        dvo.setRveSn(0);
        dvo.setDpClDt("1");
        dvo.setCntrNo("1");
        dvo.setCntrSn(0);
        dvo.setKwGrpCoCd("1");
        dvo.setRveCd("1");
        dvo.setOgTpCd("1");
        dvo.setIchrPrtnrNo("1");
        dvo.setRveAmt(0);
        dvo.setCstNo("1");
        dvo.setBnkCd("1");
        dvo.setDpCprcnfNo("1");
        dvo.setPdCd("1");
        dvo.setPdHclsfId("1");
        dvo.setPdMclsfId("1");
        dvo.setPdLclsfId("1");
        dvo.setRveDt("1");
        dvo.setPerfDt("1");
        dvo.setBilTn(0);
        dvo.setProcsDvCd("1");
        dvo.setDpMesCd("1");
        dvo.setDpTpCd("1");
        dvo.setRveDvCd("1");
        dvo.setRvePhCd("1");
        dvo.setRveplcDvCd("1");
        dvo.setCdcoCd("1");
        dvo.setDpDvCd("1");
        dvo.setSellTpCd("1");
        dvo.setSellTpDtlCd("1");
        dvo.setCardAprno("1");
        dvo.setCrcdnoEncr("1");
        dvo.setDprNm("1");
        dvo.setAcnoEncr("1");
        dvo.setCrpAcno("1");
        dvo.setVncoDvCd("1");
        dvo.setEtcAtamNo("1");
        dvo.setBznsAtamBlam(0);
        dvo.setSapDpTpCd("1");
        dvo.setSapPdDvCd("1");
        dvo.setSapPdAtcCd("1");
        dvo.setDgCstId("1");
        dvo.setCntrDtlStatCd("1");
        dvos.add(dvo);
        int resultCount = zdcaBusinessAnticipationAmtWellsService.createBusinessAnticipationAmt(dvos);

        Assertions.assertThat(resultCount).isEqualTo(0);
    }
}
