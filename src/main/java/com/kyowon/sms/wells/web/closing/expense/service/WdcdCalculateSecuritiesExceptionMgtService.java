package com.kyowon.sms.wells.web.closing.expense.service;

import com.kyowon.sms.wells.web.closing.expense.dto.WdcdCalculateSecuritiesExceptionMgtDto.*;
import com.kyowon.sms.wells.web.closing.expense.mapper.WdcdCalculateSecuritiesExceptionMgtMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WdcdCalculateSecuritiesExceptionMgtService {

    private final WdcdCalculateSecuritiesExceptionMgtMapper mapper;

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
