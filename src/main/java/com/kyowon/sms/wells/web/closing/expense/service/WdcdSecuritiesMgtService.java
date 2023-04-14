package com.kyowon.sms.wells.web.closing.expense.service;

import com.kyowon.sms.wells.web.closing.expense.converter.WdcdMarketableSecuritiesMgtConverter;
import com.kyowon.sms.wells.web.closing.expense.dto.WdcdSecuritiesMgtDto.*;
import com.kyowon.sms.wells.web.closing.expense.dvo.WdcdMarketableSecuritiesDvo;
import com.kyowon.sms.wells.web.closing.expense.mapper.WdcdSecuritiesMgtMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WdcdSecuritiesMgtService {

    private final WdcdSecuritiesMgtMapper mapper;
    private final WdcdMarketableSecuritiesMgtConverter converter;

    public List<SearchAdjustObjectRes> getAdjustObject(SearchAdjustObjectReq req) {

        return mapper.selectAdjustObject(req);
    }

    public List<SearchWithholdingTaxAdjustRes> getWithholdingTaxAdjust(SearchWithholdingTaxAdjustReq req) {

        return mapper.selectWithholdingTaxAdjust(req);
    }

    public int editWithholdingTaxAdjust(List<EditReq> reqs) {
        int count = 0;
        for (EditReq req : reqs) {
            WdcdMarketableSecuritiesDvo dvo = converter.mapEditReqToWdcdMarketableSecuritiesDvo(req);
            count += mapper.editWithholdingTaxAdjust(dvo);
        }

        return count;
    }
}
