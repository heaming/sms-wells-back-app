package com.kyowon.sms.wells.web.closing.expense.service;

import com.kyowon.sms.wells.web.closing.expense.converter.WdcdMarketableSecuritieMgtConverter;
import com.kyowon.sms.wells.web.closing.expense.dto.WdcdMarketableSecuritieMgtDto.*;
import com.kyowon.sms.wells.web.closing.expense.dvo.WdcdMarketableSecuritieDvo;
import com.kyowon.sms.wells.web.closing.expense.mapper.WdcdMarketableSecuritieMgtMapper;
import com.sds.sflex.system.config.validation.BizAssert;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WdcdMarketableSecuritieMgtService {

    private final WdcdMarketableSecuritieMgtMapper mapper;
    private final WdcdMarketableSecuritieMgtConverter converter;

    public List<FindCodeRes> getBuilDingCd(FindCodeReq req) {
        return mapper.selectBuilDingCd(req);
    }

    public List<SearchSubjectRes> getSubject(SearchSubjectReq req) {
        return mapper.selectSubject(req);
    }

    public List<SearchFinalSettlementRes> getFinalWithholdingTaxSettlement(SearchFinalSettlementReq req) {
        return mapper.selectFinalWithholdingTaxSettlement(req);
    }

    @Transactional
    public int saveSettlementWithholdingTax(List<SaveReq> reqs) {

        int count = 0;
        String opcsAdjNo = null;

        String checkMonth = mapper.selectCheckWhetherMonthFinalized(reqs.get(0));

        if ("Y".equals(checkMonth)) {
            BizAssert.isTrue(false, "등록수정 불가합니다.");
        }

        List<AccCardInfoDetailRes> detailRess = mapper.selectAccCardInfoDetail(reqs.get(0));

        for (AccCardInfoDetailRes detailRes : detailRess) {

            WdcdMarketableSecuritieDvo dvo = converter.mapAccCardInfoDetailResToWdcdMarketableSecuritieExceptionDvo(detailRes);

            if (!"null".equals(String.valueOf(detailRes))) {
                count += mapper.updateAccMst(dvo);
                count += mapper.deleteAccDetail(dvo);
            }
        }

        for (SaveReq req : reqs) {

            WdcdMarketableSecuritieDvo dvo = converter.mapSaveReqToWdcdMarketableSecuritieExceptionDvo(req);
            if (StringUtils.isEmpty(opcsAdjNo)) {
                opcsAdjNo = mapper.selectOpcsAdjNo(dvo);
            }

            dvo.setOpcsAdjNo(opcsAdjNo);
            count += mapper.insertAccMst(dvo);
            count += mapper.insertAccDetail(dvo);
            count += mapper.updateOpcsCard(dvo);
            count += mapper.insertAccMap(dvo);

            int start = Integer.parseInt(opcsAdjNo.substring(0, 6));
            int end = Integer.parseInt(opcsAdjNo.substring(6, opcsAdjNo.length()));

            opcsAdjNo = String.valueOf(start) + String.format("%06d", end + 1);

        }

        return count;
    }
}
