package com.kyowon.sms.wells.web.closing.expense.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.closing.expense.converter.WdcdSecuritiesExceptionMgtContverter;
import com.kyowon.sms.wells.web.closing.expense.dto.WdcdSecuritiesExceptionMgtDto.*;
import com.kyowon.sms.wells.web.closing.expense.dvo.WdcdSecuritiesExceptionDvo;
import com.kyowon.sms.wells.web.closing.expense.mapper.WdcdSecuritiesExceptionMgtMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WdcdSecuritiesExceptionMgtService {

    private final WdcdSecuritiesExceptionMgtMapper mapper;
    private final WdcdSecuritiesExceptionMgtContverter contverter;

    public List<SearchAdjustObjectRes> getAdjustObject(SearchAdjustObjectReq req) {

        List<WdcdSecuritiesExceptionDvo> dvos = mapper.selectAdjustObject(req);

        List<SearchAdjustObjectRes> res = new ArrayList<>();
        for (WdcdSecuritiesExceptionDvo dvo : dvos) {
            res.add(contverter.mapWdcdMarketableSecuritiesExcdDvoToSearchAdjustObjectRes(dvo));
        }
        return res;
    }

    public List<SearchWithholdingTaxAdjustRes> getWithholdingTaxAdjust(SearchWithholdingTaxAdjustReq req) {
        return mapper.selectWithholdingTaxAdjust(req);
    }

    public int editWithholdingTaxAdjust(List<SaveReq> reqs) {
        int count = 0;
        for (SaveReq req : reqs) {
            WdcdSecuritiesExceptionDvo dvo = contverter.mapEditReqToWdcdMarketableSecuritiesExcdDvo(req);
            count += mapper.editWithholdingTaxAdjust(dvo);
        }

        return count;
    }

    public String getWithholdingTax(FindReq req) {
        return mapper.selectWithholdingTax(req);
    }
}
