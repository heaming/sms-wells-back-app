package com.kyowon.sms.wells.web.closing.expense.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.closing.expense.converter.WdcdMarketableSecuritieMgtPopConverter;
import com.kyowon.sms.wells.web.closing.expense.dto.WdcdSecuritiesMgtDto.*;
import com.kyowon.sms.wells.web.closing.expense.dvo.WdcdSecuritiesDvo;
import com.kyowon.sms.wells.web.closing.expense.mapper.WdcdSecuritiesMgtMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WdcdSecuritiesMgtService {

    private final WdcdSecuritiesMgtMapper mapper;
    private final WdcdMarketableSecuritieMgtPopConverter converter;

    public List<SearchAdjustObjectRes> getAdjustObject(SearchAdjustObjectReq req) {

        List<WdcdSecuritiesDvo> dvos = mapper.selectAdjustObject(req);

        List<SearchAdjustObjectRes> res = new ArrayList<>();
        for (WdcdSecuritiesDvo dvo : dvos) {
            res.add(converter.mapWdcdMarketableSecuritiesDvoToSearchAdjustObjectRes(dvo));
        }
        return res;
    }

    public List<SearchWithholdingTaxAdjustRes> getWithholdingTaxAdjust(SearchWithholdingTaxAdjustReq req) {
        return mapper.selectWithholdingTaxAdjust(req);
    }

    public int editWithholdingTaxAdjust(List<SaveReq> reqs) {
        int count = 0;
        for (SaveReq req : reqs) {
            WdcdSecuritiesDvo dvo = converter.mapSaveReqToWdcdMarketableSecuritiesDvo(req);
            count += mapper.editWithholdingTaxAdjust(dvo);
        }

        return count;
    }

    public String getWithholdingTax(FindReq req) {
        return mapper.selectWithholdingTax(req);
    }
}
