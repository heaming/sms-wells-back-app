package com.kyowon.sms.wells.web.closing.expense.service;

import com.kyowon.sms.wells.web.closing.expense.converter.WdcdMarketableSecuritiesMgtConverter;
import com.kyowon.sms.wells.web.closing.expense.dto.WdcdMarketableSecuritiesMgtDto.*;
import com.kyowon.sms.wells.web.closing.expense.dvo.WdcdMarketableSecuritiesDvo;
import com.kyowon.sms.wells.web.closing.expense.mapper.WdcdMarketableSecuritiesMgtMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WdcdMarketableSecuritiesMgtService {

    private final WdcdMarketableSecuritiesMgtMapper mapper;
    private final WdcdMarketableSecuritiesMgtConverter converter;

    public List<AdjustObjectRes> getAdjustObject(AdjustObjectReq req) {

        return mapper.selectAdjustObject(req);
    }

    public List<WithholdingTaxAdjustRes> getWithholdingTaxAdjust(WithholdingTaxAdjustReq req) {

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
