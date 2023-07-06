package com.kyowon.sms.wells.web.closing.expense.service;

import com.kyowon.sms.wells.web.closing.expense.converter.WdcdMarketableSecuritieExceptionMgtConverter;
import com.kyowon.sms.wells.web.closing.expense.dto.WdcdMarketableSecuritieExceptionMgttDto.*;
import com.kyowon.sms.wells.web.closing.expense.dvo.WdcdMarketableSecuritieExceptionDvo;
import com.kyowon.sms.wells.web.closing.expense.mapper.WdcdMarketableSecuritieExceptionMgtMapper;
import com.sds.sflex.system.config.validation.BizAssert;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WdcdMarketableSecuritieExceptionMgtService {

    private final WdcdMarketableSecuritieExceptionMgtMapper mapper;
    private final WdcdMarketableSecuritieExceptionMgtConverter converter;

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

            WdcdMarketableSecuritieExceptionDvo dvo = converter.mapAccCardInfoDetailResToWdcdMarketableSecuritieExceptionDvo(detailRes);

            if (!"null".equals(String.valueOf(detailRes))) {
                count += mapper.updateAccMst(dvo);
                count += mapper.deleteAccDetail(dvo);
            }
        }

        for (SaveReq req : reqs) {

            WdcdMarketableSecuritieExceptionDvo dvo = converter.mapSaveReqToWdcdMarketableSecuritieExceptionDvo(req);
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
