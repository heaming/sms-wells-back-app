package com.kyowon.sms.wells.web.closing.expense.service;

import com.kyowon.sms.wells.web.closing.expense.converter.WdcdMarketableSecuritiesExcdMgtContverter;
import com.kyowon.sms.wells.web.closing.expense.dto.WdcdSecuritiesExceptionMgtDto.*;
import com.kyowon.sms.wells.web.closing.expense.dvo.WdcdMarketableSecuritiesExcdDvo;
import com.kyowon.sms.wells.web.closing.expense.mapper.WdcdSecuritiesExceptionMgtMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WdcdSecuritiesExceptionMgtService {

    private final WdcdSecuritiesExceptionMgtMapper mapper;
    private final WdcdMarketableSecuritiesExcdMgtContverter contverter;

    public List<SearchAdjustObjectRes> getAdjustObject(SearchAdjustObjectReq req) {

        return mapper.selectAdjustObject(req);
    }

    public List<SearchWithholdingTaxAdjustRes> getWithholdingTaxAdjust(SearchWithholdingTaxAdjustReq req) {

        return mapper.selectWithholdingTaxAdjust(req);
    }

    public int editWithholdingTaxAdjust(List<EditReq> reqs) {
        int count = 0;
        for (EditReq req : reqs) {
            WdcdMarketableSecuritiesExcdDvo dvo = contverter.mapEditReqToWdcdMarketableSecuritiesExcdDvo(req);
            count += mapper.editWithholdingTaxAdjust(dvo);
        }

        return count;
    }
}
