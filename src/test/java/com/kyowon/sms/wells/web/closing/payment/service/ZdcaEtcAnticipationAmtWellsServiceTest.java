package com.kyowon.sms.wells.web.closing.payment.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.common.web.closing.payment.dvo.ZdcaEtcAnticipationAmtWellsDvo;
import com.kyowon.sms.common.web.closing.payment.service.ZdcaEtcAnticipationAmtWellsService;
import com.sds.sflex.system.config.test.SpringTestSupport;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class ZdcaEtcAnticipationAmtWellsServiceTest extends SpringTestSupport {
    private final ZdcaEtcAnticipationAmtWellsService zdcaEtcAnticipationAmtWellsService;

    @Test
    @Transactional
    @DisplayName("기타선수금 생성 테스트")
    void createBusinessAnticipationAmt() {
        List<ZdcaEtcAnticipationAmtWellsDvo> dvos = new ArrayList<>();
        ZdcaEtcAnticipationAmtWellsDvo dvo = new ZdcaEtcAnticipationAmtWellsDvo();
        dvo.setEtcAtamNo("0");
        dvo.setEtcAtamOcDt("0");
        dvo.setItgDpNo("0");
        dvo.setEtcAtamTpCd("0");
        dvo.setEtcAtamProcsCd("0");
        dvo.setEtcAtamPrcsdt("0");
        dvo.setEtcAtamProcsAmt(BigDecimal.ONE);
        dvo.setRveNo("0");
        dvo.setRveSn(0);
        dvo.setRfndRcpNo("0");
        dvo.setRveDt("0");
        dvo.setBilTn(0);
        dvo.setProcsDvCd("0");
        dvo.setDpDvCd("0");
        dvo.setSellTpCd("0");
        dvo.setSellTpDtlCd("0");
        dvo.setPdCd("0");
        dvo.setPdHclsfId("0");
        dvo.setPdMclsfId("0");
        dvo.setPdLclsfId("0");
        dvo.setDpMesCd("0");
        dvo.setDpTpCd("0");
        dvo.setCdcoCd("0");
        dvo.setCardAprno("0");
        dvo.setCrcdnoEncr("0");
        dvo.setBnkCd("0");
        dvo.setDprNm("0");
        dvo.setAcnoEncr("0");
        dvo.setVncoDvCd("0");
        dvo.setCntrNo("0");
        dvo.setCntrSn(0);
        dvo.setKwGrpCoCd("0");
        dvo.setRveCd("0");
        dvo.setRveDvCd("0");
        dvo.setRvePhCd("0");
        dvo.setRveplcDvCd("0");
        dvo.setOgTpCd("0");
        dvo.setIchrPrtnrNo("0");
        dvo.setCstNo("0");
        dvo.setEtcAtamSn(0);
        dvo.setSapDpTpCd("0");
        dvo.setSapPdDvCd("0");
        dvo.setSapPdAtcCd("0");
        dvo.setDgCstId("0");
        dvo.setCrpAcno("0");
        dvo.setDpSlipTrsNo("0");
        dvo.setSapSlpno("0");
        dvo.setSapSlipMsg("0");
        dvo.setRveCoCd("0");
        dvos.add(dvo);
        int resultCount = zdcaEtcAnticipationAmtWellsService.createEtcAnticipationAmt(dvos);

        Assertions.assertThat(resultCount).isGreaterThan(0);
    }
}
