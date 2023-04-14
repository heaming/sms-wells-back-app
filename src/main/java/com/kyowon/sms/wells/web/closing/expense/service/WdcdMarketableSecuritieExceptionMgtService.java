package com.kyowon.sms.wells.web.closing.expense.service;

import com.kyowon.sms.wells.web.closing.expense.dto.WdcdMarketableSecuritieExceptionMgttDto.*;
import com.kyowon.sms.wells.web.closing.expense.mapper.WdcdMarketableSecuritieExceptionMgtMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WdcdMarketableSecuritieExceptionMgtService {

    private final WdcdMarketableSecuritieExceptionMgtMapper mapper;

    public List<FindCodeRes> getBuilDingCd() {
        return mapper.selectBuilDingCd();
    }

    public List<SearchSubjectRes> getSubject(SearchSubjectReq req) {
        return mapper.selectSubject(req);
    }

    public List<SearchFinalSettlementRes> getFinalWithholdingTaxSettlement(SearchFinalSettlementReq req) {
        return mapper.selectFinalWithholdingTaxSettlement(req);
    }
}
