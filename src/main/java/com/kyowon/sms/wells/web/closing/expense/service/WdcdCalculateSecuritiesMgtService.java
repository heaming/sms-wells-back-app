package com.kyowon.sms.wells.web.closing.expense.service;

import com.kyowon.sms.wells.web.closing.expense.dto.WdcdCalculateSecuritiesMgtDto.*;
import com.kyowon.sms.wells.web.closing.expense.mapper.WdcdCalculateSecuritiesMgtMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WdcdCalculateSecuritiesMgtService {

    private final WdcdCalculateSecuritiesMgtMapper mapper;

    public List<CodeRes> getBuilDingCd() {
        return mapper.selectBuilDingCd();
    }

    public List<SubjectRes> getSubject(SubjectReq req) {
        return mapper.selectSubject(req);
    }

    public List<finalWithholdingTaxSettlementRes> getFinalWithholdingTaxSettlement(finalWithholdingTaxSettlementReq req) {
        return mapper.selectFinalWithholdingTaxSettlement(req);
    }
}
