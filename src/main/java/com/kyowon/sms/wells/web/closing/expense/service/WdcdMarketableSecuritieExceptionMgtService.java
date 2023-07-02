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

    public List<FindCodeRes> getBuilDingCd() {
        return mapper.selectBuilDingCd();
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

        for (SaveReq req : reqs) {
            WdcdMarketableSecuritieExceptionDvo dvo = converter.mapSaveReqToWdcdMarketableSecuritieExceptionDvo(req);

            String checkMonth = mapper.selectCheckWhetherMonthFinalized(dvo);

            if ("Y".equals(checkMonth)) {
                BizAssert.isTrue(true, "등록수정 불가합니다.");
            }

            AccCardInfoDetailRes detailRes = mapper.selectAccCardInfoDetail(dvo);

            if (StringUtils.isEmpty(detailRes.opcsAdjNo())) {

                if (StringUtils.isEmpty(opcsAdjNo)) {
                    opcsAdjNo = mapper.selectOpcsAdjNo(dvo);
                }

                dvo.setOpcsAdjNo(opcsAdjNo);
                count += mapper.insertAccMst(dvo);
                count += mapper.insertAccDetail(dvo);

                if ("Y".equals(dvo.getDeleted())) {
                    count += mapper.deleteAccDetail(dvo);
                }
            } else {

                count += mapper.updateAccMst(dvo);
                count += mapper.deleteAccDetail(dvo);
                count += mapper.insertAccDetail(dvo);
            }

            count += mapper.updateOpcsCard(dvo);

            int start = Integer.parseInt(opcsAdjNo.substring(1, 6));
            int end = Integer.parseInt(opcsAdjNo.substring(7, opcsAdjNo.length())) + 1;

            opcsAdjNo = String.valueOf(start) + String.format("%08d", end);
        }

        return count;
    }
}
