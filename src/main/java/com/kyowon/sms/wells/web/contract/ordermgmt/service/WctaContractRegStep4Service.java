package com.kyowon.sms.wells.web.contract.ordermgmt.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import com.kyowon.sms.wells.web.contract.common.dvo.WctzCntrBasicChangeHistDvo;
import com.kyowon.sms.wells.web.contract.common.service.WctzHistoryService;
import com.kyowon.sms.wells.web.contract.ordermgmt.converter.WctaContractRegConverter;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.*;
import com.kyowon.sms.wells.web.contract.ordermgmt.mapper.WctaContractRegStep4Mapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctaContractRegStep4Service {
    private final WctaContractRegStep4Mapper mapper;

    private final WctaContractRegConverter converter;
    private final WctaContractRegService regService;
    private final WctzHistoryService historyService;

    public WctaContractRegDvo selectStepInfo(String cntrNo) {
        WctaContractRegStep4Dvo step4Dvo = new WctaContractRegStep4Dvo();

        WctaContractBasDvo bas = regService.selectContractBas(cntrNo);
        step4Dvo.setBas(bas);

        if (StringUtils.isEmpty(bas.getCstStlmInMthCd())) {
            // 신규인 경우
            bas.setCstStlmInMthCd("10");
        }

        List<WctaContractCstRelDvo> cstRels = regService.selectContractCstRel(cntrNo);
        WctaContractCstRelDvo c = cstRels.stream().filter((v) -> "10".equals(v.getCntrCstRelTpCd())).findFirst().get();
        WctaContractCstRelDvo cntrtInfo = regService.selectCntrtInfoByCstNo(c.getCstNo());
        step4Dvo.setCntrt(converter.mergeContractCstRelDvo(c, cntrtInfo));

        WctaContractCstRelDvo l = cstRels.stream().filter((v) -> "20".equals(v.getCntrCstRelTpCd())).findFirst().get();
        WctaContractCstRelDvo lrnrInfo = regService.selectCntrtInfoByCstNo(l.getCstNo());
        step4Dvo.setLrnr(converter.mergeContractCstRelDvo(l, lrnrInfo));

        step4Dvo.setAdrpc(regService.selectContractAdrpcBas(cntrNo));
        List<WctaContractPrtnrRelDvo> prtnrRels = regService.selectContractPrtnrRel(cntrNo);
        if (prtnrRels.size() > 1) {
            // 파트너관계가 2건 이상인 경우, 계약파트너유형코드 010인 건
            step4Dvo.setPrtnr(prtnrRels.stream().filter((p) -> "010".equals(p.getCntrPrtnrTpCd())).findFirst().get());
        } else {
            step4Dvo.setPrtnr(prtnrRels.get(0));
        }

        step4Dvo.setStlmDtls(mapper.selectStlmDtls(cntrNo));
        return WctaContractRegDvo.builder()
            .step4(step4Dvo)
            .build();
    }

    @Transactional
    public String saveContractStep4(WctaContractRegStep4Dvo dvo) {
        String cntrNo = dvo.getBas().getCntrNo();
        // 0. 계약기본
        regService.updateCntrPrgsStatCd(cntrNo, "20");
        mapper.updateCntrBasStep4(cntrNo, dvo.getBas().getCstStlmInMthCd());

        historyService.createContractBasicChangeHistory(
            WctzCntrBasicChangeHistDvo.builder()
                .cntrNo(cntrNo)
                .build()
        );
        return cntrNo;
    }
}
